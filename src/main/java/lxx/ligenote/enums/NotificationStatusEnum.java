package lxx.ligenote.enums;

/**
 * ClassName:NotificationStatusEnum
 * Package:lxx.ligenote.enums
 * Description:
 *
 * @Date:2020/2/14 14:52
 * @Author:857251389@qq.com
 */
public enum NotificationStatusEnum {
    UNREAD(0), READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
