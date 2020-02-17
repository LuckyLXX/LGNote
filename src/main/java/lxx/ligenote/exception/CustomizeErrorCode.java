package lxx.ligenote.exception;

/**
 * ClassName:CustomizeErrorCode
 * Package:lxx.ligenote.exception
 * Description:
 *
 * @Date:2019/12/29 16:06
 * @Author:857251389@qq.com
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"你的问题不在了，换一个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中回复对象"),
    NO_LOGIN(2003,"用户未登录，请登录后重试"),
    SYSTEM_ERROR(2004,"服务器抽风了，请稍后再试"),
    TYPE_NOT_EXIST(2005,"评论类型不存在"),
    COMMENT_NOT_FOUND(2006,"评论不存在"),
    CONTENT_EMPTY(2007,"评论不能为空"),
    READ_NOTIFICATION_FAIL(2008, "兄弟你这是读别人的信息呢？"),
    NOTIFICATION_NOT_FOUND(2009, "消息莫非是不翼而飞了？")
    ;

    private Integer code;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
