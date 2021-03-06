package com.william.stock.common;

import com.william.stock.entity.model.JwtData;
import com.william.stock.exception.CommonException;
import com.william.stock.utils.JwtTokenUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TokenInterceptor implements HandlerInterceptor {



    public TokenInterceptor() {
        super();
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null || token.length() < 7) {
            throw new CommonException("Authorization 缺失!");
        }
        String type = token.substring(0, 7);
        if (!"Bearer ".equals(type)) {
            throw new CommonException("Authorization类型错误!");
        }
        String subToken = token.substring(7);
        JwtData jwtData;
        try {
            jwtData = JwtTokenUtils.getJwtData(subToken);
        } catch (Exception e) {
            throw new CommonException("Authorization验证失败!");
        }
        if (ObjectUtils.isEmpty(jwtData)) {
            throw new CommonException("Authorization验证失败!");
        }
        request.setAttribute("jwtData",jwtData);
        return true;
    }

}
