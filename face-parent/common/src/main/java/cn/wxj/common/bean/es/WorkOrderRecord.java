package cn.wxj.common.bean.es;

import lombok.Data;

/**
 * @ClassName WorkOrderRecord
 * @Author: wxjason
 * @Date: 2018/8/21 13:36
 * @Description: 云讯-实名认证记录
 **/
@Data
public class WorkOrderRecord {

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
     * 人员姓名
     */
    public String personName;
    /**
     * 证件类型
     */
    public Integer cardType;
    /**
     * 人员证件号
     */
    public String cardNo;
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
     * 认证次数
     */
    public Integer count;
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
     * 整个工单的完成状态
     */
    private Integer complete;
    /**
     * OCR识别时间
     */
    private Long ocrTimestamp;
    /**
     * 活体认证时间
     */
    private Long livenessTimestamp;
    /**
     * 人证比对时间
     */
    private Long compareTimestamp;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 工单消耗时间(毫秒)
     */
    private Long time;
}
