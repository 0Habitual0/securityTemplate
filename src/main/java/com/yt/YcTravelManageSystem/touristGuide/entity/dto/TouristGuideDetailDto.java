package com.yt.YcTravelManageSystem.touristGuide.entity.dto;

import com.yt.YcTravelManageSystem.touristGuide.entity.TouristGuideEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TouristGuideDetailDto extends TouristGuideEntity {

    /**
     * 轮播图
     */
    private List<String> carouselImage;

}
