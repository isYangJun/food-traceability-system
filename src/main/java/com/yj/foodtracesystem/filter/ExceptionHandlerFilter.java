package com.yj.foodtracesystem.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yj.foodtracesystem.controllerApi.ResultUtil;
import com.yj.foodtracesystem.exception.BaseException;
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
       try {
            filterChain.doFilter(request, response);
        } catch (RuntimeException e) {
            logger.info("ExceptionHandlerFilter: "+e.getMessage());

            // custom error response class used across my project
            BaseException baseException = new BaseException(100,e.getMessage());

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
