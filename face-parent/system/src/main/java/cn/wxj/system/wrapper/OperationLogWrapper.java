package cn.wxj.system.wrapper;

import cn.wxj.common.util.DateUtils;
import cn.wxj.common.wrapper.BaseWrapper;
import cn.wxj.system.entity.Menu;
import cn.wxj.system.entity.OperationLog;
import cn.wxj.system.entity.User;
import cn.wxj.system.service.IMenuService;
import cn.wxj.system.service.IUserService;
import cn.wxj.system.vo.OperationLogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class OperationLogWrapper extends BaseWrapper<OperationLog, OperationLogVo> {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMenuService menuService;

    @Override
    protected OperationLogVo warp(OperationLog model) throws IOException {
        OperationLogVo vo = new OperationLogVo();
        BeanUtils.copyProperties(model, vo);
        User user = userService.selectById(model.getUserId());
        Menu menu = menuService.selectById(model.getMenuId());
        Menu button = menuService.selectById(model.getMenuSubId());
        vo.setName(user.getName());
        vo.setUsername(user.getUsername());
        vo.setPhone(user.getPhone());
        vo.setEmail(user.getEmail());
        vo.setMenu(menu.getMenuName());
        vo.setButton(button.getMenuName());
        vo.setResult(model.getResultCode() == HttpStatus.OK.value() ? 1 : 0);
        vo.setCreateTime(DateUtils.getDateTimeAsString(model.getCreateTime()));
        return vo;
    }

}
