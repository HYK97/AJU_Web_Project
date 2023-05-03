package com.aju.web.infra.repository.file;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aju.web.domain.file.File;
import com.aju.web.domain.notice.Notice;

/**
 * packageName :  com.aju.web.infra.repository.file
 * fileName : FileRepository
 * author :  ddh96
 * date : 2023-04-30 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-30                ddh96             최초 생성
 */
public interface FileRepository extends JpaRepository<File,Long> {

    List<File> findByNotice_NoticeNumber(Long noticeNumber);


    void deleteByNotice_NoticeNumber(Long noticeNumber);
}
