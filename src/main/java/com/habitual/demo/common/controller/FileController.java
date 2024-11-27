package com.habitual.demo.common.controller;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public CommonResponse upload(@RequestParam("file") MultipartFile file) {
        return fileService.upload(file);
    }

}
