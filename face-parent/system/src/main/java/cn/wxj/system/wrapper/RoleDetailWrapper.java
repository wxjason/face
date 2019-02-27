package cn.wxj.system.wrapper;

import cn.wxj.common.util.DateUtils;
import cn.wxj.common.wrapper.BaseWrapper;
import cn.wxj.system.entity.RoleMenu;
import cn.wxj.system.service.IRoleMenuService;
import cn.wxj.system.vo.RoleDetailVo;
import cn.wxj.system.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: ChannelListWrapper
 * @Package cn.wxj.operator.jsiot.admin.wrapper
 * @Description:
 * @Author wuxinjian
 * @Date 2018/12/25 9:33
 * @Version V1.0
 */
@Component
public class RoleDetailWrapper extends BaseWrapper<Role, RoleDetailVo> {

    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    protected RoleDetailVo warp(Role model) throws IOException {
        RoleDetailVo vo = new RoleDetailVo();
        vo.setRoleId(model.getId());
        vo.setRoleName(model.getName());
        vo.setRemark(model.getRemark());
        vo.setCreateTime(DateUtils.getDateTimeAsString(model.getCreateTime()));
        List<RoleMenu> roleMenuList = roleMenuService.selectByRoleId(model.getId());
        List<Integer> menuIds = roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        vo.setMenuIds(menuIds);
        return vo;
    }
}
