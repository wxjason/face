package cn.wxj.face.api.bean;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: SearchResult
 * @Package cn.wxj.face.api.bean
 * @Description:
 * @Author wuxinjian
 * @Date 2019/2/27 9:51
 * @Version V1.0
 */
@Data
public class SearchResult {

    private String face_token;

    private List<User> user_list;
}
