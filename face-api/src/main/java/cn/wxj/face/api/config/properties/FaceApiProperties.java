package cn.wxj.face.api.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: FaceApiProperties
 * @Package cn.wxj.face.api.config.properties
 * @Description:
 * @Author wuxinjian
 * @Date 2018/11/9 14:55
 * @Version V1.0
 */
@Component
@ConfigurationProperties(prefix = FaceApiProperties.PREFIX)
@Data
public class FaceApiProperties {

    static final String PREFIX = "face.api";


    private String appId;

    private String apiKey;

    private String secretKey;

    private String groupId;

}
