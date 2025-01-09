package com.habitual.demo.touristGuide.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TouristGuideCarouselImagePageDto {

    private String businessId;

    private String createBy;

    private String updateBy;

    private String remark;

    private int pageNum;

    private int pageSize;

    private int offset;

}
