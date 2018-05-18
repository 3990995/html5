package com.liao27.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件存储
 * Created by main on 2018/5/17.
 */
public class FileUtil {

    public static String saveFile(String uploadLocation,MultipartFile file) throws IOException {
        String ofn = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + ofn.substring(ofn.lastIndexOf("."));
        file.transferTo(new File(uploadLocation + fileName));
        return fileName;
    }
}
