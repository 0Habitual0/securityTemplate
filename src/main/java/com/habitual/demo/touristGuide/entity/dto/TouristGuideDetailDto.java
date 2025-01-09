package com.habitual.demo.touristGuide.entity.dto;

import com.habitual.demo.touristGuide.entity.TouristGuideEntity;
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
