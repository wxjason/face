package cn.wxj.face.api.controller;

import cn.wxj.face.api.bean.ImageReq;
import cn.wxj.face.api.bean.SearchUser;
import cn.wxj.face.api.service.FaceApiService;
import cn.wxj.common.annotation.PathRestController;
import cn.wxj.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: FaceApiController
 * @Package cn.wxj.face.api.controller
 * @Description:
 * @Author wuxinjian
 * @Date 2019/2/27 10:12
 * @Version V1.0
 */
@PathRestController("/api")
public class FaceApiController extends BaseController {

    @Autowired
    private FaceApiService faceApiService;

    @PostMapping("/user/add")
    public String addUser(@RequestBody ImageReq req){
        String userId = faceApiService.addUser(req.getImageBase64());
        return success(userId);
    }

    @PutMapping("/user/update")
    public String updateUser(@RequestBody ImageReq req){
        faceApiService.updateUser(req.getUserId(), req.getImageBase64());
        return SUCCESS_RESULT;
    }

    @DeleteMapping("/user/delete/{userId}")
    public String deleteUser(@PathVariable String userId){
        faceApiService.deleteUser(userId);
        return SUCCESS_RESULT;
    }

    @PostMapping("/user/search")
    public String searchUser(@RequestBody ImageReq req){
        SearchUser user = faceApiService.searchUser(req.getImageBase64());
        return success(user);
    }

    @PostMapping("/detect")
    public String detect(@RequestBody ImageReq req){
        Double score = faceApiService.detect(req.getImageBase64());
        return success(score);
    }

    @PostMapping("/compare")
    public String compare(@RequestBody ImageReq req){
        Double score = faceApiService.compare(req.getImageBase64A(), req.getImageBase64B());
        return success(score);
    }
}
