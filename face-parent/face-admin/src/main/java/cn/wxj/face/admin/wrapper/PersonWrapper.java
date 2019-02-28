package cn.wxj.face.admin.wrapper;

import cn.wxj.common.constant.StringConstants;
import cn.wxj.common.util.DateUtils;
import cn.wxj.common.util.FileUtils;
import cn.wxj.common.wrapper.BaseWrapper;
import cn.wxj.face.admin.entity.Person;
import cn.wxj.face.admin.vo.PersonVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName: ChannelListWrapper
 * @Package cn.wxj.operator.jsiot.admin.wrapper
 * @Description:
 * @Author wuxinjian
 * @Date 2018/12/25 9:33
 * @Version V1.0
 */
@Component
public class PersonWrapper extends BaseWrapper<Person, PersonVo> {

    @Value("${image.save.path:'/webapp/face/images/'}")
    private String imageSavePath;

    @Override
    protected PersonVo warp(Person model) throws IOException {
        PersonVo vo = new PersonVo();
        BeanUtils.copyProperties(model, vo);
        vo.setCreateTime(DateUtils.getDateTimeAsString(model.getCreateTime()));
        String imagePath = imageSavePath + model.getId() + StringConstants.DOT + FileUtils.IMAGE_TYPE_JPG;
        String imageBase64 = FileUtils.imageBase64(FileUtils.file2Byte(imagePath));
        vo.setPersonImage(imageBase64);
        return vo;
    }

}
