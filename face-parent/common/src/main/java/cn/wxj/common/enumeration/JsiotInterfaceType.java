package cn.wxj.common.enumeration;

import java.util.Objects;

/**
 * @author wxjason
 */

public enum JsiotInterfaceType {

    /**
     * OCR识别
     */
    OCR(1, "OCR识别"),
    /**
     * 活体检测
     */
    LIVENESS(2, "活体检测"),
    /**
     * 人证比对
     */
    COMPARE(3, "人证比对");

    private Integer type;
    private String desc;

    JsiotInterfaceType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static JsiotInterfaceType interfaceType(Integer type) {
        if (Objects.nonNull(type)) {
            for (JsiotInterfaceType t : JsiotInterfaceType.values()) {
                if (Objects.nonNull(t.type) && t.type.intValue() == type.intValue()) {
                    return t;
                }
            }
        }
        return null;
    }

    public static String desc (Integer code) {
        JsiotInterfaceType interfaceType = interfaceType(code);
        return Objects.nonNull(interfaceType) ? interfaceType.getDesc() : "";
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
