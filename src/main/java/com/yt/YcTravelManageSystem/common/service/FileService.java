package com.yt.YcTravelManageSystem.common.service;

import com.yt.YcTravelManageSystem.common.entity.CommonResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * 业务层 文件
 */
public interface FileService {

    CommonResponse upload(MultipartFile file);

}
