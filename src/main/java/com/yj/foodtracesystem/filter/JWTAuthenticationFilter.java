package com.yj.foodtracesystem.filter;

import com.yj.foodtracesystem.ConstantKey.ConstantKey;
import com.yj.foodtracesystem.exception.TokenException;
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
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 *
 * @author zhaoxinguo on 2017/9/13.
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("JWTAuthenticationFilter");
        logger.info("doFilterInternal");

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("user:", auth.getName().toString());
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        List<String> list = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : authorities) {
            logger.info("权限列表：{}", grantedAuthority.getAuthority());
            logger.info("权限列表：{}", grantedAuthority.getAuthority().toString());
            list.add(grantedAuthority.getAuthority());
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        logger.info("JWTAuthenticationFilter.getAuthentication");
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            throw new TokenException("Token为空");
        }
        // parse the token.
        String user = null;
        try {
            user = Jwts.parser()
                    .setSigningKey(ConstantKey.SIGNING_KEY)
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody()
                    .getSubject();
            logger.info(String.valueOf(user.length()));
            logger.info(user);
            logger.info(user.toString());
            logger.info(user.getClass().getName().toString());

            if (user != null) {
                ArrayList<GrantedAuthority> authorities = new ArrayList<>();

                /*String authority = (String) user.substring(user.indexOf("["), user.indexOf("]"));
                logger.info("authority",authorit);
                logger.info(authority);*/
                String authority = user;
                authority=authority.substring(authority.indexOf("-")+1,authority.length());
              //authority= authority.substring(9,15);
              logger.info(authority);
                authorities.add(new GrantedAuthorityImpl(authority));
                logger.info(authorities.get(0).getAuthority());
                user = user.substring(0, user.indexOf("-"));
                logger.info(user);
                return new UsernamePasswordAuthenticationToken(user, null, authorities);
            }
        } catch (ExpiredJwtException e) {
            logger.error("Token已过期: {} " + e);
            throw new TokenException("Token已过期");
        } catch (UnsupportedJwtException e) {
            logger.error("Token格式错误: {} " + e);
            throw new TokenException("Token格式错误");
        } catch (MalformedJwtException e) {
            logger.error("Token没有被正确构造: {} " + e);
            throw new TokenException("Token没有被正确构造");
        } catch (SignatureException e) {
            logger.error("签名失败: {} " + e);
            throw new TokenException("签名失败");
        } catch (IllegalArgumentException e) {
            logger.error("非法参数异常: {} " + e);
            throw new TokenException("非法参数异常");
        }
        return null;
    }

}
