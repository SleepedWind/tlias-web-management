package com.itheima.interceptor;

import com.itheima.utils.JwtOperator;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1.从请求头中获取token
        String token =request.getHeader("token");

        //2.判断token是否存在，如果不存在，返回401状态码
        if(token == null || token.isEmpty()){
            log.info("令牌不存在，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //3.如果token存在，校验token，如果校验错误，返回401状态码
        try{
            JwtOperator.parseToken(token);
        }catch (Exception e){
            log.info("令牌非法，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }
}
