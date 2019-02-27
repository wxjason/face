package cn.wxj.common.util;

import cn.wxj.common.bean.ResultErrorMessage;
import cn.wxj.common.config.properties.FileSystemProperties;
import cn.wxj.common.constant.NumberConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName: FileSystemUtils
 * @Package cn.wxj.common.util
 * @Description:
 * @Author wuxinjian
 * @Date 2019/1/10 9:19
 * @Version V1.0
 */
@Slf4j
@Component
public class FileSystemUtils {
    /**
     * 图形交换格式
     */
    public static final String IMAGE_TYPE_GIF = "gif";

    /**
     * 联合照片专家组
     */
    public static final String IMAGE_TYPE_JPG = "jpg";

    /**
     * 联合照片专家组
     */
    public static final String IMAGE_TYPE_JPEG = "jpeg";
    /**
     * 英文Bitmap（位图）的简写，它是Windows操作系统中的标准图像文件格式
     */
    public static final String IMAGE_TYPE_BMP = "bmp";
    /**
     * 可移植网络图形
     */
    public static final String IMAGE_TYPE_PNG = "png";
    /**
     * Photoshop的专用格式Photoshop
     */
    public static final String IMAGE_TYPE_PSD = "psd";

    private static FileSystemProperties fileSystemProperties;

    private static RestTemplate restTemplate;

    @Autowired
    private FileSystemProperties fileProperties;

    @Autowired
    private RestTemplate restT;


    @PostConstruct
    public void init() {
        fileSystemProperties = this.fileProperties;
        restTemplate = this.restT;
    }


    public static String upload(String id, String base64, String uploadUrl) {
        byte[] file = Base64Utils.decodeFromString(base64);
        Objects.requireNonNull(file, "需要上传的文件为空!");
        log.info("创建文件ID：" + id);
        log.info("Image Base64 Length:{}", base64.length());
        boolean up = false;
        for (int i = 0; i < fileSystemProperties.getRetry(); i++) {
            try {
                if (!up) {
                    String body = restTemplate.postForObject(uploadUrl + id, file, String.class);
                    log.info("上传文件：" + body);
                    up = result(body);
                    if(up) {
                        return id;
                    }
                }
            } catch (Exception e) {
                log.error("上传的文件失败，Exception：" + e.getMessage());
                if ((i + 1) < fileSystemProperties.getRetry()) {
                    log.error("开始进行第{}次重试!", (i + 1), e);
                }
            }
            ThreadUtils.sleep(fileSystemProperties.getSleep());
        }
        return null;
    }

    public static String uploadFront(String id, String base64) {
        return upload(id, base64, fileSystemProperties.getUploadFrontUrl());
    }

    public static String uploadHead(String id, String base64) {
        return upload(id, base64, fileSystemProperties.getUploadHeadUrl());
    }

    public static String uploadBack(String id, String base64) {
        return upload(id, base64, fileSystemProperties.getUploadBackUrl());
    }

    public static String uploadCatch(String id, String base64) {
        return upload(id, base64, fileSystemProperties.getUploadCatchUrl());
    }

    public static String downloadImgBase64(String id) {
        Objects.requireNonNull(id, "文件ID为空，无法进行下载!");
        final String url = fileSystemProperties.getOriginalUrl() + id;
        byte[] data = downloadSource(url);
        String imageBase64 = "";
        try {
            imageBase64 = FileUtils.imageBase64(data);
        } catch (IOException e) {
            log.error("下载图片失败!",e);
        }
        return imageBase64;
    }

    public static String downloadBase64(String id) {
        Objects.requireNonNull(id, "文件ID为空，无法进行下载!");
        final String url = fileSystemProperties.getOriginalUrl() + id;
        byte[] data = downloadSource(url);
        return Base64Utils.encodeToString(Objects.nonNull(data) ? data : new byte[0]);
    }

    private static boolean result(String result) {
        if (StringUtils.isNotEmpty(result)) {
            try {
                ResultErrorMessage r = JsonUtils.json2Obj(result, ResultErrorMessage.class);
                if (Objects.nonNull(r) && r.getResult() == NumberConstants.TWO_HUNDRED) {
                    if (Objects.nonNull(r.getErrorMessage())) {
                        log.error("操作成功：{}", r.getErrorMessage());
                    }
                    return true;
                }
            } catch (Exception e) {
                log.error("操作出现异常：{}", e.getMessage());
            }
        }
        return false;
    }

    private static byte[] downloadSource(String url) {
        String fileId = url.substring(url.indexOf("id="));
        for (int i = 0; i < fileSystemProperties.getRetry(); i++) {
            try {
                byte[] data = restTemplate.getForEntity(url, byte[].class).getBody();
                if (Objects.isNull(data)) {
                    data = new byte[0];
                }
                log.info("下载的文件大小为：{}，文件ID：{}", data.length, fileId);
                return data;
            } catch (Exception e) {
                log.error("下载的文件失败，Exception：" + e.getMessage());
                if ((i + 1) < fileSystemProperties.getRetry()) {
                    log.error("开始进行第{}次重试!", (i + 1));
                }
            }
            ThreadUtils.sleep(fileSystemProperties.getSleep());
        }
        return null;
    }
}
