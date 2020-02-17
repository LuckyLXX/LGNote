package lxx.ligenote.service;

import lxx.ligenote.dto.PageDTO;
import lxx.ligenote.dto.QuestionDTO;
import lxx.ligenote.dto.QuestionQueryDTO;
import lxx.ligenote.exception.CustomizeErrorCode;
import lxx.ligenote.exception.CustomizeException;
import lxx.ligenote.mapper.QuestionExtMapper;
import lxx.ligenote.mapper.QuestionMapper;
import lxx.ligenote.mapper.UserMapper;
import lxx.ligenote.model.Question;
import lxx.ligenote.model.QuestionExample;
import lxx.ligenote.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private QuestionExtMapper questionExtMapper;


    public PageDTO list(String search,Integer page, Integer size) {

        if (StringUtils.isNotBlank(search)) {
            String[] tags = StringUtils.split(search, " ");
            search = Arrays
                    .stream(tags)
                    .filter(StringUtils::isNotBlank)
                    .map(t -> t.replace("+", "").replace("*", "").replace("?", ""))
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.joining("|"));
        }


        Integer totalPage;
        PageDTO pageDTO = new PageDTO();

        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
        questionQueryDTO.setSearch(search);


        Integer totalCount = questionExtMapper.countBySearch(questionQueryDTO);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1)
            page = 1;
        if (page > totalPage)
            page = totalPage;
        pageDTO.setPagination(totalPage, page);

        Integer offset = size * (page - 1);
//        List<Question> questionList = questionMapper.list(offset, size);


        questionQueryDTO.setSize(size);
        questionQueryDTO.setPage(offset);
        List<Question> questions = questionExtMapper.selectBySearch(questionQueryDTO);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pageDTO.setData(questionDTOList);
        return pageDTO;

    }

    public PageDTO list(Long id, Integer page, Integer size) {
        Integer totalPage;
        PageDTO pageDTO = new PageDTO();
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(id);
        Integer totalCount = (int) questionMapper.countByExample(questionExample);
        if (totalCount == 0) {
            pageDTO.setData(null);
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
            pageDTO.setPagination(totalPage, page);
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
            pageDTO.setData(questionDTOList);
        }
        return pageDTO;
    }

    public QuestionDTO getById(Long id) {
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
            question.setCommentCount(0);
            question.setViewCount(0);
            question.setLikeCount(0);
            questionMapper.insert(question);
        } else {
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            updateQuestion.setGmtCreate(question.getGmtCreate());
            updateQuestion.setGmtModified(System.currentTimeMillis());
//            updateQuestion.setCreator(question.getCreator());
//            updateQuestion.setLikeCount(question.getLikeCount());
//            updateQuestion.setViewCount(question.getViewCount());
//            updateQuestion.setCommentCount(question.getCommentCount());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int update = questionMapper.updateByExampleSelective(updateQuestion, example);
            if (update != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }

    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        if (StringUtils.isBlank(queryDTO.getTag())) {
            return new ArrayList<>();
        }
        String regexpTag = queryDTO.getTag().replace(",", "|");
        Question question = new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexpTag);

        List<Question> questions = questionExtMapper.selectRelated(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }
}
