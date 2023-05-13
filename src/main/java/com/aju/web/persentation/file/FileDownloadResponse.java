package com.aju.web.persentation.file;

import org.springframework.core.io.Resource;

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
public class FileDownloadResponse {
    Resource resource;
    String fileName;

    public static FileDownloadResponse of(Resource resource, String fileName) {
        return new FileDownloadResponse(resource, fileName);
    }
}
