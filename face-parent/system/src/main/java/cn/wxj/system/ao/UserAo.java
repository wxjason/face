package cn.wxj.system.ao;

import cn.wxj.common.bean.BaseAo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: UserAo
 * @Package cn.wxj.system.bean.ao
 * @Description:
 * @Author wuxinjian
 * @Date 2019/1/3 10:56
 * @Version V1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserAo extends BaseAo {

    private String userId;

    private String username;

    private String password;

    private String name;

    private String phone;

    private String email;

    private Integer status;

    private String roleId;

    @Override
    public String toString() {
        return "UserAo{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
