package cn.wxj.system.wrapper;

import cn.wxj.common.util.DateUtils;
import cn.wxj.common.wrapper.BaseWrapper;
import cn.wxj.system.vo.RoleListVo;
import cn.wxj.system.entity.Role;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName: ChannelListWrapper
 * @Package cn.wxj.operator.jsiot.admin.wrapper
 * @Description:
 * @Author wuxinjian
 * @Date 2018/12/25 9:33
 * @Version V1.0
 */
@Component
public class RoleListWrapper extends BaseWrapper<Role, RoleListVo> {

    @Override
    protected RoleListVo warp(Role model) throws IOException {
        RoleListVo vo = new RoleListVo();
        vo.setRoleId(model.getId());
        vo.setRoleName(model.getName());
        vo.setRemark(model.getRemark());
        vo.setCreateTime(DateUtils.getDateTimeAsString(model.getCreateTime()));
        return vo;
    }
}
