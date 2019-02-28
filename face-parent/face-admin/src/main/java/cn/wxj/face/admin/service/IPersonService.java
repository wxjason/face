package cn.wxj.face.admin.service;

import cn.wxj.face.admin.entity.Person;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wxjason123
 * @since 2019-02-27
 */
public interface IPersonService extends IService<Person> {

    /**
     * 添加人员信息
     * @param person
     * @return
     */
    String addPerson(Person person);

    /**
     * 更新人员信息
     * @param person
     * @return
     */
    void updatePerson(Person person);

    /**
     * 删除人员信息
     * @param personId
     * @return
     */
    void deletePerson(String personId);

    /**
     * 删除人员信息
     * @param page
     * @param person
     * @return
     */
    List<Person> listByPage(Page<Person> page, Person person);

    /**
     * 删除人员信息
     * @param personId
     * @return
     */
    Person getByPersonId(String personId);
}
