package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtOperator {
    private static final String SECRET_KEY = "aXRjYXN0"; // 秘钥
    private static final long EXPIRATION_TIME = 12 * 60 * 60 * 1000 ; //12小时
    //对外提供创建jwt令牌的方法
    public static String getToken(Map<String,Object>  claims){
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();
        return token;
    }

    //对外提供解析jwt令牌的方法
    public static Claims parseToken(String token) throws Exception{
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
