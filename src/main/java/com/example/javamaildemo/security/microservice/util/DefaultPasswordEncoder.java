package com.example.javamaildemo.security.microservice.util;


import com.example.javamaildemo.security.microservice.util.Md5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义加密方法
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return Md5.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(Md5.encode(rawPassword));
    }
}
