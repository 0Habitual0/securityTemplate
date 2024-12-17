package com.habitual.demo.comment.service.impl;

import com.habitual.demo.comment.entity.CommentEntity;
import com.habitual.demo.comment.entity.dto.CommentPageDto;
import com.habitual.demo.comment.mapper.CommentMapper;
import com.habitual.demo.comment.service.CommentService;
import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.entity.PageResult;
import com.habitual.demo.common.security.context.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 业务层实现 评论
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public CommonResponse save(CommentEntity input) {
        input.setCreateBy(UserContext.getNickname());
        input.setCreateTime(new Date());
        input.setUserId(UserContext.getId());
        return CommonResponse.success(commentMapper.insert(input));
    }

    @Transactional
    @Override
    public CommonResponse delete(Long id) {
        return CommonResponse.success(commentMapper.delete(id));
    }

    @Override
    public CommonResponse selectByPage(CommentPageDto input) {
        if (input.isUserSelect()) {
            input.setUserId(UserContext.getId());
        } else {
            input.setUserId(null);
        }
        input.setOffset((input.getPageNum() - 1) * input.getPageSize());
        List<CommentEntity> list = commentMapper.selectByPage(input);
        if (!list.isEmpty()) {
            List<CommentEntity> childList = commentMapper.selectByParentIdIn(list.stream().map(CommentEntity::getId).collect(Collectors.toList()));
            Map<String, List<CommentEntity>> childMap = childList.stream()
                    .collect(Collectors.groupingBy(CommentEntity::getParentId));
            // 设置每个父评论的子评论
            list.forEach(comment -> comment.setChildren(childMap.get(comment.getId().toString())));
        }
        int totalCount = commentMapper.getTotalCount(input.getBusinessId());

        int pages = (int) Math.ceil((double) totalCount / input.getPageSize());

        PageResult<CommentEntity> result = new PageResult<>();
        result.setData(list);
        result.setTotalCount(totalCount);
        result.setPages(pages);

        return CommonResponse.success(result);
    }

}
