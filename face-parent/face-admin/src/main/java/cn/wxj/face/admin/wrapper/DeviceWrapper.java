package cn.wxj.face.admin.wrapper;

import cn.wxj.common.util.DateUtils;
import cn.wxj.common.util.FileUtils;
import cn.wxj.common.wrapper.BaseWrapper;
import cn.wxj.face.admin.entity.Device;
import cn.wxj.face.admin.entity.Person;
import cn.wxj.face.admin.vo.DeviceVo;
import cn.wxj.face.admin.vo.PersonVo;
import org.springframework.beans.BeanUtils;
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
public class DeviceWrapper extends BaseWrapper<Device, DeviceVo> {

    @Override
    protected DeviceVo warp(Device model) throws IOException {
        DeviceVo vo = new DeviceVo();
        BeanUtils.copyProperties(model, vo);
        vo.setCreateTime(DateUtils.getDateTimeAsString(model.getCreateTime()));
        return vo;
    }

}
