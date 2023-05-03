package com.aju.web.infra.repository.notice;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aju.web.domain.notice.Notice;
import com.aju.web.persentation.notice.NoticeResponse;

/**
 * packageName :  com.aju.web.infra.repository.notice
 * fileName : NoticeRepository
 * author :  ddh96
 * date : 2023-04-30 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-30                ddh96             최초 생성
 */
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query("select n from Notice n left join fetch n.files where n.project = ?2 and n.noticeNumber = ?1")
    Optional<Notice> findByProjectAndNoticeNumber(Long noticeNumber,boolean isProject);

    Page<Notice> findByTitleContainsAndProject(String search, Pageable pageable, boolean project);


}
