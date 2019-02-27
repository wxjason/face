package cn.wxj.common.bean;

/**
 * @ClassName: OperationLogAo
 * @Package com.tce.system.ao
 * @Description:
 * @Author wuxinjian
 * @Date 2019/1/10 17:52
 * @Version V1.0
 */
public class OperationLogAddAo extends BaseAo {

    /**
     * 操作用户ID
     */
    private String userId;

    /**
     * 操作IP
     */
    private String remoteIp;

    /**
     * 访问路径
     */
    private String uri;
    /**
     * 结果状态码
     */
    private Integer resultCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        return "OperationLogAddAo{" +
                "userId='" + userId + '\'' +
                ", remoteIp='" + remoteIp + '\'' +
                ", uri='" + uri + '\'' +
                ", resultCode=" + resultCode +
                '}';
    }
}
