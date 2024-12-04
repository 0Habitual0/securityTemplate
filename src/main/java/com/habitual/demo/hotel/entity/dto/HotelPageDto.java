package com.habitual.demo.hotel.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelPageDto {

    private String name;

    private String type;

    private String tel;

    private String createBy;

    private String updateBy;

    private String remark;

    private int pageNum;

    private int pageSize;

    private int offset;

}
