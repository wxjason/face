package cn.wxj.system.controller;


import com.baomidou.mybatisplus.plugins.Page;
import cn.wxj.common.annotation.PathRestController;
import cn.wxj.common.controller.BaseController;
import cn.wxj.common.factory.PageFactory;
import cn.wxj.common.util.FileUtils;
import cn.wxj.common.util.HttpUtils;
import cn.wxj.common.util.JsonUtils;
import cn.wxj.system.ao.OperationLogAddAo;
import cn.wxj.system.ao.OperationLogAo;
import cn.wxj.system.entity.OperationLog;
import cn.wxj.system.service.IExcelTextTempService;
import cn.wxj.system.service.IOperationLogService;
import cn.wxj.system.vo.OperationLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * <p>
 * 操作日志表 前端控制器
 * </p>
 *
 * @author wxjason123
 * @since 2019-01-10
 */
@PathRestController("/operation/log")
public class OperationLogController extends BaseController {

    @Autowired
    private IOperationLogService operationLogService;

    @Autowired
    private IExcelTextTempService excelTextTempService;

    @PostMapping("/list/page")
    public String listPage (@RequestBody OperationLogAo operationLogAo) {
        Page<OperationLog> page = new PageFactory<OperationLog>().defaultPage();
        List<OperationLog> operationLogList = operationLogService.listByPage(page, operationLogAo);
        return success(page, operationLogList, OperationLogVo.class);
    }

    @PostMapping("/add")
    public String add(@RequestBody OperationLogAddAo operationLogAddAo){
        operationLogService.addOperationLog(operationLogAddAo);
        return SUCCESS_RESULT;
    }

    @PostMapping("/export")
    public String export (@RequestBody OperationLogAo operationLogAo) {
        operationLogAo.setUserId(HttpUtils.getUserIdFromToken());
        String excelId = excelTextTempService.saveExcelText(JsonUtils.toJson(operationLogAo));
        return success(excelId);
    }

    @GetMapping("/download/{excelId}")
    public void download (@PathVariable String excelId, HttpServletResponse response) throws FileNotFoundException {
        String excelFile = operationLogService.createExcelFile(excelId);
        FileUtils.download(response, excelFile, ResourceUtils.FILE_URL_PREFIX);
        File file = new File(excelFile);
        if (file.exists()) {
            FileUtils.deleteDir(file);
        }
    }
}

