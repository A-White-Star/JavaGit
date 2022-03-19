package com.white.examsystem.config;

import com.white.examsystem.common.RespBean;
import com.white.examsystem.model.User;
import com.white.examsystem.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.io.PrintWriter;

@Configuration
public class SecrityConfig extends WebSecurityConfigurerAdapter {
   @Autowired
    private UserService service;
    @Autowired
    private MyFilter  myFilter;
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;

    @Bean
    PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**","/img/**","/favicon.ico","/houtai/yonghu","/client/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(myAccessDecisionManager);
                        o.setSecurityMetadataSource(myFilter);
                        return o;
                    }
                })
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler((req, resp, authentication)-> {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out=resp.getWriter();
                        User yg = (User) authentication.getPrincipal();
                        yg.setPassword(null);
                        String str = new ObjectMapper().writeValueAsString(RespBean.success(yg));
                        out.write(str);
                        out.flush();
                        out.close();
                })
                .failureHandler((req, resp, e)-> {
                       resp.setContentType("application/json;charset=utf-8");
                       PrintWriter out = resp.getWriter();
                       RespBean rb=null;
                       if(e instanceof BadCredentialsException){
                           rb =RespBean.fail(-1,"用户名或密码错误");
                       }else {
                           rb=RespBean.fail(-2,"登录中发生账户有关的异常，请和管理员联系",e);
                       }
                       String str = new ObjectMapper().writeValueAsString(rb);
                       out.write(str);
                       out.flush();
                       out.close();
                })
                .permitAll()
                .and()
                .cors()
                .and()
                .logout()
                .logoutSuccessHandler((req, resp, authentication)-> {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(RespBean.success()));
                        out.flush();
                        out.close();
                })
                .permitAll()
                .and()
                .csrf().disable().exceptionHandling()
                .authenticationEntryPoint((req, resp, e)->{
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(RespBean.fail(-3,"请先登录")));
                        out.flush();
                        out.close();
                })
                .accessDeniedHandler((req, resp, e)-> {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(RespBean.fail(-4,"没有对应权限，访问被拒绝")));
                        out.flush();
                        out.close();
                });
    }
}
