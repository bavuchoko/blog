package com.parkjongsu.blog.serve.files.service.impl;

import com.parkjongsu.blog.serve.files.entity.Files;
import com.parkjongsu.blog.serve.files.entity.FilesType;
import com.parkjongsu.blog.serve.files.repository.FilesJpaRepository;
import com.parkjongsu.blog.serve.files.service.FilesUploadService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@Service
public class FilesUploadServiceImpl implements FilesUploadService {

    private final FilesJpaRepository filesJpaRepository;

    private final String ROOT_UPLOAD_PATH;
    private final String[] IMAGES = {"jpg", "png", "jpeg", "gif", "bmp"};
    private final String[] DOCUMENTS = {"txt", "excel", "ppt", "hwp", "word", "PDF"};


    public FilesUploadServiceImpl(
            @Value("${file.uploadPath}") String rootUploadPath,
            FilesJpaRepository simmsFileJpaRepository
    ){
        this.ROOT_UPLOAD_PATH = rootUploadPath;
        this.filesJpaRepository = simmsFileJpaRepository;
    }


    @Override
    public String imageUpload(MultipartFile file, FilesType type, String folderName) throws IOException {
        String originalName = file.getOriginalFilename();
        String ext = extractExtention(originalName);

        //이미지파일 아니면 예외 던지고 중지;
        this.fileTypeChecker(ext, IMAGES);

        //file 객체생성
        String serverPath = this.createDirectory(folderName);
        String uploadName = this.generateUUIDName(ext);
        File newFile = new File(serverPath, uploadName);
        //file 저장
        this.saveFile(file, newFile);

        //썸네일 : 용량이 크면 섬네일 생성
        long fileSize = file.getSize();
        //해당 파일이 썸네일이 있는지 없는지 여부
        boolean hasThumbnail = false;
        if (isBigEnoughForThumbnail(fileSize)) {
            hasThumbnail = true;
            generateThumbnailImage(newFile, ext);
        }

        //DB에 업로드 내역 저장
        Files insertToDbFile = Files.builder()
                .uuidFileName(uploadName)
                .originalFileName(originalName)
                .uploadPath(serverPath)
                .type(type.name())
                .thumbnail(hasThumbnail)
                .size(fileSize)
                .build();
         this.insertIntoDb(insertToDbFile);
        return serverPath +  File.separator + uploadName;
    }

    private String extractExtention(String originalName) {
        String ext ="";
        if(originalName.lastIndexOf('.')>0)
            ext = originalName.substring(originalName.lastIndexOf('.') + 1);
        return ext;
    }
    /**
     * 파일 확장자가 해당 파일이 맞는지 여부
     * 확장자 명이 없거나 허용된 파일에 포함되어 있지 않으면 예외던짐
     */
    private void fileTypeChecker(String ext, String... types) {
        if (!StringUtils.hasText(ext) || !Arrays.asList(types).contains(ext))
            throw new IllegalArgumentException("허용되지 않은 파일입니다.");
    }


    /**
     * 업로드 경로 생성
     * upload 폴더 밑에 {category} 폴더 -if(없으면 생성해서)- 경로를 리턴
     */
    private String createDirectory(String category) throws IOException {
        String rootUploadPath = ROOT_UPLOAD_PATH + File.separator;
        String serverPath = rootUploadPath + File.separator + category + File.separator;
        File directory = new File(serverPath);
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }
        return serverPath;
    }

    /**
     * 업로드용 파일이름 생성
     * UUID.ext
     */
    public String generateUUIDName(String ext) throws IOException {
        UUID uuid = UUID.randomUUID();
        String uuidFileName = uuid.toString() + "." +ext;
        return uuidFileName;
    }
    /**
     * 실제 파일 저장
     */
    public void saveFile(MultipartFile mtf, File file) throws IOException {
        mtf.transferTo(file);
    }


    /**
     * 썸네일 생성할 만큼 큰 파일인지 체크하는 메서드
     */
    private boolean isBigEnoughForThumbnail(long fileSize) {
        long maxSizeInBytes = 2000000;  //2메가

        if (fileSize > maxSizeInBytes) {
            return true;
        }
        return false;
    }

    /**
     * 썸네일 용 이미지 생성
     * 128 * 128
     */
    private BufferedImage generateThumbnailImage(File file, String ext) throws IOException {
        File thumbnailFile = new File(file.getAbsolutePath(), "thumb_" + file.getName());

        BufferedImage bo_image = ImageIO.read(file);

        //생성될 넓이 높이
        BufferedImage bt_image = new BufferedImage(128, 128, BufferedImage.TYPE_3BYTE_BGR);

        Graphics2D graphic = bt_image.createGraphics();

        graphic.drawImage(bo_image, 0, 0, 128, 128, null);


        // 생성된 썸네일 이미지 저장
        /**
         * Todo 리팩토링
         * ImageIO.write 를 호출해서 저장하는게 아니라 다른 파일들 처럼 this.saveFile(MultipartFile mtf, File saveFile) 를 활용하고 싶어서 리팩토링 중
         * */
        ImageIO.write(bt_image, ext, thumbnailFile);
        return bt_image;
    }

    /**
     * DB에 저장하기
     */
    @Transactional
    public long insertIntoDb(Files simmsFile) throws IOException {
        return filesJpaRepository.save(simmsFile).getId();
    }

}
