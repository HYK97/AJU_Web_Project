package com.aju.web.persentation.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.aju.web.application.notice.NoticeService;

import lombok.RequiredArgsConstructor;

/**
 * packageName :  com.aju.web.persentation.main
 * fileName : MainController
 * author :  ddh96
 * date : 2023-04-26 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-26                ddh96             최초 생성
 */
@Controller
@RequiredArgsConstructor
public class MainController {

    private final NoticeService noticeService;

    @GetMapping({"/", "index"})
    public String index(Model model) {

        model.addAttribute("projectImage", noticeService.mainProjectImage());
        model.addAttribute("recentNotice", noticeService.getRecentNotice());
        return "index";
    }

    @GetMapping("/services")
    public String services() {
        return "services";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/greetings")
    public String greetings() {
        return "greetings";
    }

    @GetMapping("/history")
    public String history() {
        return "history";
    }

    @GetMapping("/organization")
    public String organization() {
        return "organization";
    }

    @GetMapping("/create-notice")
    public String createNotice() {
        return "create-notice";
    }

    @GetMapping("/create-project")
    public String createProjects() {
        return "create-project";
    }
    @GetMapping("/counsel")
    public String counsel() {
        return "counsel";
    }

}
