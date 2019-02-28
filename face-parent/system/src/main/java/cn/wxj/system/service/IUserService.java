package cn.wxj.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import cn.wxj.system.ao.ChangePasswordAo;
import cn.wxj.system.ao.LoginUser;
import cn.wxj.system.ao.UserAo;
import cn.wxj.system.entity.User;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wxjason123
 * @since 2018-12-29
 */
public interface IUserService extends IService<User> {

    /**
     * 根据用户名查询用户
     * @param username
     * @return User
     */
    User getByUsername(String username);

    /**
     * 用户登录验证
     * @param loginUser
     */
    void login(LoginUser loginUser);

    /**
     * 用户分页列表查询
     * @param page
     * @param userAo
     * @return List<User>
     */
    List<User> listByPage(Page<User> page, UserAo userAo);

    /**
     * 根据角色ID查询用户列表
     * @param roleId
     * @return List<User>
     */
    List<User> selectByRoleId(String roleId);

    /**
     * 用户列表查询
     * @param userAo
     * @return List<User>
     */
    List<User> list(UserAo userAo);

    /**
     * 根据用户ID查询用户
     * @param userId
     * @return User
     */
    User getUserById(String userId);

    /**
     * 添加用户
     * @param userAo
     * @return String
     */
    String addUserAo(UserAo userAo);

    /**
     * 修改用户信息
     * @param userAo
     */
    void updateUserAo(UserAo userAo);

    /**
     * 启用用户
     * @param userId
     */
    void enableUser(String userId);

    /**
     * 禁用用户
     * @param userId
     */
    void disableUser(String userId);

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);

    /**
     * 修改用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据用户ID删除用户
     * @param userId
     */
    void deleteUserById(String userId);

    /**
     * 检查新增用户信息
     * @param user
     */
    void checkAddUser(User user);

    /**
     * 检查修改用户信息
     * @param user
     */
    void checkUpdateUser(User user);

    /**
     * 检查是否为Admin用户
     * @param userId
     */
    void checkAdmin(String userId);

    /**
     * 检查用户角色ID
     * @param roleId
     */
    void checkRoleId(String roleId);

    /**
     * 修改用户密码
     * @param changePasswordAo
     */
    void changePassword(ChangePasswordAo changePasswordAo);

    /**
     * 修改用户密码
     * @param userId
     * @return String
     */
    String resetPassword(String userId);
}
