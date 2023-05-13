package com.aju.web.persentation.image;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aju.web.application.image.ImageService;
import com.aju.web.domain.image.Image;

import lombok.RequiredArgsConstructor;

/**
 * packageName :  com.aju.web.persentation.image
 * fileName : ImageController
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
public class ImageController {

    private final ImageService imageService;
    private final ResourceLoader resourceLoader;

    @PostMapping("/image")
    public ResponseEntity<String> imageUpload(MultipartFile file) {
        return ResponseEntity.ok().body(imageService.storeImageWithImagePath(file));
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<Resource> serveFile(@PathVariable Long id) {
        Image uploadFile = imageService.loadImage(id);
        Resource resource = resourceLoader.getResource("file:" + uploadFile.getImagePath());
        return ResponseEntity.ok().body(resource);
    }
}
