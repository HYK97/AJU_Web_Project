package com.aju.web.persentation.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
public class MainController {

    @GetMapping({"/", "index"})
    public String index() {
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

}
