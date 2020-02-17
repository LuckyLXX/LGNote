package lxx.ligenote.dto;

import lombok.Data;

import java.util.List;

/**
 * ClassName:TagDTO
 * Package:lxx.ligenote.dto
 * Description:
 *
 * @Date:2020/2/14 9:31
 * @Author:857251389@qq.com
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
