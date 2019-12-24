package lxx.ligenote.service;

import lxx.ligenote.dto.PageDTO;
import lxx.ligenote.dto.QuestionDTO;
import lxx.ligenote.mapper.QuestionMapper;
import lxx.ligenote.mapper.UserMapper;
import lxx.ligenote.model.Question;
import lxx.ligenote.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:QuestionService
 * Package:lxx.ligenote.service
 * Description:
 *
 * @Date:2019/12/17 17:15
 * @Author:857251389@qq.com
 */
@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;


    public PageDTO list(Integer page, Integer size) {
        Integer totalPage;
        PageDTO pageDTO = new PageDTO();
        Integer totalCount = questionMapper.count();
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1)
            page = 1;
        if (page > totalPage)
            page = totalPage;
        pageDTO.setPagination(totalPage, page, size);

        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pageDTO.setQuestions(questionDTOList);
        return pageDTO;

    }

    public PageDTO list(Integer id, Integer page, Integer size) {
        Integer totalPage;
        PageDTO pageDTO = new PageDTO();
        Integer totalCount = questionMapper.countByUserId(id);
        if (totalCount == 0) {
            pageDTO.setQuestions(null);
        } else {
            if (totalCount % size == 0) {
                totalPage = totalCount / size;
            } else {
                totalPage = totalCount / size + 1;
            }
            if (page < 1)
                page = 1;
            if (page > totalPage)
                page = totalPage;
            pageDTO.setPagination(totalPage, page, size);
            Integer offset = size * (page - 1);
            List<Question> questionList = questionMapper.listByUserId(id, offset, size);
            List<QuestionDTO> questionDTOList = new ArrayList<>();
            for (Question question : questionList) {
                User user = userMapper.findById(question.getCreator());
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
            pageDTO.setQuestions(questionDTOList);
        }
        return pageDTO;
    }
}
