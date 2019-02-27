package cn.wxj.face.api.bean;

import lombok.Data;

/**
 * @ClassName: FaceBaseResp
 * @Package cn.wxj.operator.jsiot.bean.face
 * @Description:
 * @Author wuxinjian
 * @Date 2018/11/28 11:16
 * @Version V1.0
 */
@Data
public class FaceBaseResp {

    protected Integer error_code;
    protected String error_msg;
    protected Long log_id;
    protected Integer timestamp;
    protected Integer cached;
}
