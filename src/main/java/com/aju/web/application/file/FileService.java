package com.aju.web.application.file;

import static com.aju.web.infra.utils.FileUtils.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aju.web.domain.file.File;
import com.aju.web.domain.image.Image;
import com.aju.web.domain.notice.Notice;
import com.aju.web.infra.exception.ApplicationException;
import com.aju.web.infra.exception.ErrorCode;
import com.aju.web.infra.repository.file.FileRepository;
import com.aju.web.persentation.file.FileDownloadResponse;

import lombok.RequiredArgsConstructor;

/**
 * packageName :  com.aju.web.application.file
 * fileName : FileService
 * author :  ddh96
 * date : 2023-04-30 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-30                ddh96             최초 생성
 */
@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    @Value("${file.file-path}")
    private String FILE_PATH;

    public FileDownloadResponse fileDownLoad(Long fileId) {

        File file = fileRepository.findById(fileId)
            .orElseThrow(() -> new ApplicationException(ErrorCode.FILE_NOT_FOUND));
        Path filePath = Paths.get(file.getFilePath());
        Resource resource = null; // 파일 resource 얻기
        try {
            resource = new InputStreamResource(Files.newInputStream(filePath));
        } catch (IOException e) {
            throw new ApplicationException(ErrorCode.FILE_SAVE_ERROR,e);
        }
        return FileDownloadResponse.of(resource, file.getOriginalFileName());
    }

    public String getPath(String filename) {
        return FILE_PATH + filename;
    }

    @Transactional
    public void FileStore(List<MultipartFile> files, Notice saveNotice) {
        java.io.File folder = new java.io.File(FILE_PATH);

        if (!folder.exists()) {
            folder.mkdirs();
        }
        if (files.size() == 0) {
            return;
        }
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            String uuid = createUUIDName();
            String filename = uuid + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
            Image image = null;
            try {
                file.transferTo(new java.io.File(getPath(filename)));
                fileNames.add(filename);
            } catch (IOException e) {

                throw new ApplicationException(ErrorCode.IMAGE_SAVE_ERROR,e);
            }

        }
        saveFiles(files, fileNames, saveNotice);
    }

    public void saveFiles(List<MultipartFile> files, List<String> uuidNames, Notice saveNotice) {
        List<File> file = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            file.add(File.builder()
                .originalFileName(files.get(i).getOriginalFilename())
                .filePath(getPath(uuidNames.get(i)))
                .fileSize(files.get(i).getSize())
                .fileName(uuidNames.get(i))
                .notice(saveNotice)
                .build());
        }
        fileRepository.saveAll(file);
    }

    @Transactional
    public void deleteFiles(Long noticeNumber) {
        List<File> files = fileRepository.findByNotice_NoticeNumber(noticeNumber);
        for (File file : files) {
            java.io.File deleteFile = new java.io.File(file.getFilePath());
            if (!deleteFile.delete()) {
                throw new ApplicationException(ErrorCode.FILE_DELETE_ERROR);
            }
        }
        fileRepository.deleteByNotice_NoticeNumber(noticeNumber);
    }

}
