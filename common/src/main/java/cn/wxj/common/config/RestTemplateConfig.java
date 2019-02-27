package cn.wxj.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: RestTemplateConfig
 * @Package cn.wxj.operator.jsiot.phone.config
 * @Description:
 * @Author wuxinjian
 * @Date 2018/12/27 12:42
 * @Version V1.0
 */
@Configuration
public class RestTemplateConfig {

    private static final Integer CONNECT_TIMEOUT = 5 * 1000;

    private static final Integer READ_TIMEOUT = 10 * 1000;

    @Bean
    public RestTemplate restTemplate(){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        // 设置超时
        requestFactory.setConnectTimeout(CONNECT_TIMEOUT);
        requestFactory.setReadTimeout(READ_TIMEOUT);
        return new RestTemplate();
    }
}
