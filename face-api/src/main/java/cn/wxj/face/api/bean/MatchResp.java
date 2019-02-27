package cn.wxj.face.api.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: LivenessResp
 * @Package cn.wxj.operator.jsiot.bean.face
 * @Description:
 * @Author wuxinjian
 * @Date 2018/11/28 10:27
 * @Version V1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MatchResp extends FaceBaseResp {
    private MatchResult result;
}
