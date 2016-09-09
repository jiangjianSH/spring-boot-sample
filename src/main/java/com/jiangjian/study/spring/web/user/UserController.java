package com.jiangjian.study.spring.web.user;

import com.jiangjian.study.spring.domain.User;
import com.jiangjian.study.spring.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/add")
    @ResponseBody
    public String addUser() {
        User user = new User();
        user.setName("Alice");
        user.setAddress("Tokyo");
        user.setAge(10);
        userRepository.save(user);
        return "success";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "/user/list";
    }
}
