package cn.wxj.system.service;

import cn.wxj.system.ao.RoleMenusAo;
import cn.wxj.system.entity.RoleMenu;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单角色关联表 服务类
 * </p>
 *
 * @author wxjason123
 * @since 2019-01-02
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    /**
     * 角色绑定菜单
     * @param roleMenusAo
     */
    void roleBindMenus(RoleMenusAo roleMenusAo);

    /**
     * 根据角色ID删除角色绑定的菜单
     * @param roleId
     */
    void deleteByRoleId(String roleId);

    /**
     * 根据角色ID查询角色绑定的菜单信息
     * @param roleId
     * @return List<RoleMenu>
     */
    List<RoleMenu> selectByRoleId(String roleId);
}
