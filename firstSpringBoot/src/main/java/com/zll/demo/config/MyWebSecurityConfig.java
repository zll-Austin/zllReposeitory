package com.zll.demo.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	PasswordEncoder passwordEncoder() {
		// 非加密操作，官方已废弃
		//return NoOpPasswordEncoder.getInstance();
		
		//密码加密操作，密钥迭代次数  10 次
		return new BCryptPasswordEncoder(10);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("$2a$10$pU6Ju9p/IElwlgCW/qGEu.APpnCQpV3H8cHY.q5rGb6jNiUqaFUfe").roles("ADMIN", "USER").and().withUser("zll")
				.password("$2a$10$Uv2amRirVqrd3zP9Q0ts7.MCo30Tei/0pQE7gYLA9ZywWGIOypu9W").roles("USER").and().withUser("root").password("$2a$10$pU6Ju9p/IElwlgCW/qGEu.APpnCQpV3H8cHY.q5rGb6jNiUqaFUfe").roles("ADMIN","DBA");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/**")
				.hasRole("ADMIN")
				.antMatchers("/user/**")
				.access("hasAnyRole('ADMIN','USER')")
				.antMatchers("/db/**")
				.access("hasRole('ADMIN') and hasRole('DBA')")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/hello")
				.loginProcessingUrl("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.successHandler(new AuthenticationSuccessHandler() {
					
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						Object principal = authentication.getPrincipal();
						response.setContentType("application/json;charset=utf-8");
						PrintWriter out = response.getWriter();
						response.setStatus(200);
						Map<String, Object> map = new HashMap<>();
						map.put("status", 200);
						map.put("msg", principal);
						ObjectMapper om = new ObjectMapper();
						out.write(om.writeValueAsString(map));
						out.flush();
						out.close();
					}
				})
				.failureHandler(new AuthenticationFailureHandler() {
					
					@Override
					public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
							AuthenticationException exception) throws IOException, ServletException {
						response.setContentType("application/json;charset=utf-8");
						PrintWriter out = response.getWriter();
						response.setStatus(401);
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("status", 401);
						if(exception instanceof LockedException) {
							map.put("msg", "账户被锁定，登陆失败！！");
						}else if(exception instanceof BadCredentialsException) {
							map.put("msg", "账户名或密码输入错误，登陆失败！！");
						}else if(exception instanceof DisabledException) {
							map.put("msg", "账户被禁用，登陆失败！！");
						}else if(exception instanceof AccountExpiredException) {
							map.put("msg", "账户已过期，登陆失败！！");
						}else if(exception instanceof CredentialsExpiredException) {
							map.put("msg", "密码已过期，登陆失败！！");
						}else {
							map.put("msg", "登陆失败！！");
						}
						ObjectMapper om = new ObjectMapper();
						out.write(om.writeValueAsString(map));
						out.flush();
						out.close();
					}
				})
				.permitAll()
				.and()
				.logout()
				.logoutUrl("/logout")
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.addLogoutHandler(new LogoutHandler() {
					
					@Override
					public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
						
					}
				})
				.logoutSuccessHandler(new LogoutSuccessHandler() {
					
					@Override
					public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
							throws IOException, ServletException {
						response.sendRedirect("/books");
						
					}
				})
				.and()
				.csrf()
				.disable();
	}
}
