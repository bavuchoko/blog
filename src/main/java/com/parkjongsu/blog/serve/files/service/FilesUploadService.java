package com.parkjongsu.blog.serve.files.service;

import com.parkjongsu.blog.serve.files.entity.FilesType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FilesUploadService {

    String imageUpload(MultipartFile list, FilesType type, String folderName) throws IOException;
}
