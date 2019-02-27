package cn.wxj.system.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import cn.wxj.system.ao.OperationLogAo;
import cn.wxj.system.entity.OperationLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import cn.wxj.system.vo.OperationLogVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 操作日志表 Mapper 接口
 * </p>
 *
 * @author wxjason123
 * @since 2019-01-10
 */
public interface OperationLogMapper extends BaseMapper<OperationLog> {

    /**
     * 条件查询操作日志分页列表
     * @param page
     * @param operationLogAo
     * @param isAdmin
     * @return List<OperationLog>
     */
    List<OperationLog> selectListByPage(@Param("page") Page<OperationLog> page, @Param("operationLogAo") OperationLogAo operationLogAo, @Param("isAdmin") boolean isAdmin);

    /**
     * 条件查询操作日志列表
     * @param operationLogAo
     * @param isAdmin
     * @return List<OperationLogVo>
     */
    List<OperationLogVo> list(@Param("operationLogAo") OperationLogAo operationLogAo, @Param("isAdmin") boolean isAdmin);
}
