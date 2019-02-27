package cn.wxj.common.bean.es;

import lombok.Data;

/**
 * @ClassName WorkOrderRecordIndex
 * @Author: wxjason
 * @Date: 2018/8/21 13:36
 * @Description: 云讯-实名认证记录
 **/
@Data
public class PushRecord {

    /**
     * 交易流水号(唯一)
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
     * 证件类型
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
     * 证件反面
     */
    private String cardBackId;
    /**
     * 活体抓拍图片
     */
    private String catchId;
    /**
     * 推送地址
     */
    private String pushUrl;
    /**
     * 工单结果
     */
    private Integer result;
    /**
     * 推送结果
     */
    private Integer pushResult;
    /**
     * 推送时间
     */
    private Long createTimestamp;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;

    @Override
    public String toString() {
        return "PushRecord{" +
                "serialNo='" + serialNo + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", channelCode='" + channelCode + '\'' +
                ", channelName='" + channelName + '\'' +
                ", cardType=" + cardType +
                ", cardItems='" + cardItems + '\'' +
                ", cardItemExtends='" + cardItemExtends + '\'' +
                ", cardFrontId='" + cardFrontId + '\'' +
                ", cardBackId='" + cardBackId + '\'' +
                ", catchId='" + catchId + '\'' +
                ", pushUrl='" + pushUrl + '\'' +
                ", result=" + result +
                ", pushResult=" + pushResult +
                ", createTimestamp=" + createTimestamp +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
