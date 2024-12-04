package com.habitual.demo.hotel.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelPageDto {

    private String name;

    private String tel;

    private String type;

    private int pageNum;

    private int pageSize;

    private int offset;

}
