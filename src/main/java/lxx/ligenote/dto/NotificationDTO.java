package lxx.ligenote.dto;

import lombok.Data;

/**
 * ClassName:NotificationDTO
 * Package:lxx.ligenote.dto
 * Description:
 *
 * @Date:2020/2/14 15:22
 * @Author:857251389@qq.com
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerId;
    private String typeName;
    private Integer type;
}