package cn.wxj.face.admin.entity;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
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
 * @since 2019-02-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ad_snap_record")
public class SnapRecord extends Model<SnapRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    /**
     * 人员名称
     */
    @TableField("person_name")
    private String personName;
    /**
     * 人员库人脸图名称
     */
    @TableField("person_image")
    private String personImage;
    /**
     * 抓拍图片
     */
    @TableField("snap_image")
    private String snapImage;
    /**
     * 抓拍图片
     */
    @TableField("similar")
    private Integer similar;
    @TableField("create_time")
    private LocalDateTime createTime;


    public static final String ID = "id";

    public static final String PERSON_NAME = "person_name";

    public static final String PERSON_IMAGE = "person_image";

    public static final String SNAP_IMAGE = "snap_image";

    public static final String SIMILAR = "similar";

    public static final String CREATE_TIME = "create_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
