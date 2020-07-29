package com.demo.first.controller;

import com.demo.first.bc.JBCrypt;
import com.demo.first.model.User;
import com.demo.first.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/register")
    public String register(HttpServletRequest request, Model model) {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user, Model model) {
        final String salt = BCrypt.gensalt(12);
        String newS = salt;
        user.setSalt(newS);
        String newPwd = JBCrypt.bcryptPwd(user.getPassword(), salt);
        user.setPassword(newPwd);
        List<User> list = userService.selectUserByName(user);
        if (list.size() == 0) {
            if (userService.insert(user) == 1) {
                model.addAttribute("status", 0);
            } else {
                model.addAttribute("status", 1);
            }
        } else {
            model.addAttribute("status", 2);
        }
        return "register";
    }

    @RequestMapping(value = "/login")
    public String loginPage(@ModelAttribute User user, Model model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user, Model model, HttpSession session) {

        User u = userService.login(user);
        if (u != null) {
//            u.setPassword("");

            String newPwd = JBCrypt.bcryptPwd(user.getPassword(), u.getSalt());
            if (Objects.equals(u.getPassword(), newPwd)) {
                session.setAttribute("user", u);
                model.addAttribute("status", "0");
                model.addAttribute("message", "登录成功");

            } else {


                model.addAttribute("status", "1");
                model.addAttribute("message", "用户名或密码错误");
                return "error";
            }
        } else {
            model.addAttribute("status", "1");
            model.addAttribute("message", "用户名或密码错误");
            return "error";
        }
        return "login";
    }

    @RequestMapping(value="/logout",method= RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/first/user/login";
    }
    @ResponseBody
    @RequestMapping(value="/delete",method= RequestMethod.DELETE)
    public Integer delete(HttpSession session, Model model){
        User u = (User) session.getAttribute("user");
        int result=0;
        if(u != null){
            result= userService.deleteByPrimaryKey(u.getId());
            session.setAttribute("user",null);
            System.out.println("Result:"+result);
        }
        return result;
    }

}
