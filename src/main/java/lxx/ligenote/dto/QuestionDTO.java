package lxx.ligenote.dto;

import lombok.Data;
import lxx.ligenote.model.User;

/**
 * ClassName:QuestionDTO
 * Package:lxx.ligenote.dto
 * Description:
 *
 * @Date:2019/12/17 17:05
 * @Author:857251389@qq.com
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;

}
