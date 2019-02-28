package cn.wxj.face.admin.controller;


import cn.wxj.common.annotation.PathRestController;
import cn.wxj.common.controller.BaseController;
import cn.wxj.common.factory.PageFactory;
import cn.wxj.face.admin.entity.Person;
import cn.wxj.face.admin.service.IPersonService;
import cn.wxj.face.admin.vo.PersonVo;
import cn.wxj.face.api.bean.ImageReq;
import cn.wxj.face.api.service.FaceApiService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wxjason123
 * @since 2019-02-27
 */
@PathRestController("/person")
public class PersonController extends BaseController {

    @Autowired
    private IPersonService personService;

    @PostMapping("/list/page")
    public String listPage(@RequestBody Person person){
        Page<Person> page = new PageFactory<Person>().defaultPage();
        List<Person> roleList = personService.listByPage(page, person);
        return success(page, roleList, PersonVo.class);
    }

    @PostMapping("/add")
    public String add(@RequestBody Person person){
        String personId = personService.addPerson(person);
        return success(personId);
    }

    @PostMapping("/update")
    public String update(@RequestBody Person person){
        personService.updatePerson(person);
        return SUCCESS_RESULT;
    }

    @GetMapping("/detail/{personId}")
    public String detail(@PathVariable String personId){
        Person person = personService.getByPersonId(personId);
        return success(person, PersonVo.class);
    }

    @GetMapping("/delete/{personId}")
    public String delete(@PathVariable String personId){
        personService.deletePerson(personId);
        return SUCCESS_RESULT;
    }
}

