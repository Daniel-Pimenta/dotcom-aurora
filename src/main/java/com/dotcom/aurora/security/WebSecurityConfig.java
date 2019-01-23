package com.dotcom.aurora.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	@Value("${server.servlet.context-path}")
	private String path;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("WebSecurityConfig.configure(AuthenticationManagerBuilder auth)");
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("WebSecurityConfig.configure(HttpSecurity http)");
		log.info("configure(HttpSecurity http)");
    http
      //.csrf().disable()
    	.authorizeRequests()
    	  .antMatchers("/").permitAll()
    	  .antMatchers("/contato").permitAll()
    	  .antMatchers("/novousuario/**").permitAll()
    	  .antMatchers("/escola/**").hasRole("GESTOR")
    	  .antMatchers("/turma/**").hasRole("GESTOR")
    	  /*
      	.antMatchers(HttpMethod.GET, "/aurora/novousuario").permitAll()
      	.antMatchers(HttpMethod.GET, path + "novo_usuario").permitAll()
      	.antMatchers(HttpMethod.GET, path + "fragments").permitAll()
      	.antMatchers(HttpMethod.POST, path + "professor").hasRole("PROFESSOR")
      	.antMatchers(HttpMethod.POST,path + "aluno").hasRole("ALUNO")
      	.antMatchers(HttpMethod.POST,path + "responsavel").hasRole("RESPONSAVEIS")
      	.antMatchers(HttpMethod.POST,path + "admin").hasRole("ADMIN")
      	.antMatchers(HttpMethod.POST,path + "escola").hasRole("ESCOLA")
      	*/
      	.anyRequest().authenticated()
      	.and()
      .formLogin()
        .loginPage("/login")
        .usernameParameter("username")
        .passwordParameter("password")
        .loginProcessingUrl("/checar")
        //.defaultSuccessUrl("/", true)
        .failureUrl("/login?error")
      	.permitAll()
      	.and()
      .logout()
      	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
      	.logoutSuccessUrl("/")
    ;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		log.info("WebSecurityConfig.configure(WebSecurity web)");
    web.ignoring().antMatchers("/materialize/**","/style/**");
	}
	
}
