package com.yj.foodtracesystem.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yj.foodtracesystem.controllerApi.ResultEnum;
import com.yj.foodtracesystem.controllerApi.ResultUtil;
import com.yj.foodtracesystem.exception.BaseException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.mapstruct.BeforeMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerFilter.class);

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      BaseException baseException=new BaseException(ResultEnum.NULL_TOKEN);
       try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
           logger.error("Token已过期: {} " + e);
           logger.info("异常类型："+e.getClass().getName().toString());
           baseException=new BaseException(ResultEnum.EXPIRED_TOKEN);
           response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
           response.getWriter().write(convertObjectToJson(ResultUtil.error(baseException.getCode(),baseException.getMessage())));
       } catch (UnsupportedJwtException e) {
           logger.error("Token格式错误: {} " + e);
           baseException=new BaseException(ResultEnum.ARGUMENT_ERROR);
           response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
           response.getWriter().write(convertObjectToJson(ResultUtil.error(baseException.getCode(),baseException.getMessage())));
       } catch (MalformedJwtException e) {
           logger.error("Token没有被正确构造: {} " + e);
           baseException=new BaseException(ResultEnum.UNFORMED_TOKEN);
           response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
           response.getWriter().write(convertObjectToJson(ResultUtil.error(baseException.getCode(),baseException.getMessage())));
       } catch (SignatureException e) {
           logger.error("签名失败: {} " + e);
           baseException=new BaseException(ResultEnum.SIGNFAIL_TOKEN);
           response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
           response.getWriter().write(convertObjectToJson(ResultUtil.error(baseException.getCode(),baseException.getMessage())));
       } catch (IllegalArgumentException e) {
           logger.error("非法参数异常: {} " + e);
           baseException=new BaseException(ResultEnum.ILLEAGUE_TOKEN);
           response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
           response.getWriter().write(convertObjectToJson(ResultUtil.error(baseException.getCode(),baseException.getMessage())));
       }
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
