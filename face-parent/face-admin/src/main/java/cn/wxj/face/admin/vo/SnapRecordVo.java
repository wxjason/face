package cn.wxj.face.admin.vo;

import cn.wxj.common.bean.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: SnapRecordVo
 * @Package cn.wxj.face.admin.vo
 * @Description:
 * @Author wuxinjian
 * @Date 2019/2/28 15:36
 * @Version V1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SnapRecordVo extends BaseVo {

    /**
     * 主键ID
     */
    private String id;
    /**
     * 人员名称
     */
    private String personName;
    /**
     * 人员库人脸图名称
     */
    private String personImage;
    /**
     * 抓拍图片
     */
    private String snapImage;

    private String similarPoint;

    private String createTime;
}
