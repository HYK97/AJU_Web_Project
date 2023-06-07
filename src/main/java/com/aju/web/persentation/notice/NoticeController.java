package com.aju.web.persentation.notice;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aju.web.application.notice.NoticeService;
import com.aju.web.domain.notice.Notice;
import com.aju.web.infra.interceptor.LoginCheck;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * packageName :  com.aju.web.persentation.notice
 * fileName : NoticeController
 * author :  ddh96
 * date : 2023-04-30
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-30                ddh96             최초 생성
 */
@RequiredArgsConstructor
@RequestMapping("/notice")
@Controller
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/{id}")
    public String viewNotice(Model model, @PathVariable Long id, @RequestParam(defaultValue = "0") int page) {

        Notice notice = noticeService.getNotice(id, BoardType.NOTICE);

        NoticeDetailResponse noticeDetailResponse = NoticeDetailResponse.from(notice);
        model.addAttribute("notice", noticeDetailResponse);

        return "notice-detail";
    }

    @PostMapping("/create")
    @LoginCheck
    public String viewNotice(@Valid @ModelAttribute NoticeRequest noticeRequest, BindingResult bindingResult,
        RedirectAttributes redirectAttributes) {

        Notice notice = noticeService.create(noticeRequest);

        return "redirect:/notice/" + notice.getNoticeNumber();
    }

    @GetMapping("/update/{id}")
    @LoginCheck
    public String viewUpdateNotice(@PathVariable Long id, Model model) {

        Notice notice = noticeService.getNotice(id, BoardType.NOTICE);

        NoticeDetailResponse noticeDetailResponse = NoticeDetailResponse.from(notice);
        model.addAttribute("notice", noticeDetailResponse);
        return "update-notice";
    }

    @PostMapping("/update/{id}")
    @LoginCheck
    public String updateNotice(@PathVariable Long id, @Valid @ModelAttribute NoticeRequest noticeRequest) {
        Notice notice = noticeService.update(noticeRequest, id);
        return "redirect:/notice/" + notice.getNoticeNumber();
    }

    @GetMapping
    public String list(@RequestParam(defaultValue = "") String search, Model model,
        @RequestParam(defaultValue = "0") int page) {
        Page<Notice> paging = noticeService.getList(search, page, BoardType.NOTICE,null);
        model.addAttribute("paging", paging);
        model.addAttribute("search", search);
        return "notice";
    }
    @PostMapping("/{id}")
    @LoginCheck
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        noticeService.delete(id, BoardType.PROJECT);
        attributes.addFlashAttribute("message", "삭제되었습니다.");
        return "redirect:/";
    }


}
