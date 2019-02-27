package cn.wxj.face.admin.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wxjason123
 * @since 2019-02-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ad_person")
public class Person extends Model<Person> {

    private static final long serialVersionUID = 1L;

    /**
     * 人员名称
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    /**
     * 人员名称
     */
    @TableField("person_name")
    private String personName;
    /**
     * 人员人脸图名称
     */
    @TableField("person_image")
    private String personImage;
    /**
     * 人员人脸图名称
     */
    @TableField("del")
    private Integer del;

    /**
     * 创建用户id
     */
    @TableField("create_user_id")
    private String createUserId;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
    /**
     * 修改用户id
     */
    @TableField("update_user_id")
    private String updateUserId;
    /**
     * 最后修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


    public static final String ID = "id";

    public static final String PERSON_NAME = "person_name";

    public static final String PERSON_IMAGE = "person_image";

    public static final String DEL = "del";

    public static final String CREATE_USER_ID = "create_user_id";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_USER_ID = "update_user_id";

    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
