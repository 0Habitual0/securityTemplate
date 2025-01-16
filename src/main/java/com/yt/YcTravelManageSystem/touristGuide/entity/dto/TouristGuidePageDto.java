package com.yt.YcTravelManageSystem.touristGuide.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TouristGuidePageDto {

    private String title;

    private String type;

    private String createBy;

    private String updateBy;

    private String remark;

    private int pageNum;

    private int pageSize;

    private int offset;

}
