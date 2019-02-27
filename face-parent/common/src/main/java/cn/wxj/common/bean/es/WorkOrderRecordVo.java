package cn.wxj.common.bean.es;

import lombok.Data;

/**
 * @author wxjason
 */
@Data
public class WorkOrderRecordVo {

    /**
     * 交易流水号(唯一)
     */
    public String serialNo;

    /**
     * 区域编码
     */
    public String areaCode;

    /**
     * 区域编码
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
     * 人员姓名
     */
    public String personName;
    /**
     * 人员证件号
     */
    public Integer cardType;
    /**
     * 人员证件号
     */
    public Integer cardName;
    /**
     * 人员证件号
     */
    public String cardNo;
    /**
     * 证件识别出的字段集合
     */
    public String cardItems;
    /**
     * 证件识别出的所有字段集合
     */
    public String cardItemList;
    /**
     * 证件识别出的字段扩展信息集合
     */
    public String cardItemExtends;
    /**
     * 证件正面图片ID
     */
    public String cardFrontId;
    /**
     * 证件头像图片ID
     */
    public String cardHeadId;
    /**
     * 证件背面图片ID
     */
    public String cardBackId;
    /**
     * 现场抓拍图片ID
     */
    public String catchId;

    /**
     * 活体最低置信度
     */
    private Double minLivenessScore;
    /**
     * 活体置信度
     */
    private Double livenessScore;
    /**
     * 比对最低相似度
     */
    private Double minSimilarity;
    /**
     * 人证比对相似度
     */
    private Double similarity;
    /**
     * OCR识别结果1成功0失败
     */
    private Integer ocrResult;
    /**
     * 活体认证结果1成功0失败
     */
    private Integer livenessResult;
    /**
     * 人证比对结果1成功0失败
     */
    private Integer compareResult;
    /**
     * 整个工单的结果
     */
    private Integer result;
    /**
     * 整个工单的结果
     */
    private Integer complete;
    /**
     * OCR识别时间
     */
    private Long ocrTimestamp;
    /**
     * OCR识别时间
     */
    private String ocrTime;
    /**
     * 活体认证时间
     */
    private Long livenessTimestamp;
    /**
     * 活体认证时间
     */
    private String livenessTime;
    /**
     * 人证比对时间
     */
    private Long compareTimestamp;
    /**
     * 人证比对时间
     */
    private String compareTime;
    /**
     * 工单生成时间
     */
    private Long createTimestamp;
    /**
     * 工单生成时间
     */
    private String createTime;
    /**
     * 工单总耗时
     */
    private Long time;

    @Override
    public String toString() {
        return "WorkOrderRecordVo{" +
                "serialNo='" + serialNo + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", channelCode='" + channelCode + '\'' +
                ", channelName='" + channelName + '\'' +
                ", personName='" + personName + '\'' +
                ", cardType=" + cardType +
                ", cardName=" + cardName +
                ", cardNo='" + cardNo + '\'' +
                ", cardItems='" + cardItems + '\'' +
                ", cardItemList='" + cardItemList + '\'' +
                ", cardItemExtends='" + cardItemExtends + '\'' +
                ", cardFrontId='" + cardFrontId + '\'' +
                ", cardHeadId='" + cardHeadId + '\'' +
                ", cardBackId='" + cardBackId + '\'' +
                ", catchId='" + catchId + '\'' +
                ", minLivenessScore=" + minLivenessScore +
                ", livenessScore=" + livenessScore +
                ", minSimilarity=" + minSimilarity +
                ", similarity=" + similarity +
                ", ocrResult=" + ocrResult +
                ", livenessResult=" + livenessResult +
                ", compareResult=" + compareResult +
                ", result=" + result +
                ", ocrTimestamp=" + ocrTimestamp +
                ", ocrTime='" + ocrTime + '\'' +
                ", livenessTimestamp=" + livenessTimestamp +
                ", livenessTime='" + livenessTime + '\'' +
                ", compareTimestamp=" + compareTimestamp +
                ", compareTime='" + compareTime + '\'' +
                ", createTimestamp=" + createTimestamp +
                ", createTime='" + createTime + '\'' +
                ", time=" + time +
                '}';
    }
}
