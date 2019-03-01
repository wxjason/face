package cn.wxj.face.admin.controller;


import cn.wxj.common.annotation.PathRestController;
import cn.wxj.common.controller.BaseController;
import cn.wxj.common.factory.PageFactory;
import cn.wxj.face.admin.entity.Device;
import cn.wxj.face.admin.service.IDeviceService;
import cn.wxj.face.admin.vo.DeviceVo;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wxjason123
 * @since 2019-03-01
 */
@PathRestController("/device")
public class DeviceController extends BaseController {

    @Autowired
    private IDeviceService deviceService;

    @PostMapping("/list/page")
    public String listPage(@RequestBody Device device){
        Page<Device> page = new PageFactory<Device>().defaultPage();
        List<Device> roleList = deviceService.listByPage(page, device);
        return success(page, roleList, DeviceVo.class);
    }

    @PostMapping("/list")
    public String list(@RequestBody Device device){
        List<Device> roleList = deviceService.list(device);
        return success(roleList, DeviceVo.class);
    }

    @PostMapping("/add")
    public String add(@RequestBody Device device){
        String deviceId = deviceService.addDevice(device);
        return success(deviceId);
    }

    @GetMapping("/detail/{deviceId}")
    public String detail(@PathVariable String deviceId){
        Device device = deviceService.getByDeviceId(deviceId);
        return success(device, DeviceVo.class);
    }

    @GetMapping("/enable/{deviceId}")
    public String enable(@PathVariable String deviceId){
        deviceService.enableDevice(deviceId);
        return SUCCESS_RESULT;
    }

    @GetMapping("/disable/{deviceId}")
    public String disable(@PathVariable String deviceId){
        deviceService.disableDevice(deviceId);
        return SUCCESS_RESULT;
    }

    @GetMapping("/delete/{deviceId}")
    public String delete(@PathVariable String deviceId){
        deviceService.deleteDevice(deviceId);
        return SUCCESS_RESULT;
    }
}

