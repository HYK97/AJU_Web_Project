package com.aju.web.domain.image;

import com.aju.web.domain.announcement.Notice;
import com.aju.web.domain.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * packageName :  com.aju.web.domain.image
 * fileName : Image
 * author :  ddh96
 * date : 2023-04-26 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-26                ddh96             최초 생성
 */
@Entity
public class Image extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_number", nullable = false)
    private Long imageNumber;
    private Long imageSize;
    private String originalImageName;
    private String imageURL;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_number")
    private Notice notice;

}
