package cn.wxj.face.admin.mapper;

import cn.wxj.face.admin.entity.Person;
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
public interface PersonMapper extends BaseMapper<Person> {

    /**
     * 删除人员信息
     * @param page
     * @param person
     * @return
     */
    List<Person> findListByPage(@Param("page") Page<Person> page, @Param("person") Person person);
}
