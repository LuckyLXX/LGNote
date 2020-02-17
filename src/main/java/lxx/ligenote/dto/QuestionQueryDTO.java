package lxx.ligenote.dto;

import lombok.Data;

/**
 * ClassName:QuestionQueryDTO
 * Package:lxx.ligenote.dto
 * Description:
 *
 * @Date:2020/2/16 16:19
 * @Author:857251389@qq.com
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private String sort;
    private Long time;
    private String tag;
    private Integer page;
    private Integer size;
}