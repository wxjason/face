package cn.wxj.system.vo;

import cn.wxj.common.bean.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: RoleListVo
 * @Package cn.wxj.system.bean.vo
 * @Description:
 * @Author wuxinjian
 * @Date 2019/1/2 17:48
 * @Version V1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleListVo extends BaseVo {

    private String roleId;

    private String roleName;

    private String remark;

    private String createTime;

    @Override
    public String toString() {
        return "RoleListVo{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
