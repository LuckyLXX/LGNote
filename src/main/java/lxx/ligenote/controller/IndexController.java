package lxx.ligenote.controller;

import lxx.ligenote.dto.PageDTO;
import lxx.ligenote.dto.QuestionDTO;
import lxx.ligenote.mapper.UserMapper;
import lxx.ligenote.model.User;
import lxx.ligenote.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:IndexController
 * Package:lxx.ligenote.controller
 * Description:
 *
 * @Date:2019/12/11 9:19
 * @Author:857251389@qq.com
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "5") Integer size) {

        PageDTO questionDTOList = questionService.list(page,size);
        model.addAttribute("questionsDTO", questionDTOList);
        return "index";
    }
}
