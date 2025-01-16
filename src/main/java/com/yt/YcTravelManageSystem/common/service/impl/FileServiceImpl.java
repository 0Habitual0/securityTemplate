package com.yt.YcTravelManageSystem.common.service.impl;

import com.yt.YcTravelManageSystem.common.entity.CommonResponse;
import com.yt.YcTravelManageSystem.common.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * 业务层实现 文件
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public CommonResponse upload(MultipartFile file) {
        try {
            // 获取文件后缀名
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            int i = 0;
            if (originalFilename != null) {
                i = originalFilename.lastIndexOf('.');
            }
            if (i > 0) {
                fileExtension = originalFilename.substring(i);
            }
            // 生成唯一文件名
            String uniqueFileName = UUID.randomUUID() + fileExtension;
            // 获取项目根路径
            String rootPath = System.getProperty("user.dir");
            // 指定保存路径
            String directoryPath = rootPath + "/static/uploads/";
            String filePath = directoryPath + uniqueFileName;

            // 创建目录
            Path path = Paths.get(directoryPath);
            if (Files.notExists(path)) {
                Files.createDirectories(path);
            }
            // 保存文件
            file.transferTo(new File(filePath));

            // 返回文件访问URL
            return CommonResponse.success("/uploads/" + uniqueFileName);

        } catch (IOException e) {
            return CommonResponse.fail("文件上传失败: " + e.getMessage());
        }
    }

}
