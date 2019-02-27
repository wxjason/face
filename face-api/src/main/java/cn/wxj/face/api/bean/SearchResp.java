package cn.wxj.face.api.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: SearchResp
 * @Package cn.wxj.face.api.bean
 * @Description:
 * @Author wuxinjian
 * @Date 2019/2/27 9:27
 * @Version V1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchResp extends FaceBaseResp {

    private SearchResult result;
}
