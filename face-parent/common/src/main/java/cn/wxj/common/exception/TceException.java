package cn.wxj.common.exception;


import cn.wxj.common.enumeration.ExceptionType;

/**
 * 自定义的异常
 *
 * @author WangJinbo
 * @Date 2017/12/28 下午10:32
 */
public class TceException extends RuntimeException {

    private Integer code;

    private String message;

    private ExceptionType exceptionEnum;

    public TceException(ExceptionType exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
        this.exceptionEnum = exceptionEnum;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExceptionType getExceptionEnum() {
        return exceptionEnum;
    }

    public void setExceptionEnum(ExceptionType exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }
}
