package com.aju.web.persentation.notice;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class ProjectResponse {

    private Long noticeNumber;

    private String title;

    private Long thumbnail;

}
