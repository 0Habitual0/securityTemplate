package com.habitual.demo.comment.mapper;

import com.habitual.demo.comment.entity.CommentEntity;
import com.habitual.demo.comment.entity.dto.CommentPageDto;
import com.habitual.demo.hotel.entity.HotelEntity;
import com.habitual.demo.hotel.entity.dto.HotelPageDto;
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

}
