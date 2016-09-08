package com.jiangjian.study.spring.web.article;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @RequestMapping("/")
    public String index() {
        return "article/index";
    }

    @RequestMapping("/detail")
    @ResponseBody
    public String detail() {
        return "detail";
    }
    @RequestMapping("/about")
    @ResponseBody
    public String about() {
        return "this is about page";
    }
}
