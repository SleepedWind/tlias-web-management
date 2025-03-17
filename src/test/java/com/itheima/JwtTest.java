package com.itheima;

import com.itheima.utils.JwtOperator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Character.getType;

@Slf4j
public class JwtTest {

    @Test
    public void testGenJwt() throws Exception {
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",10);
        claims.put("username","itheima");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"aXRjYXN0")
                .addClaims(claims)  //添加自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + 12*3600*1000)) //设置令牌超时时间
                .compact(); //构建令牌
        System.out.println(jwt);

        Claims parseToken = new JwtOperator().parseToken(jwt);
        System.out.println(parseToken);
        System.out.println(parseToken.getClass().toString());
    }
}
