package cn.wxj.face.admin.vo;

import cn.wxj.common.bean.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: PersonVo
 * @Package cn.wxj.face.admin.vo
 * @Description:
 * @Author wuxinjian
 * @Date 2019/2/27 16:56
 * @Version V1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PersonVo extends BaseVo {

    /**
     * 人员名称
     */
    private String id;
    /**
     * 人员名称
     */
    private String personName;
    /**
     * 人员人脸图Base64
     */
    private String personImage;
}
