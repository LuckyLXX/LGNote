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
    QUESTION_NOT_FOUND("你的问题不在了，换一个试试？");
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
