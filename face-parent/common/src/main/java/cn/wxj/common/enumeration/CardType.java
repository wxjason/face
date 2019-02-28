package cn.wxj.common.enumeration;

import java.util.Objects;

/**
 * @author wxjason
 */

public enum CardType {

    /**
     * 身份证
     */
    ID_CARD(1, "身份证");

    private Integer type;
    private String desc;

    CardType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static CardType cardType(Integer type) {
        if (Objects.nonNull(type)) {
            for (CardType t : CardType.values()) {
                if (Objects.nonNull(t.type) && t.type.intValue() == type.intValue()) {
                    return t;
                }
            }
        }
        return null;
    }

    public static String desc (Integer code) {
        CardType cardType = cardType(code);
        return Objects.nonNull(cardType) ? cardType.getDesc() : "";
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
