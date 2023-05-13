package com.aju.web.persentation.file;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.aju.web.application.file.FileService;

import lombok.RequiredArgsConstructor;

/**
 * packageName :  com.aju.web.persentation.file
 * fileName : FileController
 * author :  ddh96
 * date : 2023-04-30 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-30                ddh96             최초 생성
 */
@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final ResourceLoader resourceLoader;

    @GetMapping("/file/{id}")
    public ResponseEntity<Resource> serveFile(@PathVariable Long id) throws UnsupportedEncodingException {
        FileDownloadResponse fileDownloadResponse = fileService.fileDownLoad(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(
            URLEncoder.encode(fileDownloadResponse.getFileName(), String.valueOf(StandardCharsets.UTF_8))).build());
        return new ResponseEntity<>(fileDownloadResponse.getResource(), headers, HttpStatus.OK);
    }
}
