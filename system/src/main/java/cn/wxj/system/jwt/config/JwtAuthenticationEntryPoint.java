package cn.wxj.system.jwt.config;

import cn.wxj.common.bean.Result;
import cn.wxj.common.enumeration.ExceptionType;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: JwtAuthenticationEntryPoint
 * @Package cn.wxj.system.config
 * @Description:
 * @Author wuxinjian
 * @Date 2018/12/13 15:52
 * @Version V1.0
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(Result.fail(ExceptionType.TOKEN_ERROR));
    }
}
