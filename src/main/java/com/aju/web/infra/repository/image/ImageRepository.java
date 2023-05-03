package com.aju.web.infra.repository.image;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aju.web.domain.image.Image;

/**
 * packageName :  com.aju.web.infra.repository.image
 * fileName : ImageRepository
 * author :  ddh96
 * date : 2023-04-30 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-30                ddh96             최초 생성
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
}
