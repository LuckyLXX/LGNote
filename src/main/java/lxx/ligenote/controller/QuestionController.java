package lxx.ligenote.controller;

import lxx.ligenote.dto.QuestionDTO;
import lxx.ligenote.model.Question;
import lxx.ligenote.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



/**
 * ClassName:QuestionController
 * Package:lxx.ligenote.controller
 * Description:
 *
 * @Date:2019/12/24 10:47
 * @Author:857251389@qq.com
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("question",questionDTO);

        return "question";
    }
}
