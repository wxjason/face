package cn.wxj.face.admin.entity;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableLogic;
import java.io.Serializable;

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
 * @since 2019-03-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ad_device")
public class Device extends Model<Device> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    /**
     * 设备名称
     */
    @TableField("device_name")
    private String deviceName;
    /**
     * 设备网络流地址
     */
    @TableField("stream_url")
    private String streamUrl;
    /**
     * 设备状态1启用0停用
     */
    @TableField("status")
    private Integer status;
    /**
     * 逻辑删除:1:未删,0已删
     */
    @TableField("del")
    @TableLogic
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

    public static final String DEVICE_NAME = "device_name";

    public static final String STREAM_URL = "stream_url";

    public static final String STATUS = "status";

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
