package cn.wxj.common.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: FileProperties
 * @Package cn.wxj.yunshi.common.config
 * @Description:
 * @Author wuxinjian
 * @Date 2018/11/9 14:55
 * @Version V1.0
 */
@Component
@ConfigurationProperties(prefix = FileSystemProperties.PREFIX)
@Data
public class FileSystemProperties {

    static final String PREFIX = "spring.file-system";

    private Integer timeout;

    private Integer retry;

    private Integer sleep;

    private String uploadUrl;

    private String uploadFrontUrl;

    private String uploadHeadUrl;

    private String uploadBackUrl;

    private String uploadCatchUrl;

    private String originalUrl;

    @Override
    public String toString() {
        return "FileSystemProperties{" +
                "timeout=" + timeout +
                ", retry=" + retry +
                ", sleep=" + sleep +
                ", uploadUrl='" + uploadUrl + '\'' +
                ", uploadFrontUrl='" + uploadFrontUrl + '\'' +
                ", uploadHeadUrl='" + uploadHeadUrl + '\'' +
                ", uploadBackUrl='" + uploadBackUrl + '\'' +
                ", uploadCatchUrl='" + uploadCatchUrl + '\'' +
                ", originalUrl='" + originalUrl + '\'' +
                '}';
    }
}
