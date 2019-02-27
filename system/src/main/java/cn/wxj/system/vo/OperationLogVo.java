package cn.wxj.system.vo;

import cn.wxj.common.bean.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @ClassName: UserVo
 * @Package cn.wxj.system.bean.vo
 * @Description:
 * @Author wuxinjian
 * @Date 2019/1/3 10:59
 * @Version V1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OperationLogVo extends BaseVo {
    /**
     * 操作人姓名
     */
    private String name;
    /**
     * 操作人用户名
     */
    private String username;

    /**
     * 操作人IP地址
     */
    private String remoteIp;
    /**
     * 操作人手机号
     */
    private String phone;
    /**
     * 操作人邮箱
     */
    private String email;
    /**
     * 操作菜单
     */
    private String menu;

    /**
     * 操作按钮
     */
    private String button;

    /**
     * 结果
     */
    private Integer result;

    /**
     * 创建时间
     */
    private String createTime;

    @Override
    public String toString() {
        return "OperationLogVo{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", remoteIp='" + remoteIp + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", menu='" + menu + '\'' +
                ", button='" + button + '\'' +
                ", result=" + result +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
