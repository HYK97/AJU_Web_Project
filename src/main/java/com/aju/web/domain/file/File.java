package com.aju.web.domain.file;

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
import jakarta.persistence.OneToMany;
/*
 * packageName :  com.aju.web.domain.file
 * fileName : File
 * author :  ddh96
 * date : 2023-04-25 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-25                ddh96             최초 생성
 */

@Entity
public class File extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_number", nullable = false)
    private Long fileNumber;
    private String originalFileName;
    private Long fileSize;
    private String fileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_number")
    private Notice notice;

}
