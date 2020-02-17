package lxx.ligenote.controller;

import lxx.ligenote.dto.CommentCreateDTO;
import lxx.ligenote.dto.CommentDTO;
import lxx.ligenote.dto.ResultDTO;
import lxx.ligenote.enums.CommentTypeEnum;
import lxx.ligenote.exception.CustomizeErrorCode;
import lxx.ligenote.model.Comment;
import lxx.ligenote.model.User;
import lxx.ligenote.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ClassName:CommentController
 * Package:lxx.ligenote.controller
 * Description:
 *
 * @Date:2020/1/11 15:58
 * @Author:857251389@qq.com
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if(commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setType(commentCreateDTO.getType());
        comment.setContent(commentCreateDTO.getContent());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment,user);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id){
        List<CommentDTO> subComments = commentService.listCommentById(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(subComments);
    }
}
