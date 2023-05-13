package com.aju.web.persentation.notice;

import java.time.LocalDateTime;

import com.aju.web.domain.notice.Notice;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName :  com.aju.web.persentation.notice
 * fileName : NoticeResponse
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
public class NoticeResponse {
    private Long noticeNumber;
    private String title;
    private String content;
    private LocalDateTime modifiedDate;

    public static NoticeResponse from(Notice notice) {
        return new NoticeResponse(notice.getNoticeNumber(), notice.getTitle(), notice.getContent(),
            notice.getUpdatedTime());
    }
}
