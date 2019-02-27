package cn.wxj.system.ao;

import cn.wxj.common.bean.BaseAo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: LoginUser
 * @Package cn.wxj.system.ao
 * @Description:
 * @Author wuxinjian
 * @Date 2018/12/13 14:19
 * @Version V1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginUser extends BaseAo {

    private String username;

    private String password;

}
