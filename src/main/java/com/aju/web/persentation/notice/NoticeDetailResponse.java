package com.aju.web.persentation.notice;

import java.time.LocalDateTime;
import java.util.List;

import com.aju.web.domain.notice.Notice;
import com.aju.web.persentation.file.FileResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName :  com.aju.web.persentation.notice
 * fileName : NoticeDetailResponse
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
public class NoticeDetailResponse {
    private Long noticeNumber;
    private String title;
    private String content;
    private String constructionYear;

    private LocalDateTime modifiedDate;
    private List<FileResponse> filesURL;

    public static NoticeDetailResponse from(Notice notice) {
        List<FileResponse> files = notice.getFiles().stream().map(FileResponse::from).toList();
        return new NoticeDetailResponse(notice.getNoticeNumber(), notice.getTitle(), notice.getConstructionYear(), notice.getContent(),
            notice.getUpdatedTime(), files);
    }

}
