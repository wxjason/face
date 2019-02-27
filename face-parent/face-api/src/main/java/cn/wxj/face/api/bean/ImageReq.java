package cn.wxj.face.api.bean;

import lombok.Data;

/**
 * @ClassName: ImageReq
 * @Package cn.wxj.face.api.bean
 * @Description:
 * @Author wuxinjian
 * @Date 2019/2/27 10:20
 * @Version V1.0
 */
@Data
public class ImageReq {

    private String imageBase64;

    private String imageBase64A;

    private String imageBase64B;

    private String userId;
}
