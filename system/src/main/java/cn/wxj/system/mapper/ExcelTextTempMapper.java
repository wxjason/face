package cn.wxj.system.mapper;

import cn.wxj.system.entity.ExcelTextTemp;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Excel导入过渡表 Mapper 接口
 * </p>
 *
 * @author wxjason123
 * @since 2019-01-16
 */
public interface ExcelTextTempMapper extends BaseMapper<ExcelTextTemp> {

    /**
     * 清除数据库缓存的前天以前Excel导入的数据
     */
    List<ExcelTextTemp> selectBeforeDate(@Param("now") String now);
}
