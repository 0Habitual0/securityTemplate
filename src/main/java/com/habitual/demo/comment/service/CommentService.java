package com.habitual.demo.comment.service;

import com.habitual.demo.comment.entity.CommentEntity;
import com.habitual.demo.comment.entity.dto.CommentPageDto;
import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.hotel.entity.HotelEntity;
import com.habitual.demo.hotel.entity.dto.HotelPageDto;

/**
 * 业务层 评论
 */
public interface CommentService {

    CommonResponse save(CommentEntity input);

    CommonResponse delete(Long id);

    CommonResponse selectByPage(CommentPageDto input);

}
