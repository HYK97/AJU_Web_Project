package com.aju.web.infra.utils;

import java.util.UUID;

/**
 * packageName :  com.aju.web.infra.utils
 * fileName : FileUtils
 * author :  ddh96
 * date : 2023-04-30 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-30                ddh96             최초 생성
 */
public class FileUtils {
    public static String createUUIDName() {
        return UUID.randomUUID().toString();
    }
}
