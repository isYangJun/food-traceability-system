package com.yj.foodtracesystem.configuration;

import com.yj.foodtracesystem.filter.JWTAuthenticationFilter;
import com.yj.foodtracesystem.filter.JWTLoginFilter;
import com.yj.foodtracesystem.handler.UserHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 22:20 2018/4/18
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserHandler userHandler;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Autowired
    private UserDetailsService userDetailsService;


    public SecurityConfiguration(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // 该方法是登录的时候会进入
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        logger.info("SecurityConfiguration: JDBC config");
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    // 设置 HTTP 验证规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login", "/registration", "/header", "/403", "/QRCodeRes", "/errorProWeight","/signup","/users/**").permitAll()
                .antMatchers("/test/**","/swagger-ui.html","/v2/api-docs").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/cooperator/**").hasAuthority("COOPERATOR")
                .antMatchers("/farmer/**").hasAuthority("FARMER")
                .antMatchers("/transadmin/**").hasAuthority("TRANS_ADMIN")
                .antMatchers("/transporter/**").hasAuthority("TRANSPORTER")
                .antMatchers("/reposadmin/**").hasAuthority("REPOS_ADMIN")
                .antMatchers("/repository/**").hasAuthority("REPOSITORY")
                .antMatchers("/saleadmin/**").hasAuthority("SALE_ADMIN")
                .antMatchers("/saleman/**").hasAuthority("SALEMAN")
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTLoginFilter(authenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}
