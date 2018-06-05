package com.nh.p2p;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	protected void configure(HttpSecurity http) throws Exception{
		http
					.authorizeRequests()
					//设置spring security对/和/login路径不拦截
					.antMatchers("/","/login").permitAll()
					.anyRequest().authenticated()
					.and()
					.formLogin()
					//spring security的登陆页面访问路径为/login
					.loginPage("/login")
					//登陆成功后转向/chat路径
					.defaultSuccessUrl("/chat")
					.permitAll()
					.and()
					.logout()
					.permitAll();
	}
	
	//在内存中分别配置两个用户wyf和wisely,密码和用户名一致,角色是USER
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
                .withUser("wyf").password("wyf").roles("USER").and()
                .withUser("wisely").password("wisely").roles("USER");
    }

	// /resources/static/目录下的静态资源，Spring Security不拦截
	public void configure(WebSecurity web)throws Exception{
			web.ignoring().antMatchers("/resources/static/**");
	}
}
