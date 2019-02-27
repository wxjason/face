package cn.wxj.common.bean;


import lombok.Data;

/**
 * @ClassName: ResultErrorMessage
 * @Package cn.wxj.yunshi.common.bean
 * @Description:
 * @Author wuxinjian
 * @Date 2018/11/12 10:29
 * @Version V1.0
 */
@Data
public class ResultErrorMessage {

    private Integer result;

    private String errorMessage;

    @Override
    public String toString() {
        return "ResultErrorMessage{" +
                "result=" + result +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
