package lxx.ligenote.exception;

/**
 * ClassName:CustomizeException
 * Package:lxx.ligenote.exception
 * Description:
 *
 * @Date:2019/12/29 15:49
 * @Author:857251389@qq.com
 */
public class CustomizeException extends RuntimeException{
    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode(){
        return code;
    }
}