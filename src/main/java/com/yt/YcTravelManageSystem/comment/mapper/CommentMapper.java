package com.yt.YcTravelManageSystem.comment.mapper;

import com.yt.YcTravelManageSystem.comment.entity.CommentEntity;
import com.yt.YcTravelManageSystem.comment.entity.dto.CommentPageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据访问层Mybatis 评论
 */
@Mapper
public interface CommentMapper {

    int insert(CommentEntity input);

    int delete(Long id);

    int getTotalCount(String businessId);

    List<CommentEntity> selectByPage(CommentPageDto input);

    List<CommentEntity> selectByParentIdIn(List<Long> parentIds);

}
