package cn.wxj.system.ao;

import cn.wxj.common.bean.BaseAo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: ChangePasswordAo
 * @Package cn.wxj.system.bean.ao
 * @Description:
 * @Author wuxinjian
 * @Date 2019/1/3 14:20
 * @Version V1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChangePasswordAo extends BaseAo {

    private String oldPassword;

    private String newPassword;

    private String rePassword;

    @Override
    public String toString() {
        return "ChangePasswordAo{" +
                "oldPassword='" + oldPassword + '\'' +
                ", rePassword='" + rePassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
