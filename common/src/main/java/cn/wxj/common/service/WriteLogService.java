package cn.wxj.common.service;

import cn.wxj.common.bean.OperationLogAddAo;
import cn.wxj.common.util.JsonUtils;
import cn.wxj.common.util.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: WriteLogService
 * @Package com.tce.gateway.service
 * @Description:
 * @Author wuxinjian
 * @Date 2019/1/10 18:51
 * @Version V1.0
 */
@Slf4j
@Service
public class WriteLogService {

    private static final String WRITE_LOG_URL = "http://127.0.0.1:8005/system/operation/log/add";

    @Autowired
    private RestTemplate restTemplate;

    @Async
    public void writeLog(String userId, String remoteIp, String uri, Integer resultCode, String token) {
        OperationLogAddAo operationLogAddAo = new OperationLogAddAo();
        operationLogAddAo.setUserId(userId);
        operationLogAddAo.setRemoteIp(remoteIp);
        operationLogAddAo.setUri(uri);
        operationLogAddAo.setResultCode(resultCode);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(JwtTokenUtils.TOKEN_HEADER, JwtTokenUtils.TOKEN_PREFIX + token);
        requestHeaders.add("Content-Type", "application/json");
        HttpEntity<String> requestEntity = new HttpEntity<>(JsonUtils.toJson(operationLogAddAo), requestHeaders);
        try {
            restTemplate.exchange(WRITE_LOG_URL, HttpMethod.POST, requestEntity, String.class);
        } catch (Exception e) {
            log.error("操作日志写入错误", e);
        }
    }
}
