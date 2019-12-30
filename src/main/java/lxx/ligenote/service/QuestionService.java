package lxx.ligenote.service;

import lxx.ligenote.dto.PageDTO;
import lxx.ligenote.dto.QuestionDTO;
import lxx.ligenote.exception.CustomizeErrorCode;
import lxx.ligenote.exception.CustomizeException;
import lxx.ligenote.mapper.QuestionMapper;
import lxx.ligenote.mapper.UserMapper;
import lxx.ligenote.model.Question;
import lxx.ligenote.model.QuestionExample;
import lxx.ligenote.model.User;
import org.apache.ibatis.session.RowBounds;
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
        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());
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
//        List<Question> questionList = questionMapper.list(offset, size);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
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
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(id);
        Integer totalCount = (int) questionMapper.countByExample(questionExample);
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
            QuestionExample example = new QuestionExample();
            example.createCriteria().andCreatorEqualTo(id);
            List<Question> questionList = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
            List<QuestionDTO> questionDTOList = new ArrayList<>();
            for (Question question : questionList) {
                User user = userMapper.selectByPrimaryKey(question.getCreator());
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
            pageDTO.setQuestions(questionDTOList);
        }
        return pageDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdte(Question question) {
        if (question.getId() == null) {
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        } else {
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            updateQuestion.setGmtModified(System.currentTimeMillis());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int update = questionMapper.updateByExample(updateQuestion, example);
            if (update != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }
}
