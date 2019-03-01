package cn.wxj.face.admin.bean;

import lombok.Data;

/**
 * @ClassName: SnapReq
 * @Package cn.wxj.face.admin.bean
 * @Description:
 * @Author wuxinjian
 * @Date 2019/3/1 11:44
 * @Version V1.0
 */
@Data
public class SnapReq {

    private String deviceId;

    private String imageBase64;
}
