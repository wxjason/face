package cn.wxj.system.entity;

import java.io.Serializable;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author wxjason123
 * @since 2019-01-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 角色名称
     */
    @TableField("name")
    private String name;
    /**
     * 角色备注
     */
    @TableField("remark")
    private String remark;
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

    public static final String NAME = "name";

    public static final String REMARK = "remark";

    public static final String CREATE_USER_ID = "create_user_id";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_USER_ID = "update_user_id";

    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
