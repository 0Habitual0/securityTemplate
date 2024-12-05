package com.habitual.demo.comment.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentPageDto {

    private String businessId;

    private String type;

    private String createBy;

    private String updateBy;

    private String remark;

    private int pageNum;

    private int pageSize;

    private int offset;


}
