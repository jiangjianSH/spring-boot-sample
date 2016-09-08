package com.jiangjian.study.spring.web.user;

import com.jiangjian.study.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JdbcOperations jdbcOperations;

    @RequestMapping("/add")
    @ResponseBody
    public String addUser() {
        jdbcOperations.update("insert into user (name, age, address) values(?, ?, ?)", "fangjuan", 25, "池州");
        return "success";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<User>  users = jdbcOperations.query("select name, age, address from user", (rs, rowNum) -> {
            User user = new User();
            user.setName(rs.getString("name"));
            user.setAddress(rs.getString("address"));
            user.setAge(rs.getInt("age"));
            return user;
        }, null);
        model.addAttribute("users", users);
        return "/user/list";
    }
}
