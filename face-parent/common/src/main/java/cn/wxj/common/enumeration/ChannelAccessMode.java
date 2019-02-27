package cn.wxj.common.enumeration;

import java.util.Objects;

/**
 * @author wxjason
 */

public enum ChannelAccessMode {
    /**
     * H5方式
     */
    H5(1, "H5"),
    /**
     * 库中无此号
     */
    API(2, "API");

    private Integer code;
    private String desc;

    ChannelAccessMode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ChannelAccessMode code(Integer code) {
        if (Objects.nonNull(code)) {
            for (ChannelAccessMode t : ChannelAccessMode.values()) {
                if (Objects.nonNull(t.code) && t.code.equals(code)) {
                    return t;
                }
            }
        }
        return null;
    }

    public static String desc (Integer code) {
        ChannelAccessMode specialCrowdType = code(code);
        return Objects.nonNull(specialCrowdType) ? specialCrowdType.getDesc() : "";
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
