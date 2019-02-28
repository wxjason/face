package cn.wxj.face.admin.service.impl;

import cn.wxj.common.constant.StringConstants;
import cn.wxj.common.enumeration.Delete;
import cn.wxj.common.enumeration.ExceptionType;
import cn.wxj.common.exception.TceException;
import cn.wxj.common.util.DateUtils;
import cn.wxj.common.util.FileUtils;
import cn.wxj.common.util.HttpUtils;
import cn.wxj.common.util.StringUtils;
import cn.wxj.face.admin.entity.Person;
import cn.wxj.face.admin.mapper.PersonMapper;
import cn.wxj.face.admin.service.IPersonService;
import cn.wxj.face.api.service.FaceApiService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wxjason123
 * @since 2019-02-27
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {

    @Autowired
    private FaceApiService faceApiService;

    @Value("${image.save.path:'/webapp/face/images/'}")
    private String imageSavePath;

    @Override
    public String addPerson(Person person) {
        String personId = faceApiService.addUser(person.getPersonImage());
        String imagePath = imageSavePath + personId + StringConstants.DOT + FileUtils.IMAGE_TYPE_JPG;
        person.setId(personId);
        String image = FileUtils.process(person.getPersonImage(), 800, 800);
        FileUtils.saveImageBase64(image, imagePath);
        person.setPersonImage(imagePath);
        person.setDel(Delete.NORMOL.getCode());
        person.setCreateUserId(HttpUtils.getUserIdFromToken());
        person.setCreateTime(DateUtils.localDateTime());
        this.insert(person);
        return personId;
    }

    @Override
    public void updatePerson(Person person) {
        faceApiService.updateUser(person.getId(), person.getPersonImage());
        String imagePath = imageSavePath + person.getId() + StringConstants.DOT + FileUtils.IMAGE_TYPE_JPG;
        String image = FileUtils.process(person.getPersonImage(), 800, 800);
        FileUtils.saveImageBase64(image, imagePath);
        person.setPersonImage(imagePath);
        person.setUpdateUserId(HttpUtils.getUserIdFromToken());
        person.setUpdateTime(DateUtils.localDateTime());
        this.updateById(person);
    }

    @Override
    public void deletePerson(String personId) {
        faceApiService.deleteUser(personId);
        Person person = new Person();
        person.setId(personId);
        person.setDel(Delete.DELETE.getCode());
        person.setUpdateUserId(HttpUtils.getUserIdFromToken());
        person.setUpdateTime(DateUtils.localDateTime());
        this.updateById(person);
    }

    @Override
    public List<Person> listByPage(Page<Person> page, Person person) {
        return this.baseMapper.findListByPage(page, person);
    }

    @Override
    public Person getByPersonId(String personId) {
        return this.selectById(personId);
    }
}
