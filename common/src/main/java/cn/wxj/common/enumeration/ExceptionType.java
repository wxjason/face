package cn.wxj.common.enumeration;

/**
 * @author fengshuonan
 * @Description 所有业务异常的枚举
 * @date 2016年11月12日 下午5:04:51
 */
public enum ExceptionType {

    /**
     * 操作成功
     */
    HANDLE_SUCCESS(200, "成功"),

    BAD_REQUEST(400, "请求有错误"),

    LOGIN_FAILED(401, "登录失败"),

    TOKEN_ERROR(403, "Token认证失败"),

    SERVER_ERROR(500, "服务器异常"),

    SYSTEM_MAINTENANCE(500, "服务器维护中，请稍后再进行尝试"),

    SYSTEM_BUSY(500, "系统繁忙,请稍后再试"),

    FILE_SERVER_ERROR(500, "文件服务器异常"),

    /**
     * Excel
     */
    EXCEL_UPLOAD_FAIL_TITLE_ERROR(20001,"上传失败，模板不正确"),
    EXCEL_CONTENT_EMPTY(20002,"Excel中内容为空"),

    /**
     * 渠道
     */
    SECRET_KEY_ERROR(10001, "密钥生成失败"),
    CHANNEL_AREA_NOT_EXISTS(10002, "渠道所在省不存在"),
    CHANNEL_CODE_EMPTY(10003, "渠道编码不能为空"),
    CHANNEL_CODE_IS_EXISTS(10004, "渠道编码已存在"),
    CHANNEL_NAME_EMPTY(10005, "渠道名称不能为空"),
    CHANNEL_NAME_LENGTH_LIMIT(10006, "渠道名称长度超长,最大长度30个字符"),
    CHANNEL_SET_MEAL_ERROR(10007, "渠道限量方式不合法"),
    CHANNEL_TOTAL_COUNT_EMPTY(10008, "渠道每日限量数不能为空"),
    CHANNEL_CODE_NOT_EXISTS(10009, "渠道编码不存在"),
    CHANNEL_ID_NOT_EXISTS(10010, "渠道ID不存在"),
    CHANNEL_SCORE_LIMIT(10011, "渠道活体最低置信度超过范围0-1"),
    CHANNEL_SIMILARITY_LIMIT(10012, "渠道比对最低相似度超过范围0-1"),
    CHANNEL_SERIAL_NO_ERROR(10013, "渠道交易流水号不合法"),
    CHANNEL_SERIAL_NO_OVERDUE(10014, "渠道交易流水号已过期"),
    CHANNEL_ID_EMPTY(10015, "渠道ID不能为空"),
    CHANNEL_NAME_ERROR(10016, "渠道名称不合法,请输入1-30个字符的汉字、英文、数字、下划线"),
    CHANNEL_CODE_ERROR(10017, "渠道编码不合法,请输入1-30个字符的英文、数字、下划线"),
    CHANNEL_SAME_AREA_NAME_EXISTS(10018, "同省下已存在该渠道名称"),
    CHANNEL_TOTAL_COUNT_ERROR(10019, "渠道每日限量数不合法,请输入正整数"),
    CHANNEL_IS_DISABLED(10020, "该渠道已停用"),
    CHANNEL_SET_MEAL_LIMIT(10021, "该渠道今日调用次数已达上限"),
    CHANNEL_NOT_IN_LIST(10022, "该渠道暂未接入实名认证平台"),
    CHANNEL_DISABLED(10023, "该渠道已停用实名认证平台"),
    CHANNEL_TIMESTAMP_ERROR(10024, "渠道申请流水号时间戳错误"),
    CHANNEL_TOKEN_URL_EMPTY(10025, "渠道结果回调地址不能为空"),
    CHANNEL_SECRET_ERROR(10026, "密文有误"),
    CHANNEL_ACCESS_MODE_ERROR(10027, "渠道接入方式有误"),
    CHANNEL_ENABLE_TO_DELETE(10028, "该渠道启用中,请停用后再删除"),


    /**
     * 证件识别
     */
    CARD_DISTINGUISH_FAILED(10201, "证件识别失败,请重新上传"),
    CARD_VALID_FAILED(10202, "工信部身份验证失败,请重新上传"),
    CARD_FRONT_IMAGE_EMPTY(10203, "该流水号没有对应证件图片"),
    CARD_VALID_TIME_LIMIT(10204, "22:00-08:00工信部不提供身份验证服务"),

    /**
     * 视频上传处理
     */
    VIDEO_UPLOAD_FAILED(10301, "视频上传失败"),

    VIDEO_FRAME_SHORT(10302, "视频时长太短,建议2-5秒"),

    OCR_FIRST(10303, "请先通过OCR认证"),

    /**
     * 活体检测
     */
    LIVENESS_ERROR(10401, "活体检测出现异常"),

    LIVENESS_FAILED(10402, "活体检测失败，尽量保证拍摄画面清晰"),

    LIVENESS_NO_FACE(10403, "未检测到人脸，建议平视正对手机摄像头"),

    /**
     * 比对
     */
    COMPARE_ERROR(10501, "人证比对出现异常"),

    COMPARE_FAILED(10502, "人证比对失败，尽量保证拍摄画面清晰"),

    /**
     * 登录
     */
    LOGIN_USERNAME_EMPTY(10601, "用户名不能为空"),
    LOGIN_PASSWORD_EMPTY(10602, "密码不能为空"),
    LOGIN_USERNAME_NOT_EXISTS(10603, "用户名不存在"),
    LOGIN_PASSWORD_ERROR(10604, "密码错误"),
    LOGIN_USER_DISABLED(10605, "该用户已被禁用"),

    /**
     * 角色
     */
    ROLE_ID_EMPTY(10701, "角色ID不能为空"),
    ROLE_NAME_EMPTY(10702, "角色名称不能为空"),
    ROLE_NAME_IS_EXISTS(10703, "角色名称已存在"),
    ROLE_HAS_USERS(10704, "该角色已有用户绑定,无法删除"),
    ROLE_ID_NOT_EXISTS(10705, "角色ID不存在"),
    ROLE_NAME_ERROR(10706, "角色名称不合法,请输入1-30个字符的汉字、英文、数字、下划线"),
    ROLE_ADMIN_NOT_UPDATE(10707, "不可操作超级管理员角色"),
    ROLE_REMARK_LENGTH_LIMIT(10708, "备注信息超过长度超长,最大长度100个字符"),

    /**
     * 菜单
     */
    MENU_ID_HAS_SAME(10801, "有相同菜单ID"),
    MENU_ID_NOT_EXISTS(10802, "菜单ID不存在"),
    MENU_NAME_ERROR(10803, "菜单名称不合法,请输入1-30个字符的汉字、英文、数字、下划线"),
    MENU_ICON_LENGTH_LIMIT(10804, "菜单图标长度超长,最大长度100个字符"),
    MENU_URL_LENGTH_LIMIT(10805, "菜单跳转路径长度超长,最大长度100个字符"),
    MENU_TYPE_ERROR(10806, "菜单类型不合法,（1：菜单  2：按钮  3：接口）"),
    MENU_REMARK_LENGTH_LIMIT(10807, "菜单备注长度超长,最大长度100个字符"),
    MENU_PID_NOT_EXISTS(10808, "父级菜单不存在"),
    MENU_ID_EMPTY(10809, "菜单ID不存在"),
    MENU_PID_IS_SELF(10810, "当前菜单不可作为自己父级菜单"),

    /**
     * 用户
     */
    USER_ID_NOT_EXISTS(10901, "用户ID不存在"),
    USER_ID_EMPTY(10902, "用户ID不能为空"),
    USER_ADMIN_NOT_UPDATE(10903, "不可操作admin用户"),
    USER_USERNAME_ERROR(10904, "用户名不合法,请输入1-30个字符的英文、数字、下划线"),
    USER_USERNAME_EXISTS(10905, "该用户名已存在"),
    USER_PASSWORD_ERROR(10906, "密码不合法,请输入1-20个字符的英文、数字、下划线"),
    USER_NAME_ERROR(10907, "用户姓名不合法,请输入1-30个字符的汉字、英文、数字、下划线"),
    USER_PHONE_ERROR(10908, "用户手机号不合法,请输入1-20个数字"),
    USER_EMAIL_ERROR(10909, "用户邮箱输入不合法"),
    USER_OLD_PASSWORD_EMPTY(10910, "原密码不能为空"),
    USER_OLD_PASSWORD_ERROR(10911, "原密码错误"),
    USER_NEW_PASSWORD_EMPTY(10912, "新密码不能为空"),
    USER_NEW_PASSWORD_NOT_SAME(10913, "两次密码输入不一致"),
    USER_PHONE_EXISTS(10914, "该手机号已被其他用户绑定"),
    USER_EMAIL_EXISTS(10915, "该邮箱已被其他用户绑定"),
    USER_ENABLED_TO_DELETE(10916, "该用户启用中,请停用后再删除"),
    USER_NOT_DISABLE_SELF(10917, "不可停用当前登录用户"),
    USER_NOT_DELETE_SELF(10918, "不可删除当前登录用户"),


    /**
     * 人脸检测
     */
    DETECT_ERROR(11101, "人脸检测出现异常"),
    DETECT_NO_FACE(11102, "证件照片中未找到人脸,请重新上传"),
    DETECT_FAILED(11103, "证件照片中人脸检测失败,请重新上传"),

    /**
     * 省
     */
    AREA_CODE_EMPTY(10101, "省代码不能为空"),
    AREA_CODE_EXISTS(10102, "省代码已存在"),
    AREA_CODE_LENGTH_LIMIT(10103, "省代码长度超过限制,最大长度30个字符"),
    AREA_CODE_HAS_SAME(10104, "有相同省代码"),
    AREA_NAME_EMPTY(10105, "省名称不能为空"),
    AREA_NAME_ERROR(10106, "省名称不合法,请输入1-30个字符的汉字、英文、数字、下划线"),
    AREA_REMARK_LENGTH_LIMIT(10107, "省备注信息长度超过限制,最大长度100个字符"),
    AREA_CODE_NOT_EXISTS(10108, "省代码不存在"),
    AREA_HAS_CHANNELS(10109, "该省下已存在渠道,不可删除");

    ExceptionType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
