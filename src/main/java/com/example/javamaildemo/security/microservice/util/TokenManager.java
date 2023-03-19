package com.example.javamaildemo.security.microservice.util;

import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class TokenManager {
    private static final long EXPIRATION = 1000 * 60 * 60 * 24;
    private static final String KEY = "123456";

    public static String createToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .signWith(SignatureAlgorithm.HS256, KEY)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                // 当payload过长时可以选择压缩载荷
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    public static String getUserInfoFromToken(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody().getSubject();
    }

    // 获取jwt中间的载荷部分
    private static Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }

    public static void main(String[] args) {
        String token = createToken("zhangsan");
        System.out.println(token);

        Claims userInfo = getClaims("eyJhbGciOiJIUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSqspIzEsvTsxT0lFKrShQsjI0M7c0MDayNDWvBQA_eygyIwAAAA.E7KzKEarxjgP53uTm0gMrAcIdrky3lK-iIf7QDKjieo");
        System.out.println(userInfo);
    }
}