package cn.wxj.face.admin.service;

import cn.wxj.face.admin.entity.SnapRecord;
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
public interface ISnapRecordService extends IService<SnapRecord> {

    /**
     * 处理抓拍图片
     * @param deviceId
     * @param imageBase64
     */
    void snapImage(String deviceId, String imageBase64);

    /**
     * 查询列表数据
     * @param page
     * @param snapRecord
     * @return
     */
    List<SnapRecord> listByPage(Page<SnapRecord> page, SnapRecord snapRecord);
}
