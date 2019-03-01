package cn.wxj.face.admin.service.impl;

import cn.wxj.common.enumeration.Delete;
import cn.wxj.common.enumeration.Status;
import cn.wxj.common.util.DateUtils;
import cn.wxj.common.util.HttpUtils;
import cn.wxj.common.util.UUIDUtils;
import cn.wxj.face.admin.entity.Device;
import cn.wxj.face.admin.mapper.DeviceMapper;
import cn.wxj.face.admin.service.IDeviceService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wxjason123
 * @since 2019-03-01
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

    @Override
    public List<Device> listByPage(Page<Device> page, Device device) {
        return this.baseMapper.findListByPage(page, device);
    }

    @Override
    public List<Device> list(Device device) {
        return this.baseMapper.findList(device);
    }

    @Override
    public String addDevice(Device device) {
        String deviceId = UUIDUtils.create();
        device.setId(deviceId);
        device.setStatus(Status.ENABLE.getStatus());
        device.setDel(Delete.NORMOL.getCode());
        device.setCreateUserId(HttpUtils.getUserIdFromToken());
        device.setCreateTime(DateUtils.localDateTime());
        //TODO 调用添加设备接口

        this.insert(device);
        return deviceId;
    }

    @Override
    public Device getByDeviceId(String deviceId) {
        return this.selectById(deviceId);
    }

    @Override
    public void enableDevice(String deviceId) {
        Device device = this.getByDeviceId(deviceId);
        device.setStatus(Status.ENABLE.getStatus());
        device.setUpdateUserId(HttpUtils.getUserIdFromToken());
        device.setUpdateTime(DateUtils.localDateTime());
        //TODO 调用启用设备接口
        this.updateById(device);
    }

    @Override
    public void disableDevice(String deviceId) {
        Device device = this.getByDeviceId(deviceId);
        device.setStatus(Status.DISABLE.getStatus());
        device.setUpdateUserId(HttpUtils.getUserIdFromToken());
        device.setUpdateTime(DateUtils.localDateTime());
        //TODO 调用停用设备接口
        this.updateById(device);
    }

    @Override
    public void deleteDevice(String deviceId) {
        Device device = new Device();
        device.setId(deviceId);
        device.setDel(Delete.DELETE.getCode());
        this.updateById(device);
    }
}
