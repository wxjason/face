package cn.wxj.system.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import cn.wxj.system.ao.RoleAo;
import cn.wxj.system.entity.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author wxjason123
 * @since 2019-01-02
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 角色分页列表
     * @param page
     * @param roleAo
     * @return List<Role>
     */
    List<Role> findListByPage(@Param("page") Page<Role> page, @Param("roleAo") RoleAo roleAo);

    /**
     * 角色列表
     * @param roleAo
     * @return List<Role>
     */
    List<Role> findList(@Param("roleAo") RoleAo roleAo);
}
