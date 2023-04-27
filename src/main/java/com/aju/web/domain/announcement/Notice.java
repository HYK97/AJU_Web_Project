package com.aju.web.domain.announcement;

import java.util.List;

import com.aju.web.domain.common.BaseEntity;
import com.aju.web.domain.file.File;
import com.aju.web.domain.image.Image;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

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
public class Notice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_number", nullable = false)
    private Long noticeNumber;

    private String title;
    private String content;

    @OneToMany(mappedBy = "notice")
    private List<File> files;
    @OneToMany(mappedBy = "notice")
    private List<Image> images;



}
