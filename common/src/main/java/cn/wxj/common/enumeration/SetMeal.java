package cn.wxj.common.enumeration;

import java.util.Objects;

public enum SetMeal {

    /**
     * 不限
     */
    UNLIMITED(1, "不限"),

    /**
     * 每天次数
     */
    DAY_NUM(2, "每天次数");

    private Integer code;
    private String desc;

    SetMeal(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SetMeal setMeal(Integer code) {
        if (Objects.nonNull(code)) {
            for (SetMeal t : SetMeal.values()) {
                if (Objects.nonNull(t.code) && t.code.intValue() == code.intValue()) {
                    return t;
                }
            }
        }
        return null;
    }

    public static String desc (Integer code) {
        SetMeal setMeal = setMeal(code);
        return Objects.nonNull(setMeal) ? setMeal.getDesc() : "";
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
