package cn.wxj.system.jwt.config;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ClassName: PasswordEncoder
 * @Package cn.wxj.system.jwt.config
 * @Description:
 * @Author wuxinjian
 * @Date 2019/1/2 14:41
 * @Version V1.0
 */
public class PasswordEncoder extends BCryptPasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return super.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        boolean same = super.matches(rawPassword, encodedPassword);
        if (!same) {
            throw new BadCredentialsException("密码错误");
        }
        return true;
    }
}
