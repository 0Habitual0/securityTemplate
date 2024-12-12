package com.habitual.demo.order.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPageDto {

    private boolean userSelect;

    private String orderCode;

    private String status;

    private String createBy;

    private String updateBy;

    private String remark;

    private int pageNum;

    private int pageSize;

    private int offset;

}
