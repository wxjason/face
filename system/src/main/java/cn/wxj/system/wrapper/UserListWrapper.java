package cn.wxj.system.wrapper;

import cn.wxj.common.util.DateUtils;
import cn.wxj.common.wrapper.BaseWrapper;
import cn.wxj.system.vo.UserVo;
import cn.wxj.system.entity.User;
import cn.wxj.system.service.IRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserListWrapper extends BaseWrapper<User, UserVo> {

    @Autowired
    private IRoleService roleService;

    @Override
    protected UserVo warp(User model) throws IOException {
        UserVo vo = new UserVo();
        BeanUtils.copyProperties(model, vo);
        vo.setUserId(model.getId());
        vo.setCreateTime(DateUtils.getDateTimeAsString(model.getCreateTime()));
        vo.setRoleName(roleService.getRoleByRoleId(model.getRoleId()).getName());
        return vo;
    }

}
