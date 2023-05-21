package com.aju.web.application.image;

import static com.aju.web.infra.utils.FileUtils.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aju.web.domain.image.Image;
import com.aju.web.infra.exception.ApplicationException;
import com.aju.web.infra.exception.ErrorCode;
import com.aju.web.infra.repository.image.ImageRepository;

import lombok.RequiredArgsConstructor;

/**
 * packageName :  com.aju.web.application.image
 * fileName : ImageService
 * author :  ddh96
 * date : 2023-04-29 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-29                ddh96             최초 생성
 */
@Service
@RequiredArgsConstructor
public class ImageService {
    @Value("${file.image-path}")
    private String FILE_PATH;
    private final ImageRepository imageRepository;

    public String getPath(String filename) {
        return FILE_PATH + filename;
    }

    public String storeImageWithImagePath(MultipartFile file) {
        Image image = imageProcess(file);
        if (image == null)
            return null;

        return "/image/" + image.getImageNumber();
    }

    private Image imageProcess(MultipartFile file) {
        File folder = new File(FILE_PATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        if (file.isEmpty()) {
            return null;
        }
        String uuid = createUUIDName();
        String filename = uuid + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
        Image image = null;
        try {
            file.transferTo(new File(getPath(filename)));
            image = saveImage(file, filename);
        } catch (IOException e) {
            throw new ApplicationException(ErrorCode.IMAGE_SAVE_ERROR,e);
        }
        return image;
    }

    public Image storeImage(MultipartFile file) {
        Image image = imageProcess(file);
        if (image == null)
            return null;
        return image;
    }

    private Image saveImage(MultipartFile file, String uuidName) throws IOException {
        Image image = Image.builder()
            .imageSize(file.getSize())
            .originalImageName(file.getOriginalFilename())
            .fileName(uuidName)
            .imagePath(FILE_PATH + File.separatorChar + uuidName).build();

        return imageRepository.save(image);
    }

    public Image loadImage(Long fileId) {
        return imageRepository.findById(fileId).orElseThrow(() -> new ApplicationException(ErrorCode.IMAGE_NOT_FOUND));
    }

    public void deleteImage(Image image) {
        String imagePath = image.getImagePath();
        imageRepository.delete(image);
        try {
            Files.delete(new File(imagePath).toPath());
        } catch (IOException e) {
            throw new ApplicationException(ErrorCode.IMAGE_DELETE_ERROR,e);
        }
    }
}
