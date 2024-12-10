package com.habitual.demo.comment.controller;

import com.habitual.demo.comment.entity.CommentEntity;
import com.habitual.demo.comment.entity.dto.CommentPageDto;
import com.habitual.demo.comment.service.CommentService;
import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.hotel.entity.HotelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 控制层 评论
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 新增/回复
     */
    @PostMapping("save")
    public CommonResponse save(@RequestBody CommentEntity input) {
        return commentService.save(input);
    }

    /**
     * 删除
     */
    @GetMapping("delete")
    public CommonResponse delete(@RequestParam Long id) {
        return commentService.delete(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("selectByPage")
    public CommonResponse selectByPage(@RequestBody CommentPageDto input) {
        return commentService.selectByPage(input);
    }

}