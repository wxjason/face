package cn.wxj.face.admin.service.impl;

import cn.wxj.common.constant.StringConstants;
import cn.wxj.common.util.DateUtils;
import cn.wxj.common.util.FileUtils;
import cn.wxj.common.util.UUIDUtils;
import cn.wxj.face.admin.entity.Person;
import cn.wxj.face.admin.entity.SnapRecord;
import cn.wxj.face.admin.mapper.SnapRecordMapper;
import cn.wxj.face.admin.service.IPersonService;
import cn.wxj.face.admin.service.ISnapRecordService;
import cn.wxj.face.api.bean.SearchUser;
import cn.wxj.face.api.service.FaceApiService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wxjason123
 * @since 2019-02-27
 */
@Service
public class SnapRecordServiceImpl extends ServiceImpl<SnapRecordMapper, SnapRecord> implements ISnapRecordService {

    private final FaceApiService faceApiService;

    private final IPersonService personService;

    private final String snapImageSavePath;

    private final Double minSimilar;

    @Autowired
    public SnapRecordServiceImpl(FaceApiService faceApiService, IPersonService personService,
                                 @Value("${image.save.path:'/webapp/face/images/'}") String imageSavePath,
                                 @Value("${compare.min.similar:0.5}") Double minSimilar) {
        this.faceApiService = faceApiService;
        this.personService = personService;
        this.snapImageSavePath = imageSavePath + "snap/";
        this.minSimilar = minSimilar;
    }

    @Override
    public void snapImage(String imageBase64) {
        String snapId = UUIDUtils.create();
        String snapImagePath = snapImageSavePath + "snap-" + snapId + StringConstants.DOT + FileUtils.IMAGE_TYPE_JPG;
        imageBase64 = FileUtils.process(imageBase64, 800, 800);
        FileUtils.saveImageBase64(imageBase64, snapImagePath);
        SearchUser searchUser = faceApiService.searchUser(imageBase64);
        SnapRecord snapRecord = new SnapRecord();
        snapRecord.setId(snapId);
        snapRecord.setCreateTime(DateUtils.localDateTime());
        if (Objects.isNull(searchUser) || minSimilar > searchUser.getScore()) {
            snapRecord.setPersonName("陌生人");
            snapRecord.setSnapImage(snapImagePath);
            this.insert(snapRecord);
            return;
        }
        Person person = personService.getByPersonId(searchUser.getUserId());
        String personImagePath = snapImageSavePath + "person-" + snapId + StringConstants.DOT + FileUtils.IMAGE_TYPE_JPG;
        FileUtils.saveImageBase64(Base64Utils.encodeToString(FileUtils.file2Byte(person.getPersonImage())), personImagePath);
        snapRecord.setSimilar((int)(searchUser.getScore() * 100));
        snapRecord.setPersonName(person.getPersonName());
        snapRecord.setPersonImage(personImagePath);
        snapRecord.setSnapImage(snapImagePath);
        this.insert(snapRecord);
    }

    @Override
    public List<SnapRecord> listByPage(Page<SnapRecord> page, SnapRecord snapRecord) {
        return this.baseMapper.findListByPage(page, snapRecord);
    }
}
