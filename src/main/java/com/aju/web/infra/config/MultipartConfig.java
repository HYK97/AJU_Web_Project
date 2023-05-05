package com.aju.web.infra.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import com.aju.web.infra.exception.ApplicationException;
import com.aju.web.infra.exception.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

@Configuration
public class MultipartConfig {

    private final List<String> deniedExtensions = List.of(".exe", ".bat", ".sh"); // 업로드를 금지할 확장자 목록

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver() {
            @Override
            public boolean isMultipart(HttpServletRequest request) {
                boolean isMultipart = super.isMultipart(request);

                if (isMultipart) {
                    String fileName = null;
                    Part file = null;
                    try {
                        file = request.getPart("file");
                    } catch (IllegalStateException e) {
                        throw new ApplicationException(ErrorCode.FILE_SIZE_EXCEED);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    if (file != null) {
                        fileName = file.getSubmittedFileName();
                    }

                    if (fileName != null && !fileName.isEmpty()) {
                        String extension = fileName.substring(fileName.lastIndexOf('.'));
                        if (deniedExtensions.contains(extension)) {
                            throw new ApplicationException(ErrorCode.FILE_EXTENSION_DENIED);
                        }
                    }
                }

                return isMultipart;
            }
        };
    }
}
