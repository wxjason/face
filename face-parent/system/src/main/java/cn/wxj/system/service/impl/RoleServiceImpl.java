package cn.wxj.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import cn.wxj.common.constant.NumberConstants;
import cn.wxj.common.enumeration.ExceptionType;
import cn.wxj.common.exception.TceException;
import cn.wxj.common.util.*;
import cn.wxj.system.ao.RoleAo;
import cn.wxj.system.common.Constants;
import cn.wxj.system.entity.Role;
import cn.wxj.system.entity.User;
import cn.wxj.system.mapper.RoleMapper;
import cn.wxj.system.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.wxj.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author wxjason123
 * @since 2019-01-02
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IUserService userService;

    @Override
    public List<Role> listByPage(Page<Role> page, RoleAo roleAo) {
        return this.baseMapper.findListByPage(page, roleAo);
    }

    @Override
    public List<Role> list(RoleAo roleAo) {
        return this.baseMapper.findList(roleAo);
    }

    @Override
    public String addRole(RoleAo roleAo) {
        roleAo.setRoleId(null);
        this.checkRole(roleAo, true);
        Role role = new Role();
        role.setId(roleAo.getRoleId());
        role.setName(roleAo.getRoleName());
        role.setRemark(roleAo.getRemark());
        role.setCreateUserId(HttpUtils.getUserIdFromToken());
        role.setCreateTime(DateUtils.localDateTime());
        this.insert(role);
        return role.getId();
    }

    @Override
    public void updateRole(RoleAo roleAo) {
        this.checkAdmin(roleAo.getRoleId());
        this.checkRole(roleAo, false);
        Role role = new Role();
        role.setId(roleAo.getRoleId());
        role.setName(roleAo.getRoleName());
        role.setRemark(roleAo.getRemark());
        role.setUpdateUserId(HttpUtils.getUserIdFromToken());
        role.setUpdateTime(DateUtils.localDateTime());
        int row = this.baseMapper.updateById(role);
        if (row == NumberConstants.ZERO) {
            throw new TceException(ExceptionType.ROLE_ID_NOT_EXISTS);
        }
    }

    @Override
    public void checkRole(RoleAo roleAo, boolean isAdd) {
        if (!isAdd && StringUtils.isBlank(roleAo.getRoleId())) {
            throw new TceException(ExceptionType.ROLE_ID_EMPTY);
        }
        if (StringUtils.isBlank(roleAo.getRoleName())) {
            throw new TceException(ExceptionType.ROLE_NAME_EMPTY);
        }
        if (!RegexUtils.matchName(roleAo.getRoleName())) {
            throw new TceException(ExceptionType.ROLE_NAME_ERROR);
        }
        Role role = this.getRoleByRoleName(roleAo.getRoleName());
        if (Objects.nonNull(role) && !role.getId().equals(roleAo.getRoleId())) {
            throw new TceException(ExceptionType.ROLE_NAME_IS_EXISTS);
        }
        if (Objects.nonNull(roleAo.getRemark()) && roleAo.getRemark().length() > NumberConstants.ONE_HUNDRED) {
            throw new TceException(ExceptionType.ROLE_REMARK_LENGTH_LIMIT);
        }
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        Wrapper<Role> wrapper = new EntityWrapper<>();
        wrapper.eq(Role.NAME, roleName);
        return this.selectOne(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(String roleId) {
        if (StringUtils.isEmpty(roleId)) {
            throw new TceException(ExceptionType.ROLE_ID_EMPTY);
        }
        this.checkAdmin(roleId);
        List<User> userList = userService.selectByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(userList)) {
            throw new TceException(ExceptionType.ROLE_HAS_USERS);
        }
        int row = this.baseMapper.deleteById(roleId);
        if (row == NumberConstants.ZERO) {
            throw new TceException(ExceptionType.ROLE_ID_NOT_EXISTS);
        }
    }

    @Override
    public Role getRoleByRoleId(String roleId) {
        Role role = this.selectById(roleId);
        if (Objects.isNull(role)) {
            throw new TceException(ExceptionType.ROLE_ID_NOT_EXISTS);
        }
        return role;
    }

    @Override
    public List<Role> selectListByRoleIds(List<String> roleIds) {
        Wrapper<Role> wrapper = new EntityWrapper<>();
        wrapper.in(Role.ID, roleIds);
        return this.selectList(wrapper);
    }

    @Override
    public void checkAdmin(String roleId) {
        if (Constants.ADMIN_ROLE_ID.equals(roleId)) {
            throw new TceException(ExceptionType.ROLE_ADMIN_NOT_UPDATE);
        }
    }
}
