package cn.wxj.face.admin.service.impl;

import cn.wxj.common.constant.StringConstants;
import cn.wxj.common.util.*;
import cn.wxj.face.admin.entity.Device;
import cn.wxj.face.admin.entity.Person;
import cn.wxj.face.admin.entity.SnapRecord;
import cn.wxj.face.admin.mapper.SnapRecordMapper;
import cn.wxj.face.admin.service.IDeviceService;
import cn.wxj.face.admin.service.IPersonService;
import cn.wxj.face.admin.service.ISnapRecordService;
import cn.wxj.face.admin.vo.SnapRecordVo;
import cn.wxj.face.admin.websocket.WebSocket;
import cn.wxj.face.admin.wrapper.SnapRecordWrapper;
import cn.wxj.face.api.bean.SearchUser;
import cn.wxj.face.api.service.FaceApiService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.IOException;
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

    private final IDeviceService deviceService;

    private final String snapImageSavePath;

    private final Double minSimilar;

    @Autowired
    public SnapRecordServiceImpl(FaceApiService faceApiService, IPersonService personService, IDeviceService deviceService,
                                 @Value("${image.save.path:'/webapp/face/images/'}") String imageSavePath,
                                 @Value("${compare.min.similar:0.5}") Double minSimilar) {
        this.faceApiService = faceApiService;
        this.personService = personService;
        this.deviceService = deviceService;
        this.snapImageSavePath = imageSavePath + "snap/";
        this.minSimilar = minSimilar;
    }

    @Override
    public void snapImage(String deviceId, String imageBase64) {
        Device device = deviceService.getByDeviceId(deviceId);
        String snapId = UUIDUtils.create();
        String snapImagePath = snapImageSavePath + "snap-" + snapId + StringConstants.DOT + FileUtils.IMAGE_TYPE_JPG;
        imageBase64 = FileUtils.process(imageBase64, 800, 800);
        FileUtils.saveImageBase64(imageBase64, snapImagePath);
        SearchUser searchUser = faceApiService.searchUser(imageBase64);
        SnapRecord snapRecord = new SnapRecord();
        snapRecord.setId(snapId);
        snapRecord.setDeviceId(deviceId);
        snapRecord.setDeviceName(device.getDeviceName());
        snapRecord.setCreateTime(DateUtils.localDateTime());
        if (Objects.isNull(searchUser) || minSimilar > searchUser.getScore()) {
            snapRecord.setPersonName("陌生人");
            snapRecord.setSnapImage(snapImagePath);
            this.insert(snapRecord);
            SnapRecordVo vo = this.wrap(snapRecord);
            WebSocket.sendMsg(JsonUtils.toJson(vo));
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
        SnapRecordVo vo = this.wrap(snapRecord);
        WebSocket.sendMsg(JsonUtils.toJson(vo));
    }

    @Override
    public List<SnapRecord> listByPage(Page<SnapRecord> page, SnapRecord snapRecord) {
        return this.baseMapper.findListByPage(page, snapRecord);
    }

    private SnapRecordVo wrap (SnapRecord model) {
        SnapRecordVo vo = new SnapRecordVo();
        BeanUtils.copyProperties(model, vo);
        vo.setCreateTime(DateUtils.getDateTimeAsString(model.getCreateTime()));
        String snapImageBase64 = null;
        try {
            snapImageBase64 = FileUtils.imageBase64(FileUtils.file2Byte(model.getSnapImage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        vo.setSnapImage(snapImageBase64);
        if (Objects.nonNull(model.getSimilar())) {
            vo.setSimilarPoint(model.getSimilar() + "%");
        }
        return vo;
    }
}
