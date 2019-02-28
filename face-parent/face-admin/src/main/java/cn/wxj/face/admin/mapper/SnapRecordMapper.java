package cn.wxj.face.admin.mapper;

import cn.wxj.face.admin.entity.SnapRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wxjason123
 * @since 2019-02-27
 */
public interface SnapRecordMapper extends BaseMapper<SnapRecord> {

    /**
     * 查询列表数据
     * @param page
     * @param snapRecord
     * @return
     */
    List<SnapRecord> findListByPage(@Param("page") Page<SnapRecord> page, @Param("snapRecord") SnapRecord snapRecord);
}
