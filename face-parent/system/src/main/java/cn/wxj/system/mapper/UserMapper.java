package cn.wxj.system.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import cn.wxj.system.ao.UserAo;
import cn.wxj.system.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author wxjason123
 * @since 2019-01-02
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 用户登录验证
     * @param page
     * @param userAo
     * @return List<User>
     */
    List<User> selectListByPage(@Param("page") Page<User> page, @Param("userAo") UserAo userAo);

    /**
     * 用户列表查询
     * @param userAo
     * @return List<User>
     */
    List<User> selectUserList(@Param("userAo") UserAo userAo);
}
