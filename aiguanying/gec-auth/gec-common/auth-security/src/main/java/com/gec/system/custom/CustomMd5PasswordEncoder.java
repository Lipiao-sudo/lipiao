package com.gec.system.custom;

import com.gec.system.util.MD5Helper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 密码处理
 * </p>
 *
 */
@Component
public class CustomMd5PasswordEncoder implements PasswordEncoder {

    //加密
    public String encode(CharSequence rawPassword) {
        return MD5Helper.encrypt(rawPassword.toString());
    }

    //密码解析
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5Helper.encrypt(rawPassword.toString()));
    }
}