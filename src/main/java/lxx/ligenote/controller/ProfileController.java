package lxx.ligenote.controller;

import lxx.ligenote.dto.PageDTO;
import lxx.ligenote.mapper.UserMapper;
import lxx.ligenote.model.User;
import lxx.ligenote.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:ProfileController
 * Package:lxx.ligenote.controller
 * Description:
 *
 * @Date:2019/12/21 16:46
 * @Author:857251389@qq.com
 */
@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "2") Integer size,
                          Model model,
                          HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的问题");
        }
        if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }
        PageDTO list = questionService.list(user.getId(), page, size);
        model.addAttribute("questionsDTO",list);
        return "profile";
    }
}
