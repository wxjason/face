package cn.wxj.face.admin.controller;


import cn.wxj.common.annotation.PathRestController;

import cn.wxj.common.controller.BaseController;
import cn.wxj.common.factory.PageFactory;
import cn.wxj.face.admin.entity.SnapRecord;
import cn.wxj.face.admin.service.ISnapRecordService;
import cn.wxj.face.admin.vo.SnapRecordVo;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@PathRestController("/snap/record")
public class SnapRecordController extends BaseController {

    private final ISnapRecordService snapRecordService;

    @Autowired
    public SnapRecordController(ISnapRecordService snapRecordService) {
        this.snapRecordService = snapRecordService;
    }

    @PostMapping("/list/page")
    public String listPage(@RequestBody SnapRecord snapRecord){
        Page<SnapRecord> page = new PageFactory<SnapRecord>().defaultPage();
        List<SnapRecord> roleList = snapRecordService.listByPage(page, snapRecord);
        return success(page, roleList, SnapRecordVo.class);
    }

    @GetMapping("/detail/{snapRecordId}")
    public String listPage(@PathVariable String snapRecordId){
        SnapRecord snapRecord = snapRecordService.selectById(snapRecordId);
        return success(snapRecord, SnapRecordVo.class);
    }
}

