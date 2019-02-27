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
public class OperationLogAo extends BaseAo {

    private String name;

    private String username;

    private String userId;

    private Integer result;

    private Integer menuId;

    private String startTime;

    private String endTime;

    @Override
    public String toString() {
        return "OperationLogAo{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                ", result=" + result +
                ", menuId=" + menuId +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
