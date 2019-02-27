package cn.wxj.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import cn.wxj.system.ao.OperationLogAddAo;
import cn.wxj.system.ao.OperationLogAo;
import cn.wxj.system.entity.OperationLog;
import com.baomidou.mybatisplus.service.IService;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * <p>
 * 操作日志表 服务类
 * </p>
 *
 * @author wxjason123
 * @since 2019-01-10
 */
public interface IOperationLogService extends IService<OperationLog> {

    /**
     * 条件查询操作日志分页列表
     * @param page
     * @param operationLogAo
     * @return List<OperationLog>
     */
    List<OperationLog> listByPage(Page<OperationLog> page, OperationLogAo operationLogAo);

    /**
     * 添加操作日志
     * @param operationLogAddAo
     * @return List<OperationLog>
     */
    void addOperationLog(OperationLogAddAo operationLogAddAo);

    /**
     * 根据条件创建操作日志导入Excel文件并返回文件全路径
     * @param excelId
     * @return String
     */
    String createExcelFile(String excelId) throws FileNotFoundException;
}
