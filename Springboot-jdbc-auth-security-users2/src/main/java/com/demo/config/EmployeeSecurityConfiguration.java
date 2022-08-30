package com.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@EnableWebSecurity
public class EmployeeSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/resources/**");
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests().antMatchers("/").permitAll()
	        .antMatchers("/register").permitAll()
	            .antMatchers("/welcome")
	            .hasAnyRole("USER", "ADMIN","MANAGER").antMatchers("/getEmployees").hasAnyRole("USER", "ADMIN")
	            .antMatchers("/addNewEmployee").hasAnyRole("ADMIN").anyRequest().authenticated().and().formLogin()
	            .and().formLogin().loginPage("/login")
	            .permitAll().and().logout().permitAll();

	        http.csrf().disable();
	    }

	   /* @Autowired
	   public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
	        authenticationMgr.inMemoryAuthentication().withUser("employee").password("employee")
	            .authorities("ROLE_USER").and().withUser("admin").password("admin")
	            .authorities("ROLE_USER", "ROLE_ADMIN");
	    }*/
	    @Autowired
	    DataSource datasource;
	    @Autowired
	    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
	    	auth.jdbcAuthentication().dataSource(datasource);
	    }
	    @Bean
	     public JdbcUserDetailsManager jdbcUserDetailsManager() throws Exception {
	    	 JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
	    	 jdbcUserDetailsManager.setDataSource(datasource);
	    	 return jdbcUserDetailsManager;
	    	 
	     }

}
