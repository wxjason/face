package cn.wxj.system.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import cn.wxj.common.bean.Result;
import cn.wxj.common.constant.StringConstants;
import cn.wxj.common.enumeration.ExceptionType;
import cn.wxj.common.exception.TceException;
import cn.wxj.common.util.JsonUtils;
import cn.wxj.system.ao.LoginUser;
import cn.wxj.system.entity.User;
import cn.wxj.common.util.JwtTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: JwtAuthenticationFilter
 * @Package cn.wxj.system.jwt.filter
 * @Description:
 * @Author wuxinjian
 * @Date 2018/12/13 14:48
 * @Version V1.0
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        // 从输入流中获取到登录的信息
        LoginUser loginUser;
        try {
            loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
        } catch (IOException e) {
            throw new TceException(ExceptionType.BAD_REQUEST);
        }
        //这里的username会传给UserDetailsService的loadUserByUsername方法，作为loadUserByUsername的参数。
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());

        // 允许子类设置详细属性
        setDetails(request, authRequest);

        return authenticationManager.authenticate(authRequest);
    }

    /**
     * 成功验证后调用的方法,如果验证成功，就生成token并返回
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        // 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象
        // 所以就是JwtUser啦
        User user = (User) authResult.getPrincipal();
        String token = JwtTokenUtils.createToken(user.getId(), user.getUsername(), false);
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的格式应该是 `Bearer token`
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
        response.getWriter().write(Result.success());
    }

    /**
     * 这是验证失败时候调用的方法
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Result result = new Result(ExceptionType.LOGIN_FAILED.getCode(), ExceptionType.LOGIN_FAILED.getMessage() + StringConstants.COLON + failed.getMessage());
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(JsonUtils.toJson(result));
    }
}
