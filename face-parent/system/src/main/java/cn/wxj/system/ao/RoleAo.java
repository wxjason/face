package cn.wxj.system.ao;

import cn.wxj.common.bean.BaseAo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: RoleAo
 * @Package cn.wxj.system.bean.ao
 * @Description:
 * @Author wuxinjian
 * @Date 2019/1/2 17:47
 * @Version V1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleAo extends BaseAo {
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 角色名称
     */
    private String roleName;

    private String remark;

    private Object createTime;

    private Object menuIds;

    @Override
    public String toString() {
        return "RoleAo{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", menuIds=" + menuIds +
                '}';
    }
}
