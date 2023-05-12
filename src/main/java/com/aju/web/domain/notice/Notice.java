package com.aju.web.domain.notice;

import java.util.List;

import org.aspectj.weaver.ast.Not;

import com.aju.web.domain.common.BaseEntity;
import com.aju.web.domain.file.File;
import com.aju.web.domain.image.Image;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName :  com.aju.web.domain.inquiry
 * fileName : Inquiry
 * author :  ddh96
 * date : 2023-04-25 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-25                ddh96             최초 생성
 */

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_number", nullable = false)
    private Long noticeNumber;

    @Column(length = 400)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    private boolean project;

    @JoinColumn(name = "image_number")
    @OneToOne(fetch = FetchType.LAZY)
    private Image thumbnail;

    @OneToMany(mappedBy = "notice")
    private List<File> files;

    public void updateThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void update(Notice updateNotice) {
        this.title = updateNotice.getTitle();
        this.content = updateNotice.getContent();
        this.thumbnail = updateNotice.getThumbnail() != null ? updateNotice.getThumbnail() : this.thumbnail;
    }

}
