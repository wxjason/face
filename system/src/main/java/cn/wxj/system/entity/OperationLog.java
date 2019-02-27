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
 * 操作日志表
 * </p>
 *
 * @author wxjason123
 * @since 2019-01-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_operation_log")
public class OperationLog extends Model<OperationLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 操作人id
     */
    @TableField("user_id")
    private String userId;
    /**
     * 操作IP地址
     */
    @TableField("remote_ip")
    private String remoteIp;
    /**
     * 菜单ID
     */
    @TableField("menu_id")
    private Integer menuId;
    /**
     * 功能ID
     */
    @TableField("menu_sub_id")
    private Integer menuSubId;
    /**
     * 结果状态码
     */
    @TableField("result_code")
    private Integer resultCode;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String REMOTE_IP = "remote_ip";

    public static final String MENU_ID = "menu_id";

    public static final String MENU_SUB_ID = "menu_sub_id";

    public static final String RESULT_CODE = "result_code";

    public static final String CREATE_TIME = "create_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
