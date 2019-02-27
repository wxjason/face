package cn.wxj.system.controller;


import com.baomidou.mybatisplus.plugins.Page;
import cn.wxj.common.annotation.PathRestController;
import cn.wxj.common.controller.BaseController;
import cn.wxj.common.factory.PageFactory;
import cn.wxj.common.util.HttpUtils;
import cn.wxj.system.ao.ChangePasswordAo;
import cn.wxj.system.ao.UserAo;
import cn.wxj.system.vo.UserVo;
import cn.wxj.system.entity.User;
import cn.wxj.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wxjason123
 * @since 2019-01-02
 */
@PathRestController("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @PostMapping("/list/page")
    public String listPage(@RequestBody UserAo userAo){
        Page<User> page = new PageFactory<User>().defaultPage();
        List<User> roleList = userService.listByPage(page, userAo);
        return success(page, roleList, UserVo.class);
    }

    @PostMapping("/list")
    public String list(@RequestBody UserAo userAo){
        List<User> roleList = userService.list(userAo);
        return success(roleList, UserVo.class);
    }

    @PostMapping("/add")
    public String add(@RequestBody UserAo userAo){
        String userId = userService.addUserAo(userAo);
        return success(userId);
    }

    @PostMapping("/update")
    public String update(@RequestBody UserAo userAo){
        userService.updateUserAo(userAo);
        return SUCCESS_RESULT;
    }

    @GetMapping("/enable/{userId}")
    public String enable(@PathVariable String userId){
        userService.enableUser(userId);
        return SUCCESS_RESULT;
    }

    @GetMapping("/disable/{userId}")
    public String disable(@PathVariable String userId){
        userService.disableUser(userId);
        return SUCCESS_RESULT;
    }

    @GetMapping("/delete/{userId}")
    public String delete(@PathVariable String userId){
        userService.deleteUserById(userId);
        return SUCCESS_RESULT;
    }

    @GetMapping("/detail/{userId}")
    public String detail(@PathVariable String userId){
        User user = userService.getUserById(userId);
        return success(user, UserVo.class);
    }

    @GetMapping("/current")
    public String current(){
        User user = userService.getUserById(HttpUtils.getUserIdFromToken());
        return success(user, UserVo.class);
    }

    @PostMapping("/password/change")
    public String changePassword(@RequestBody ChangePasswordAo changePasswordAo){
        userService.changePassword(changePasswordAo);
        return SUCCESS_RESULT;
    }

    @GetMapping("/password/reset/{userId}")
    public String resetPassword(@PathVariable String userId){
        String newPassword = userService.resetPassword(userId);
        return success(newPassword);
    }

}

