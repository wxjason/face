package cn.wxj.face.admin.mapper;

import cn.wxj.face.admin.entity.Device;
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
 * @since 2019-03-01
 */
public interface DeviceMapper extends BaseMapper<Device> {

    /**
     * 分页列表
     * @param page
     * @param device
     * @return
     */
    List<Device> findListByPage(@Param("page") Page<Device> page, @Param("device") Device device);

    /**
     * 列表
     * @param device
     * @return
     */
    List<Device> findList(@Param("device") Device device);
}
