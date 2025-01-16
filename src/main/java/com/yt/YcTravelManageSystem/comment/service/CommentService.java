package com.yt.YcTravelManageSystem.comment.service;

import com.yt.YcTravelManageSystem.comment.entity.CommentEntity;
import com.yt.YcTravelManageSystem.comment.entity.dto.CommentPageDto;
import com.yt.YcTravelManageSystem.common.entity.CommonResponse;

/**
 * 业务层 评论
 */
public interface CommentService {

    CommonResponse save(CommentEntity input);

    CommonResponse delete(Long id);

    CommonResponse selectByPage(CommentPageDto input);

}
