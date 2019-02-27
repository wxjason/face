package cn.wxj.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import cn.wxj.common.constant.NumberConstants;
import cn.wxj.common.enumeration.ExceptionType;
import cn.wxj.common.exception.TceException;
import cn.wxj.common.util.CollectionUtils;
import cn.wxj.common.util.StringUtils;
import cn.wxj.system.ao.RoleMenusAo;
import cn.wxj.system.entity.Menu;
import cn.wxj.system.entity.RoleMenu;
import cn.wxj.system.mapper.RoleMenuMapper;
import cn.wxj.system.service.IMenuService;
import cn.wxj.system.service.IRoleMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.wxj.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单角色关联表 服务实现类
 * </p>
 *
 * @author wxjason123
 * @since 2019-01-02
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRoleService roleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void roleBindMenus(RoleMenusAo roleMenusAo) {
        final String roleId = roleMenusAo.getRoleId();
        roleService.checkAdmin(roleMenusAo.getRoleId());
        List<Integer> menuIds = roleMenusAo.getMenuIds();
        // 判断传入菜单ID是否有相同
        if (menuIds.size() != menuIds.stream().distinct().count()) {
            throw new TceException(ExceptionType.MENU_ID_HAS_SAME);
        }
        if (CollectionUtils.isEmpty(menuIds)) {
            this.deleteByRoleId(roleId);
            return;
        }
        List<Menu> menuList = menuService.selectByMenuIds(menuIds);
        // 判断传入菜单ID是否存在
        if (menuIds.size() != menuList.size()) {
            throw new TceException(ExceptionType.MENU_ID_NOT_EXISTS);
        }
        final List<RoleMenu> roleMenuList = new ArrayList<>(menuIds.size());
        menuIds.forEach(menuId -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuList.add(roleMenu);
        });
        this.deleteByRoleId(roleId);
        if (roleMenuList.size() > NumberConstants.ZERO) {
            this.insertBatch(roleMenuList, roleMenuList.size());
        }
    }

    @Override
    public void deleteByRoleId(String roleId) {
        if (StringUtils.isBlank(roleId)) {
            throw new TceException(ExceptionType.ROLE_ID_EMPTY);
        }
        roleService.checkAdmin(roleId);
        // 判断角色ID是否存在
        roleService.getRoleByRoleId(roleId);
        Wrapper<RoleMenu> wrapper = new EntityWrapper<>();
        wrapper.eq(RoleMenu.ROLE_ID, roleId);
        this.delete(wrapper);
    }

    @Override
    public List<RoleMenu> selectByRoleId(String roleId) {
        if (StringUtils.isBlank(roleId)) {
            throw new TceException(ExceptionType.ROLE_ID_EMPTY);
        }
        Wrapper<RoleMenu> wrapper = new EntityWrapper<>();
        wrapper.eq(RoleMenu.ROLE_ID, roleId);
        return this.selectList(wrapper);
    }
}
