package lxx.ligenote.dto;

import lombok.Data;



/**
 * ClassName:CommentCreateDTO
 * Package:lxx.ligenote.dto
 * Description:
 *
 * @Date:2020/1/11 16:16
 * @Author:857251389@qq.com
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private Integer type;
    private String content;
}
