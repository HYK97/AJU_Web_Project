package com.aju.web.application.notice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aju.web.application.file.FileService;
import com.aju.web.application.image.ImageService;
import com.aju.web.domain.image.Image;
import com.aju.web.domain.notice.Notice;
import com.aju.web.infra.exception.ApplicationException;
import com.aju.web.infra.exception.ErrorCode;
import com.aju.web.infra.repository.notice.NoticeRepository;
import com.aju.web.persentation.notice.BoardType;
import com.aju.web.persentation.notice.NoticeRequest;
import com.aju.web.persentation.notice.ProjectRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName :  com.aju.web.application.notice
 * fileName : NoticeService
 * author :  ddh96
 * date : 2023-04-30 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-30                ddh96             최초 생성
 */
@Service
@RequiredArgsConstructor
public class NoticeService {
    public final NoticeRepository noticeRepository;

    public final FileService fileService;
    public final ImageService imageService;
    private static final Pattern pattern = Pattern.compile("/image/(\\d+)");

    public Notice getNotice(Long id, BoardType notice) {
        return noticeRepository.findByProjectAndNoticeNumber(id, notice.isProject())
            .orElseThrow(() -> new ApplicationException(ErrorCode.NOTICE_NOT_FOUND));
    }

    @Transactional
    public Notice create(NoticeRequest noticeRequest) {
        Notice build = Notice.builder()
            .title(noticeRequest.getTitle())
            .content(noticeRequest.getContent())
            .project(false)
            .thumbnail(null)
            .build();
        Notice saveNotice = noticeRepository.save(build);

        if (noticeRequest.getFile().get(0).getSize() > 0) {
            fileService.FileStore(noticeRequest.getFile(), saveNotice);
        }
        return saveNotice;
    }

    @Transactional
    public Page<Notice> getList(String search, int page, BoardType notice) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("updatedTime").descending());
        if (notice.isProject()) {
            return noticeRepository.findByTitleContainsAndProject(search, pageable, true);
        }
        return noticeRepository.findByTitleContainsAndProject(search, pageable, false);
    }

    public Notice create(ProjectRequest projectRequest) {
        Notice createNotice = Notice.builder()
            .title(projectRequest.getTitle())
            .content(projectRequest.getContent())
            .project(true)
            .thumbnail(null)
            .build();
        Notice saveNotice = noticeRepository.save(createNotice);

        if (projectRequest.getFile().get(0).getSize() > 0) {
            fileService.FileStore(projectRequest.getFile(), saveNotice);
        }
        if (projectRequest.getThumbnail() != null) {
            Image image = imageService.storeImage(projectRequest.getThumbnail());
            createNotice.updateThumbnail(image);
            saveNotice = noticeRepository.save(createNotice);
        }
        return saveNotice;
    }

    @Transactional
    public Notice update(NoticeRequest noticeRequest, Long id) {
        Notice notice = noticeRepository.findById(id)
            .orElseThrow(() -> new ApplicationException(ErrorCode.NOTICE_NOT_FOUND));

        notice.update(Notice.builder()
            .title(noticeRequest.getTitle())
            .content(noticeRequest.getContent())
            .project(false)
            .thumbnail(null)
            .build());

        Notice updateNotice = noticeRepository.save(notice);

        if (noticeRequest.getFile().get(0).getSize() > 0) {
            fileService.deleteFiles(updateNotice.getNoticeNumber());
            fileService.FileStore(noticeRequest.getFile(), updateNotice);
        }
        return updateNotice;
    }

    @Transactional
    public Notice update(ProjectRequest projectRequest, Long id) {
        Notice notice = noticeRepository.findById(id)
            .orElseThrow(() -> new ApplicationException(ErrorCode.NOTICE_NOT_FOUND));
        Image updateThumbnail = null;

        if (projectRequest.getThumbnail().getSize() > 0) {
            imageService.deleteImage(notice.getThumbnail());
            updateThumbnail = imageService.storeImage(projectRequest.getThumbnail());
            notice.updateThumbnail(updateThumbnail);
        }

        Notice.NoticeBuilder builder = Notice.builder()
            .title(projectRequest.getTitle())
            .content(projectRequest.getContent())
            .thumbnail(null)
            .project(true);
        if (updateThumbnail != null) {
            builder.thumbnail(updateThumbnail);
        }

        notice.update(builder.build());

        Notice updateNotice = noticeRepository.save(notice);

        if (projectRequest.getFile().get(0).getSize() > 0) {
            fileService.deleteFiles(updateNotice.getNoticeNumber());
            fileService.FileStore(projectRequest.getFile(), updateNotice);
        }
        return updateNotice;
    }

    @Transactional
    public void delete(Long id, BoardType type) {
        Notice notice = noticeRepository.findById(id)
            .orElseThrow(() -> new ApplicationException(ErrorCode.NOTICE_NOT_FOUND));

        if (notice.getThumbnail() != null) {
            imageService.deleteImage(notice.getThumbnail());
        }
        fileService.deleteFiles(notice.getNoticeNumber());

        noticeRepository.delete(notice);
    }

    public List<SimpleProjectResponse> mainProjectImage() {
        Page<Notice> findProject = noticeRepository.findByProjectIsTrue(PageRequest.of(0, 20, Sort.by("updatedTime").descending()));
        return findProject.stream()
            .map(i -> new SimpleProjectResponse(i.getNoticeNumber(), i.getThumbnail().getImageNumber())).toList();

    }

    private static void parseId(String paringData, List<Long> idList) {
        Matcher matcher = pattern.matcher(paringData);

        while (matcher.find()) {
            idList.add(Long.parseLong(matcher.group(1)));
        }
    }

    public List<SimpleNoticeResponse> getRecentNotice() {
        List<Notice> findNotice = noticeRepository.findByProjectIsFalse(
            PageRequest.of(0, 5, Sort.by("updatedTime").descending())).getContent();
        return findNotice.stream()
            .map(i -> new SimpleNoticeResponse(i.getNoticeNumber(), i.getTitle(), i.getCreatedTime()))
            .toList();

    }

    @AllArgsConstructor
    @Getter
    static class SimpleProjectResponse {
        private Long id;
        private Long imageId;

    }

    @AllArgsConstructor
    @Getter
    static class SimpleNoticeResponse {
        private Long id;
        private String title;
        private LocalDateTime createDate;
    }
}
