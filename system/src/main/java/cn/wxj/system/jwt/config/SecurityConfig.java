package cn.wxj.system.jwt.config;

import cn.wxj.common.enumeration.Status;
import cn.wxj.system.entity.RoleMenu;
import cn.wxj.system.entity.User;
import cn.wxj.system.jwt.filter.JwtAuthenticationFilter;
import cn.wxj.system.jwt.filter.JwtAuthorizationFilter;
import cn.wxj.system.service.IRoleMenuService;
import cn.wxj.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName: SecurityConfig
 * @Package cn.wxj.system.config
 * @Description:
 * @Author wuxinjian
 * @Date 2018/12/13 14:42
 * @Version V1.0
 */
@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 加密密码
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                //访问：/home 无需登录认证权限
                .antMatchers("/actuator/**", "/operation/log/add", "/operation/log/download/**").permitAll()
                //所有请求
                .anyRequest()
                //不需要验证
                //.permitAll()
                //需要验证
                .authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                // 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {    //用户登录实现
        return new UserDetailsService() {

            @Autowired
            private IUserService userService;
            @Autowired
            private IRoleMenuService roleMenuService;

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                log.info("登录用户为：{}", username);
                User user = userService.getByUsername(username);
                if (Objects.isNull(user)) {
                    //抛出错误，用户不存在
                    throw new AccountExpiredException("用户名" + username + "不存在");
                }
                if (Status.DISABLE.getStatus().equals(user.getStatus())) {
                    //抛出错误，用户被禁用
                    throw new DisabledException("用户名" + username + "已被禁用");
                }
                //获取用户权限
                final List<GrantedAuthority> authorities = new ArrayList<>();
                List<Integer> menuIds = roleMenuService.selectByRoleId(user.getRoleId())
                        .stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
                menuIds.forEach(menuId -> {
                    GrantedAuthority authority = new SimpleGrantedAuthority(String.valueOf(menuId));
                    authorities.add(authority);
                });
                user.setAuthorities(authorities);
                return user;
            }
        };
    }
}
