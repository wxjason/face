package cn.wxj.face.api.bean;

import lombok.Data;

/**
 * @ClassName: User
 * @Package cn.wxj.face.api.bean
 * @Description:
 * @Author wuxinjian
 * @Date 2019/2/27 9:25
 * @Version V1.0
 */
@Data
public class User {

    private String group_id;

    private String user_id;

    private String user_info;

    private Double score;

}
