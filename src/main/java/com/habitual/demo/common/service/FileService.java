package com.habitual.demo.common.service;

import com.habitual.demo.common.entity.CommonResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * 业务层 文件
 */
public interface FileService {

    CommonResponse upload(MultipartFile file);

}
