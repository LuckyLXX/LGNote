package lxx.ligenote.enums;

/**
 * ClassName:CommentTypeEnum
 * Package:lxx.ligenote.enums
 * Description:
 *
 * @Date:2020/2/4 15:38
 * @Author:857251389@qq.com
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if(commentTypeEnum.getType()==type)
                return true;
        }
        return false;
    }

    public Integer getType() {
        return type;
    }
}
