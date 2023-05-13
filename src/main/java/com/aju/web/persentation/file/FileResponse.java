package com.aju.web.persentation.file;

import com.aju.web.domain.file.File;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName :  com.aju.web.persentation.file
 * fileName : FileResponse
 * author :  ddh96
 * date : 2023-04-30 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-30                ddh96             최초 생성
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FileResponse {
    private Long fileNumber;
    private String originalFileName;
    private String fileName;
    private Long fileSize;
    private String filePath;

    public static FileResponse from(File file) {
        return new FileResponse(file.getFileNumber(), file.getOriginalFileName(), file.getFileName(),
            file.getFileSize(), "/file/" + file.getFileNumber());
    }
}
