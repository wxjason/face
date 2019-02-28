package cn.wxj.face.admin.wrapper;

import cn.wxj.common.util.DateUtils;
import cn.wxj.common.util.FileUtils;
import cn.wxj.common.util.StringUtils;
import cn.wxj.common.wrapper.BaseWrapper;
import cn.wxj.face.admin.entity.SnapRecord;
import cn.wxj.face.admin.vo.SnapRecordVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName: ChannelListWrapper
 * @Package cn.wxj.operator.jsiot.admin.wrapper
 * @Description:
 * @Author wuxinjian
 * @Date 2018/12/25 9:33
 * @Version V1.0
 */
@Component
public class SnapRecordWrapper extends BaseWrapper<SnapRecord, SnapRecordVo> {

    @Override
    protected SnapRecordVo warp(SnapRecord model) throws IOException {
        SnapRecordVo vo = new SnapRecordVo();
        BeanUtils.copyProperties(model, vo);
        vo.setCreateTime(DateUtils.getDateTimeAsString(model.getCreateTime()));
        if (StringUtils.isNotBlank(model.getPersonImage())) {
            String personImageBase64 = FileUtils.imageBase64(FileUtils.file2Byte(model.getPersonImage()));
            vo.setPersonImage(personImageBase64);
        }
        String snapImageBase64 = FileUtils.imageBase64(FileUtils.file2Byte(model.getSnapImage()));
        vo.setSnapImage(snapImageBase64);
        if (Objects.nonNull(model.getSimilar())) {
            vo.setSimilarPoint(model.getSimilar() + "%");
        }
        return vo;
    }

}
