package cn.wxj.common.enumeration;

import java.util.Objects;

/**
 * @author wxjason
 */

public enum SexType {

    /**
     * 男
     */
    MAN(0, "男"),

    /**
     * 女
     */
    WOMAN(1, "女"),

    /**
     * 未知
     */
    UNKNOWN(2,"未知");

    private Integer code;
    private String desc;

    SexType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SexType sexType(Integer code) {
        if (Objects.nonNull(code)) {
            for (SexType t : SexType.values()) {
                if (Objects.nonNull(t.code) && t.code.intValue() == code.intValue()) {
                    return t;
                }
            }
        }
        return UNKNOWN;
    }

    public static String desc (Integer code) {
        return sexType(code).getDesc();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
