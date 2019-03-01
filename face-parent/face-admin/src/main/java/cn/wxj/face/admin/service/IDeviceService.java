package cn.wxj.face.admin.service;

import cn.wxj.face.admin.entity.Device;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wxjason123
 * @since 2019-03-01
 */
public interface IDeviceService extends IService<Device> {

    /**
     * 分页列表
     * @param page
     * @param device
     * @return
     */
    List<Device> listByPage(Page<Device> page, Device device);

    /**
     * 列表
     * @param device
     * @return
     */
    List<Device> list(Device device);

    /**
     * 添加设备
     * @param device
     * @return
     */
    String addDevice(Device device);

    /**
     * 设备详情
     * @param deviceId
     * @return
     */
    Device getByDeviceId(String deviceId);

    /**
     * 启用设备
     * @param deviceId
     * @return
     */
    void enableDevice(String deviceId);

    /**
     * 停用设备
     * @param deviceId
     * @return
     */
    void disableDevice(String deviceId);

    /**
     * 删除设备
     * @param deviceId
     * @return
     */
    void deleteDevice(String deviceId);
}
