package cn.wxj.common.interceptor;

import cn.wxj.common.enumeration.ExceptionType;
import cn.wxj.common.exception.TceException;
import cn.wxj.common.service.WriteLogService;
import cn.wxj.common.util.HttpUtils;
import cn.wxj.common.util.JwtTokenUtils;
import cn.wxj.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: ConcurrencyInterceptor
 * @Package cn.wxj.operator.jsiot.phone.config
 * @Description:
 * @Author wuxinjian
 * @Date 2019/1/11 16:32
 * @Version V1.0
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    private static final String UNKNOWN = "unknown";

    private static final String WRITE_LOG_URI = "/operation/log/add";

    private static final String SYSTEM_URI = "/system";

    private static final String FACE_ADMIN_URI = "/face/admin";

    private static final String[] ACCESS_URIS = {"/login", "/download", "/operation/log/add"};

    @Autowired
    private WriteLogService writeLogService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        for (String accessUri : ACCESS_URIS) {
            if (uri.contains(accessUri)) {
                return true;
            }
        }
        String token = HttpUtils.getToken();
        if (StringUtils.isEmpty(token)) {
            throw new TceException(ExceptionType.TOKEN_ERROR);
        }

        String userId;
        try {
            userId = JwtTokenUtils.getUserId(token);
        } catch (Exception e) {
            throw new TceException(ExceptionType.TOKEN_ERROR);
        }
        if (StringUtils.isEmpty(userId)) {
            throw new TceException(ExceptionType.TOKEN_ERROR);
        }
        String username;
        try {
            username = JwtTokenUtils.getUsername(token);
        } catch (Exception e) {
            throw new TceException(ExceptionType.TOKEN_ERROR);
        }
        if (StringUtils.isEmpty(username)) {
            throw new TceException(ExceptionType.TOKEN_ERROR);
        }
        boolean isExpiration;
        try {
            isExpiration = JwtTokenUtils.isExpiration(token);
        } catch (Exception e) {
            throw new TceException(ExceptionType.TOKEN_ERROR);
        }
        if (isExpiration) {
            throw new TceException(ExceptionType.TOKEN_ERROR);
        }
        String newToken = JwtTokenUtils.createToken(userId, username, false);
        response.addHeader("token", JwtTokenUtils.TOKEN_PREFIX + newToken);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        String uri = request.getRequestURI();
        if (!uri.startsWith(SYSTEM_URI) && !uri.startsWith(FACE_ADMIN_URI)) {
            return;
        }
        if (uri.contains(WRITE_LOG_URI)) {
            return;
        }
        String token = HttpUtils.getToken();
        String userId;
        try {
            userId = JwtTokenUtils.getUserId(token);
        } catch (Exception e) {
            throw new TceException(ExceptionType.TOKEN_ERROR);
        }
        if (StringUtils.isBlank(userId)) {
            return;
        }
        String remoteIp = getClientIP(request);
        Integer resultCode = response.getStatus();
        try {
            writeLogService.writeLog(userId, remoteIp, uri, resultCode, token);
        } catch (Exception e) {
            log.error("操作日志写入异常", e);
        }
    }

    /***
     * 获取客户端IP地址;这里通过了Nginx获取;X-Real-IP,
     *
     * @param request
     * @return
     */
    public String getClientIP(HttpServletRequest request) {
        // 这里和代理模块中要保持一致
        String ip = request.getHeader("X-Real-IP");

        log.info("IP from => X-Real-IP:{}", ip);
        // 下面的属于固定格式
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
            log.info("IP from => X-Forwarded-For:{}", ip);
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            log.info("IP from => Proxy-Client-IP:{}", ip);
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            log.info("IP from => WL-Proxy-Client-IP:{}", ip);
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            log.info("IP from => getRemoteAddr:{}", ip);
        }
        return ip;
    }
}
