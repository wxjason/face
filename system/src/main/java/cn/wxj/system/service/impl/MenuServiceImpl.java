package cn.wxj.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import cn.wxj.common.constant.NumberConstants;
import cn.wxj.common.constant.StringConstants;
import cn.wxj.common.enumeration.ExceptionType;
import cn.wxj.common.exception.TceException;
import cn.wxj.common.util.CollectionUtils;
import cn.wxj.common.util.HttpUtils;
import cn.wxj.common.util.RegexUtils;
import cn.wxj.common.util.StringUtils;
import cn.wxj.system.common.MenuNode;
import cn.wxj.system.entity.Menu;
import cn.wxj.system.entity.User;
import cn.wxj.system.enumeration.MenuType;
import cn.wxj.system.mapper.MenuMapper;
import cn.wxj.system.service.IMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.wxj.system.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author wxjason123
 * @since 2019-01-02
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private IUserService userService;

    @Override
    public Integer addMenu(Menu menu) {
        this.checkMenu(menu, true);
        this.insert(menu);
        return menu.getId();
    }

    @Override
    public void updateMenu(Menu menu) {
        this.checkMenu(menu, false);
        this.updateById(menu);
    }

    @Override
    public void checkMenu(Menu menu, boolean isAdd) {
        if (!isAdd) {
            if (Objects.isNull(menu.getId())) {
                throw new TceException(ExceptionType.MENU_ID_EMPTY);
            }
            if (menu.getId().equals(menu.getPid())) {
                throw new TceException(ExceptionType.MENU_PID_IS_SELF);
            }
        }
        if (Objects.nonNull(menu.getPid())) {
            Menu pMenu = this.selectById(menu.getPid());
            if (menu.getPid() != NumberConstants.ZERO && Objects.isNull(pMenu)) {
                throw new TceException(ExceptionType.MENU_PID_NOT_EXISTS);
            }
        }
        if (!RegexUtils.matchName(menu.getMenuName())) {
            if (isAdd || StringUtils.isNotEmpty(menu.getMenuName())) {
                throw new TceException(ExceptionType.MENU_NAME_ERROR);
            }
        }
        if (StringUtils.isNotBlank(menu.getMenuIcon()) && menu.getMenuIcon().length() > NumberConstants.ONE_HUNDRED) {
            throw new TceException(ExceptionType.MENU_ICON_LENGTH_LIMIT);
        }
        if (StringUtils.isNotBlank(menu.getMenuUrl()) && menu.getMenuUrl().length() > NumberConstants.ONE_HUNDRED) {
            throw new TceException(ExceptionType.MENU_URL_LENGTH_LIMIT);
        }
        MenuType menuType = MenuType.menuType(menu.getMenuType());
        boolean typeError = (isAdd || Objects.nonNull(menu.getMenuType())) && Objects.isNull(menuType);
        if (typeError) {
            throw new TceException(ExceptionType.MENU_TYPE_ERROR);
        }
        if (StringUtils.isNotBlank(menu.getRemark()) && menu.getRemark().length() > NumberConstants.ONE_HUNDRED) {
            throw new TceException(ExceptionType.MENU_REMARK_LENGTH_LIMIT);
        }

    }

    @Override
    public List<Menu> listByPage(Page<Menu> page, Integer menuType) {
        return this.baseMapper.selectListByPage(page, menuType);
    }

    @Override
    public List<Menu> list(Integer menuType) {
        return this.baseMapper.selectMenuList(menuType);
    }

    @Override
    public List<Menu> selectByMenuIds(List<Integer> menuIds) {
        Wrapper<Menu> wrapper = new EntityWrapper<>();
        wrapper.in(Menu.ID, menuIds);
        return this.selectList(wrapper);
    }

    @Override
    public void deleteMenuById(Integer menuId) {
        int row = this.baseMapper.deleteById(menuId);
        if (row == NumberConstants.ZERO) {
            throw new TceException(ExceptionType.MENU_ID_NOT_EXISTS);
        }
    }

    @Override
    public Menu getMenuById(Integer menuId) {
        Menu menu = this.selectById(menuId);
        if (Objects.isNull(menu)) {
            throw new TceException(ExceptionType.MENU_ID_NOT_EXISTS);
        }
        return menu;
    }

    @Override
    public List<MenuNode> tree(final Integer menuType) {
        if (Objects.nonNull(menuType)) {
            MenuType type = MenuType.menuType(menuType);
            if (Objects.isNull(type)) {
                throw new TceException(ExceptionType.MENU_TYPE_ERROR);
            }
        }
        User user = userService.getUserById(HttpUtils.getUserIdFromToken());
        final String roleId = user.getRoleId();
        List<MenuNode> menuNodeList = this.baseMapper.selectListByPid(NumberConstants.ZERO, menuType, roleId);
        if (CollectionUtils.isNotEmpty(menuNodeList)) {
            menuNodeList.forEach(node -> node.setChildren(selectChildren(node.getId(), menuType, roleId)));
        }
        return menuNodeList;
    }

    @Override
    public List<Menu> selectByPid(final Integer pid, Integer menuType, String roleId){
        List<MenuNode> menuNodeList = this.baseMapper.selectListByPid(NumberConstants.ZERO, menuType, roleId);
        List<Menu> menuList = new ArrayList<>(menuNodeList.size());
        menuNodeList.forEach(menuNode -> {
            Menu menu = new Menu();
            BeanUtils.copyProperties(menuNode, menu);
            menu.setPid(pid);
            menuList.add(menu);
        });
        return menuList;
    }

    @Override
    public Menu getMenuByTypeAndUrl(Integer menuType, String url) {
        return this.baseMapper.selectMenuByTypeAndUrl(menuType, url);
    }

    @Override
    public List<Menu> listHasUrl() {
        String roleId = null;
        if (StringUtils.isNotEmpty(HttpUtils.getUserIdFromToken())) {
            User user = userService.getUserById(HttpUtils.getUserIdFromToken());
            roleId = user.getRoleId();
        }
        return this.baseMapper.selectListHasUrl(MenuType.MENU.getType(), roleId);
    }

    @Override
    public List<Menu> childrenButton(String url) {
        User user = userService.getUserById(HttpUtils.getUserIdFromToken());
        final String roleId = user.getRoleId();
        return this.baseMapper.selectChildren(url, MenuType.BUTTON.getType(), roleId);
    }

    @Override
    public List<MenuNode> menuTree() {
        List<MenuNode> menuNodeList = this.tree(MenuType.MENU.getType());
        return this.changeUrl(menuNodeList);
    }

    private List<MenuNode> changeUrl(List<MenuNode> menuNodeList) {
        menuNodeList.forEach(menuNode -> {
            if (StringUtils.isNotBlank(menuNode.getMenuUrl())) {
                String menuUrlTemp = menuNode.getMenuUrl().substring(NumberConstants.ONE);
                ///
//                menuUrlTemp = menuUrlTemp.substring(menuUrlTemp.indexOf(StringConstants.FORWARD_SLASH) + NumberConstants.ONE);
                menuUrlTemp = menuUrlTemp.substring(menuUrlTemp.indexOf(StringConstants.FORWARD_SLASH));
                menuNode.setMenuUrl(menuUrlTemp);
            }
            menuNode.setChildren(this.changeUrl(menuNode.getChildren()));
        });
        return menuNodeList;
    }

    private List<MenuNode> selectChildren (final Integer pid, Integer menuType, String roleId) {
        List<MenuNode> menuNodeList = this.baseMapper.selectListByPid(pid, menuType, roleId);
        menuNodeList.forEach(menuNode -> {
            menuNode.setChildren(selectChildren(menuNode.getId(), menuType, roleId));
        });
        return menuNodeList;
    }
}
