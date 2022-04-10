package cn.my.controller;

import cn.my.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/jsp")
public class UserController {
    //页面初始化
    @RequestMapping("toLogin")
    public String initLogin() {
        return "login";
    }

    //处理登录功能
    @RequestMapping("/login")
    public String login(User user, Model model, HttpSession session) {
        System.out.println(user.getName());
        if ("ZhangSan".equals(user.getName()) && "123456".equals(user.getPwd())) {
            session.setAttribute("user", user);
            return "redirect:main";
        }
        model.addAttribute("msg", "Fail");
        return "login";
    }

    @RequestMapping("/main")
    public String toMain() {return "main";}

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }
}
