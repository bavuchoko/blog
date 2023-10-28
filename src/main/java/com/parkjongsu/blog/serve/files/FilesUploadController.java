package com.parkjongsu.blog.serve.files;

import com.parkjongsu.blog.common.WebCommon;
import com.parkjongsu.blog.serve.files.entity.FilesType;
import com.parkjongsu.blog.serve.files.service.FilesUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/file", produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
public class FilesUploadController {

    private final FilesUploadService filesUploadService;

    @Value("${file.downloadPath}")
    private String FILE_PATH;
    @PostMapping
    public ResponseEntity uploadFile(
            @RequestPart(value ="file", required=true) MultipartFile file
    ) throws IOException {
        String fileNameWithPath;
        try {
            fileNameWithPath = filesUploadService.imageUpload(file, FilesType.IMG, "content");
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Map response = new HashMap();
        response.put("uri", fileNameWithPath);
        return ResponseEntity.ok(response);
    }
}
