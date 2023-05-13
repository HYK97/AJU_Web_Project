package com.aju.web.persentation.notice;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoticeRequest {
    @NotBlank(message = "이칸은 비워둘 수 없었습니다.")
    @Length(min = 1, max = 400, message = "최소 1자이상  최대 400자 이하로 작성해주세요")
    private String title;
    @NotBlank(message = "이칸은 비워둘 수 없었습니다.")
    @Length(min = 1, message = "최소 1자이상 작성해주세요")
    private String content;
    private List<MultipartFile> file;

}