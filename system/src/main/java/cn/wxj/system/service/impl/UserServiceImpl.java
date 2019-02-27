package cn.wxj.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import cn.wxj.common.constant.NumberConstants;
import cn.wxj.common.enumeration.Delete;
import cn.wxj.common.enumeration.ExceptionType;
import cn.wxj.common.enumeration.Status;
import cn.wxj.common.exception.TceException;
import cn.wxj.common.util.*;
import cn.wxj.system.ao.ChangePasswordAo;
import cn.wxj.system.ao.LoginUser;
import cn.wxj.system.ao.UserAo;
import cn.wxj.system.common.Constants;
import cn.wxj.system.entity.User;
import cn.wxj.system.mapper.UserMapper;
import cn.wxj.system.service.IRoleService;
import cn.wxj.system.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wxjason123
 * @since 2018-12-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final int RESET_PASWORD_LENGTH = 6;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private IRoleService roleService;

    @Override
    public User getByUsername(String username) {
        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq(User.USERNAME, username)
                .eq(User.DEL, Delete.NORMOL.getCode());
        return this.selectOne(wrapper);
    }

    @Override
    public void login(LoginUser loginUser) {
        if (StringUtils.isEmpty(loginUser.getUsername())) {
            throw new TceException(ExceptionType.LOGIN_USERNAME_EMPTY);
        }
        if (StringUtils.isEmpty(loginUser.getPassword())) {
            throw new TceException(ExceptionType.LOGIN_PASSWORD_EMPTY);
        }
        User user = this.getByUsername(loginUser.getUsername());
        if (Objects.isNull(user)) {
            throw new TceException(ExceptionType.LOGIN_USERNAME_NOT_EXISTS);
        }
        if (!bCryptPasswordEncoder.matches(loginUser.getPassword(), user.getPassword())) {
            throw new TceException(ExceptionType.LOGIN_PASSWORD_ERROR);
        }
    }

    @Override
    public List<User> listByPage(Page<User> page, UserAo userAo) {
        return this.baseMapper.selectListByPage(page, userAo);
    }

    @Override
    public List<User> selectByRoleId(String roleId) {
        if (StringUtils.isEmpty(roleId)) {
            throw new TceException(ExceptionType.ROLE_ID_EMPTY);
        }
        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper.ne(User.ID, Constants.ADMIN_USER_ID);
        wrapper.eq(User.ROLE_ID, roleId);
        return this.selectList(wrapper);
    }

    @Override
    public List<User> list(UserAo userAo) {
        return this.baseMapper.selectUserList(userAo);
    }

    @Override
    public User getUserById(String userId){
        if (!Constants.ADMIN_USER_ID.equals(HttpUtils.getUserIdFromToken()) && Constants.ADMIN_USER_ID.equals(userId)) {
            throw new TceException(ExceptionType.USER_ADMIN_NOT_UPDATE);
        }
        if (StringUtils.isBlank(userId)) {
            throw new TceException(ExceptionType.USER_ID_EMPTY);
        }
        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq(User.ID, userId)
                .eq(User.DEL, Delete.NORMOL.getCode());
        User user = this.selectOne(wrapper);
        if (Objects.isNull(user)) {
            throw new TceException(ExceptionType.USER_ID_NOT_EXISTS);
        }
        return user;
    }

    @Override
    public String addUserAo(UserAo userAo) {
        User user = new User();
        BeanUtils.copyProperties(userAo, user);
        user.setId(null);
        this.checkAddUser(user);
        this.addUser(user);
        return user.getId();
    }

    @Override
    public void updateUserAo(UserAo userAo) {
        User user = new User();
        BeanUtils.copyProperties(userAo, user);
        user.setId(userAo.getUserId());
        this.checkUpdateUser(user);
        this.updateUser(user);
    }

    @Override
    public void enableUser(String userId) {
        User user = this.getUserById(userId);
        user.setStatus(Status.ENABLE.getStatus());
        this.updateUser(user);
    }

    @Override
    public void disableUser(String userId) {
        User user = this.getUserById(userId);
        user.setStatus(Status.DISABLE.getStatus());
        if (userId.equals(HttpUtils.getUserIdFromToken())) {
            throw new TceException(ExceptionType.USER_NOT_DISABLE_SELF);
        }
        this.updateUser(user);
    }

    @Override
    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ENABLE.getStatus());
        user.setDel(Delete.NORMOL.getCode());
        user.setCreateTime(DateUtils.localDateTime());
        user.setCreateUserId(HttpUtils.getUserIdFromToken());
        this.insert(user);
    }

    @Override
    public void updateUser(User user) {
        this.checkAdmin(user.getId());
        user.setUpdateTime(DateUtils.localDateTime());
        user.setUpdateUserId(HttpUtils.getUserIdFromToken());
        int row = this.baseMapper.updateById(user);
        if (row == NumberConstants.ZERO) {
            throw new TceException(ExceptionType.USER_ID_NOT_EXISTS);
        }
    }

    @Override
    public void deleteUserById(String userId) {
        User user = this.getUserById(userId);
        Status status = Status.status(user.getStatus());
        if (userId.equals(HttpUtils.getUserIdFromToken())) {
            throw new TceException(ExceptionType.USER_NOT_DELETE_SELF);
        }
        if (Status.ENABLE.equals(status)) {
            throw new TceException(ExceptionType.USER_ENABLED_TO_DELETE);
        }
        user.setDel(Delete.DELETE.getCode());
        this.updateUser(user);
    }

    @Override
    public void checkAddUser(User user) {
        this.checkUsername(user.getUsername());
        this.checkPassword(user.getPassword());
        this.checkName(user.getName());
        this.checkPhone(user.getPhone(), user.getId());
        this.checkEmail(user.getEmail(), user.getId());
        this.checkRoleId(user.getRoleId());
    }

    @Override
    public void checkUpdateUser(User user) {
        this.checkAdmin(user.getId());
        user.setUsername(null);
        user.setPassword(null);
        if (Objects.nonNull(user.getName())) {
            this.checkName(user.getName());
        }
        if (Objects.nonNull(user.getPhone())) {
            this.checkPhone(user.getPhone(), user.getId());
        }
        this.checkEmail(user.getEmail(), user.getId());
        if (StringUtils.isNotEmpty(user.getRoleId())) {
            this.checkRoleId(user.getRoleId());
        }
    }

    @Override
    public void checkAdmin(String userId) {
        if (Constants.ADMIN_USER_ID.equals(userId) && !Constants.ADMIN_USER_ID.equals(HttpUtils.getUserIdFromToken())) {
            throw new TceException(ExceptionType.USER_ADMIN_NOT_UPDATE);
        }
    }

    @Override
    public void checkRoleId(String roleId) {
        if (StringUtils.isEmpty(roleId)) {
            throw new TceException(ExceptionType.ROLE_ID_EMPTY);
        }
        roleService.getRoleByRoleId(roleId);
    }

    @Override
    public void changePassword(ChangePasswordAo changePasswordAo) {
        User user = this.getUserById(HttpUtils.getUserIdFromToken());
        if (StringUtils.isEmpty(changePasswordAo.getOldPassword())) {
            throw new TceException(ExceptionType.USER_OLD_PASSWORD_EMPTY);
        }
        try {
            bCryptPasswordEncoder.matches(changePasswordAo.getOldPassword(), user.getPassword());
        } catch (Exception e) {
            throw new TceException(ExceptionType.USER_OLD_PASSWORD_ERROR);
        }
        if (StringUtils.isEmpty(changePasswordAo.getNewPassword())) {
            throw new TceException(ExceptionType.USER_NEW_PASSWORD_EMPTY);
        }
        if (!changePasswordAo.getNewPassword().equals(changePasswordAo.getRePassword())) {
            throw new TceException(ExceptionType.USER_NEW_PASSWORD_NOT_SAME);
        }
        this.checkPassword(changePasswordAo.getNewPassword());
        user.setPassword(bCryptPasswordEncoder.encode(changePasswordAo.getNewPassword()));
        user.setUpdateUserId(user.getId());
        user.setUpdateTime(DateUtils.localDateTime());
        this.updateById(user);
    }

    @Override
    public String resetPassword(String userId) {
        this.checkAdmin(userId);
        User user = this.getUserById(userId);
        String newPassword = UUIDUtils.random(RESET_PASWORD_LENGTH);
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        user.setUpdateUserId(HttpUtils.getUserIdFromToken());
        user.setUpdateTime(DateUtils.localDateTime());
        this.updateById(user);
        return newPassword;
    }

    private void checkUsername (String username) {
        if (!RegexUtils.matchCode(username)) {
            throw new TceException(ExceptionType.USER_USERNAME_ERROR);
        }
        User userN = this.getByUsername(username);
        if (Objects.nonNull(userN)) {
            throw new TceException(ExceptionType.USER_USERNAME_EXISTS);
        }
    }

    private void checkPassword (String password) {
        if (!RegexUtils.matchCode(password)) {
            throw new TceException(ExceptionType.USER_PASSWORD_ERROR);
        }
    }

    private void checkName (String name) {
        if (!RegexUtils.matchName(name)) {
            throw new TceException(ExceptionType.USER_NAME_ERROR);
        }
    }

    private void checkPhone (String phone, String userId) {
        if (!RegexUtils.matchPhone(phone)) {
            throw new TceException(ExceptionType.USER_PHONE_ERROR);
        }
        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq(User.PHONE, phone).eq(User.DEL, Delete.NORMOL.getCode());
        User user = this.selectOne(wrapper);
        if (Objects.nonNull(user) && !user.getId().equals(userId)) {
            throw new TceException(ExceptionType.USER_PHONE_EXISTS);
        }
    }

    private void checkEmail (String email, String userId) {
        if (StringUtils.isNotEmpty(email) && !RegexUtils.matchEmail(email)) {
            throw new TceException(ExceptionType.USER_EMAIL_ERROR);
        }
        if (StringUtils.isEmpty(email)) {
            return;
        }
        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq(User.EMAIL, email).eq(User.DEL, Delete.NORMOL.getCode());
        User user = this.selectOne(wrapper);
        if (Objects.nonNull(user) && !user.getId().equals(userId)) {
            throw new TceException(ExceptionType.USER_EMAIL_EXISTS);
        }
    }
}
