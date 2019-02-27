package cn.wxj.face.api.bean;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: DetectResult
 * @Package cn.wxj.operator.jsiot.phone.bean.face
 * @Description:
 * @Author wuxinjian
 * @Date 2019/1/8 16:20
 * @Version V1.0
 */
@Data
public class DetectResult {

    private int face_num;
    private List<Face> face_list;
}
