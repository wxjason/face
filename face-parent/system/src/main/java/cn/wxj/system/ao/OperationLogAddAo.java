package cn.wxj.system.ao;

import cn.wxj.common.bean.BaseAo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: OperationLogAo
 * @Package cn.wxj.system.ao
 * @Description:
 * @Author wuxinjian
 * @Date 2019/1/10 17:52
 * @Version V1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
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
