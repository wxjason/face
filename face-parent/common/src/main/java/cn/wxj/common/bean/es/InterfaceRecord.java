package cn.wxj.common.bean.es;

import lombok.Data;

/**
 * @ClassName WorkOrderRecordIndex
 * @Author: wxjason
 * @Date: 2018/8/21 13:36
 * @Description: 云讯-实名认证记录
 **/
@Data
public class InterfaceRecord {

    /**
     * 数据id(唯一)
     */
    public String id;

    /**
     * 交易流水号
     */
    public String serialNo;

    /**
     * 省代码
     */
    public String areaCode;

    /**
     * 省名称
     */
    public String areaName;

    /**
     * 渠道编码
     */
    public String channelCode;

    /**
     * 渠道名称
     */
    public String channelName;
    /**
     * 接口类型1.OCR,2活体检测,3人证比对
     */
    public Integer interfaceType;
    /**
     * 人员证件号
     */
    public Integer cardType;
    /**
     * 证件识别出的字段集合
     */
    public String cardItems;
    /**
     * 证件识别出的字段扩展信息集合
     */
    public String cardItemExtends;
    /**
     * 证件正面图片ID
     */
    private String cardFrontId;
    /**
     * 证件头像图片ID
     */
    private String cardHeadId;
    /**
     * 证件反面
     */
    private String cardBackId;
    /**
     * 活体抓拍图片
     */
    private String catchId;
    /**
     * 接口调用结果1成功0失败
     */
    private Integer result;
    /**
     * 接口调用失败原因
     */
    private String message;
    /**
     * 耗时(毫秒)
     */
    private Long time;
    /**
     * 查询开始时间
     */
    public String startTime;

    /**
     * 查询结束时间
     */
    public String endTime;

    @Override
    public String toString() {
        return "InterfaceRecord{" +
                "id='" + id + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", channelCode='" + channelCode + '\'' +
                ", channelName='" + channelName + '\'' +
                ", interfaceType=" + interfaceType +
                ", cardType=" + cardType +
                ", cardItems='" + cardItems + '\'' +
                ", cardItemExtends='" + cardItemExtends + '\'' +
                ", cardFrontId='" + cardFrontId + '\'' +
                ", cardHeadId='" + cardHeadId + '\'' +
                ", cardBackId='" + cardBackId + '\'' +
                ", catchId='" + catchId + '\'' +
                ", result=" + result +
                ", message='" + message + '\'' +
                ", time=" + time +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
