package lxx.ligenote.dto;

import lombok.Data;
import lxx.ligenote.model.User;

/**
 * ClassName:CommentDTO
 * Package:lxx.ligenote.dto
 * Description:
 *
 * @Date:2020/2/10 13:56
 * @Author:857251389@qq.com
 */
@Data
public class CommentDTO {
    private Long id;

    private Long parentId;

    private Integer type;

    private Long commentator;

    private Long gmtCreate;

    private Long gmtModified;

    private Long likeCount;

    private String content;

    private User user;

    private Integer commentCount;

}
