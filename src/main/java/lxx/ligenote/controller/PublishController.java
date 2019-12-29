package lxx.ligenote.controller;

import lxx.ligenote.dto.QuestionDTO;
import lxx.ligenote.mapper.QuestionMapper;
import lxx.ligenote.mapper.UserMapper;
import lxx.ligenote.model.Question;
import lxx.ligenote.model.User;
import lxx.ligenote.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:PublishController
 * Package:lxx.ligenote.controller
 * Description:
 *
 * @Date:2019/12/15 20:50
 * @Author:857251389@qq.com
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(name = "title") String title,
                            @RequestParam(name = "description") String description,
                            @RequestParam(name = "tag") String tag,
                            @RequestParam(name = "id") Integer id,
                            HttpServletRequest request,
                            Model model) {

        User user = (User) request.getSession().getAttribute("user");
        if( user == null){
            model.addAttribute("error","用户未登录");
        }
        Question question = new Question();
        question.setCreator(user.getId());
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setId(id);
        questionService.createOrUpdte(question);
        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name="id") Integer id,
                       Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("title",questionDTO.getTitle());
        model.addAttribute("description",questionDTO.getDescription());
        model.addAttribute("tag",questionDTO.getTag());
        model.addAttribute("id",questionDTO.getId());
        return "publish";
    }
}
