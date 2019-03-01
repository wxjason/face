package cn.wxj.face.admin.vo;

import cn.wxj.common.bean.BaseVo;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
public class DeviceVo extends BaseVo {

    /**
     * 主键id
     */
    private String id;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备网络流地址
     */
    private String streamUrl;
    /**
     * 设备状态1启用0停用
     */
    private Integer status;
    /**
     * 创建用户id
     */
    private String createTime;

}
