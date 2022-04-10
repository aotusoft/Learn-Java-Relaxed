package cn.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Controller
//@RequestMapping("/jsp/")
public class IndexController {
    @RequestMapping("/jsp/login")
    public String login() {
        return "login.jsp";
    }

    @RequestMapping("/register")
    public String register() {
        return "register.jsp";
    }

    @RequestMapping(value = "/successGet", method = RequestMethod.GET, params = "username")
    public String successGet(@RequestParam String username, HttpSession session) {
        session.setAttribute("username",username);
        return "success";
    }
    @RequestMapping(value = "/successPost", method = RequestMethod.POST, params = "username")
    public String successPost(@RequestParam String username, HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        username = new String(username.getBytes(StandardCharsets.UTF_8),"UTF-8");
        username = new String(username.getBytes(StandardCharsets.UTF_8));
        session.setAttribute("username",username);
        return "success.jsp";
    }
    //转发
    @RequestMapping("/LoginTest")
    public String loginn() {
        return "hasLogin";
    }
    //重定向
    @RequestMapping("/hasLogin")
    public String reg() {
        return "redirect:hasRgister";
    }

    @RequestMapping("/isLogin")
    public String hasRgister() {
        return "register";
    }

}
