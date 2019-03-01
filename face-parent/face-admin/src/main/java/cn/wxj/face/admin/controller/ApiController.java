package cn.wxj.face.admin.controller;


import cn.wxj.common.annotation.PathRestController;
import cn.wxj.common.controller.BaseController;
import cn.wxj.face.admin.bean.SnapReq;
import cn.wxj.face.admin.entity.Device;
import cn.wxj.face.admin.service.IDeviceService;
import cn.wxj.face.admin.service.ISnapRecordService;
import cn.wxj.face.admin.vo.DeviceVo;
import cn.wxj.face.api.bean.ImageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wxjason123
 * @since 2019-02-27
 */
@PathRestController("/api")
public class ApiController extends BaseController {

    @Autowired
    private ISnapRecordService snapRecordService;

    @Autowired
    private IDeviceService deviceService;

    @PostMapping("/snap")
    public String snapImage(@RequestBody SnapReq req){
        snapRecordService.snapImage(req.getDeviceId(), req.getImageBase64());
        return SUCCESS_RESULT;
    }

    @GetMapping("/device/list")
    public String deviceList(){
        List<Device> deviceList = deviceService.list(new Device());
        return success(deviceList, DeviceVo.class);
    }
}

