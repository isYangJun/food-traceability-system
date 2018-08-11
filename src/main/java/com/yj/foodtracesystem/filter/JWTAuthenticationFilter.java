package com.yj.foodtracesystem.filter;

import com.yj.foodtracesystem.ConstantKey.ConstantKey;
import com.yj.foodtracesystem.Result.ResultEnum;
import com.yj.foodtracesystem.exception.BaseException;
import com.yj.foodtracesystem.model.GrantedAuthorityImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 *
 * @author yangjun on 2017/9/13.
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("JWTAuthenticationFilter.doFilterInternal");
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            logger.debug(request.getRequestURL().toString());
            if(request.getRequestURL().toString().contains("/users/signup")||request.getRequestURL().toString().contains("/swagger")
                    ){
                chain.doFilter(request, response);
                return;
            }
            throw new BaseException(ResultEnum.NULL_TOKEN);
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.debug("user:", auth.getName().toString());
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        List<String> list = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : authorities) {
            logger.debug("权限列表：{}", grantedAuthority.getAuthority());
            list.add(grantedAuthority.getAuthority());
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        logger.debug("JWTAuthenticationFilter.getAuthentication");
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            throw new BaseException(ResultEnum.NULL_TOKEN);
        }
        // parse the token.
        String user = null;

            user = Jwts.parser()
                    .setSigningKey(ConstantKey.SIGNING_KEY)
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody()
                    .getSubject();
            if (user != null) {
                ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                String authority = user;
                authority = authority.substring(authority.indexOf("-") + 1, authority.length());
                logger.debug(authority);
                authorities.add(new GrantedAuthorityImpl(authority));
                user = user.substring(0, user.indexOf("-"));
                return new UsernamePasswordAuthenticationToken(user, null, authorities);
            }

        return null;
    }
}