package lxx.ligenote.controller;

import lxx.ligenote.dto.CommentDTO;
import lxx.ligenote.dto.QuestionDTO;
import lxx.ligenote.enums.CommentTypeEnum;
import lxx.ligenote.service.CommentService;
import lxx.ligenote.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


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

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        List<CommentDTO> comments = commentService.listCommentById(id, CommentTypeEnum.QUESTION);
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        model.addAttribute("relatedQuestions", relatedQuestions);

        return "question";
    }
}
