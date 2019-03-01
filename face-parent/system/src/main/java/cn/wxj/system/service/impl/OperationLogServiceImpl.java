package cn.wxj.system.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import cn.wxj.common.constant.NumberConstants;
import cn.wxj.common.constant.StringConstants;
import cn.wxj.common.enumeration.Result;
import cn.wxj.common.util.*;
import cn.wxj.system.ao.OperationLogAddAo;
import cn.wxj.system.ao.OperationLogAo;
import cn.wxj.system.common.Constants;
import cn.wxj.system.entity.Menu;
import cn.wxj.system.entity.OperationLog;
import cn.wxj.system.enumeration.MenuType;
import cn.wxj.system.mapper.OperationLogMapper;
import cn.wxj.system.service.IExcelTextTempService;
import cn.wxj.system.service.IMenuService;
import cn.wxj.system.service.IOperationLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.wxj.system.vo.OperationLogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author wxjason123
 * @since 2019-01-10
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

    private static final String[] EXPORT_TITLE = new String[]{"操作人姓名", "操作用户名", "操作人IP", "手机号", "邮箱", "操作功能", "操作内容", "操作结果", "操作时间"};

    @Autowired
    private IMenuService menuService;
    @Autowired
    private IExcelTextTempService excelTextTempService;

    @Override
    public List<OperationLog> listByPage(Page<OperationLog> page, OperationLogAo operationLogAo) {
        return this.baseMapper.selectListByPage(page, operationLogAo, Constants.ADMIN_USER_ID.equals(HttpUtils.getUserIdFromToken()));
    }

    @Override
    public void addOperationLog(OperationLogAddAo operationLogAddAo) {
        OperationLog operationLog = new OperationLog();
        BeanUtils.copyProperties(operationLogAddAo, operationLog);
        String uri = operationLogAddAo.getUri();
        Menu button = null;
        for (int i = NumberConstants.ZERO; i < NumberConstants.THREE; i++) {
            button = menuService.getMenuByTypeAndUrl(MenuType.BUTTON.getType(), uri);
            if (Objects.nonNull(button)) {
                break;
            }
            uri = uri.substring(NumberConstants.ZERO, uri.lastIndexOf(StringConstants.FORWARD_SLASH));
            if (StringUtils.isBlank(uri)) {
                return;
            }
        }
        if (Objects.isNull(button)) {
            return;
        }
        operationLog.setMenuId(button.getPid());
        operationLog.setMenuSubId(button.getId());
        operationLog.setCreateTime(DateUtils.localDateTime());
        this.insert(operationLog);
    }

    @Override
    public String createExcelFile(String excelId) throws FileNotFoundException {
        String excelTextTemp = excelTextTempService.getExcelText(excelId);
        OperationLogAo operationLogAo = JsonUtils.json2Obj(excelTextTemp, OperationLogAo.class);
        List<OperationLogVo> list = this.baseMapper.list(operationLogAo, Constants.ADMIN_USER_ID.equals(operationLogAo.getUserId()));
        String excelFile = ResourceUtils.getFile(StringUtils.EMPTY)
                .getAbsolutePath()
                .concat(File.separator)
                .concat("export")
                .concat(File.separator)
                .concat(excelId);
        try {
            ExcelUtils.createExcel(excelFile);
            List<List<Object>> excel = new LinkedList<>();
            list.forEach(data -> {
                List<Object> row = new ArrayList<>();
                row.add(data.getName());
                row.add(data.getUsername());
                row.add(data.getRemoteIp());
                row.add(data.getPhone());
                row.add(data.getEmail());
                row.add(data.getMenu());
                row.add(data.getButton());
                row.add(Result.desc(data.getResult()));
                row.add(data.getCreateTime());
                excel.add(row);
            });
            ExcelUtils.appendRow(excelFile, excel, EXPORT_TITLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excelFile;
    }
}
