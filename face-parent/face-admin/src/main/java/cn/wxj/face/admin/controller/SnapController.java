package cn.wxj.face.admin.controller;


import cn.wxj.common.annotation.PathRestController;
import cn.wxj.common.controller.BaseController;
import cn.wxj.face.admin.service.ISnapRecordService;
import cn.wxj.face.api.bean.ImageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wxjason123
 * @since 2019-02-27
 */
@PathRestController("/snap")
public class SnapController extends BaseController {

    @Autowired
    private ISnapRecordService snapRecordService;

    @PostMapping("/image")
    public String snapImage(@RequestBody ImageReq req){
        snapRecordService.snapImage(req.getImageBase64());
        return SUCCESS_RESULT;
    }
}

