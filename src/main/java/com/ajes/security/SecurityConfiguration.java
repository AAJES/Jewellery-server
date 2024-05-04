package com.ajes.security;


import com.ajes.service.UserService;
import com.ajes.utility.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserService userService;
	@Autowired
	private JwtFilter jwtFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

		authenticationManagerBuilder.userDetailsService(userService);

	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests()
		        .antMatchers("/jewellery/logIn1").permitAll()
				.antMatchers("/jewellery/home/").permitAll()
				.antMatchers("/jewellery/about/").permitAll()
		        .antMatchers("/jewellery/user/**").permitAll()
				.antMatchers("/jewellery/metal/**").permitAll()
				.antMatchers("/jewellery/purity/**").permitAll()
				.antMatchers("/jewellery/category/**").permitAll()
				.antMatchers("/jewellery/product/**").permitAll()
				.antMatchers("/jewellery/booking/**").permitAll()
				.antMatchers("/jewellery/bill/**").permitAll()
				.antMatchers("/jewellery/customer/**").permitAll()
				.antMatchers("/jewellery/employee/**").permitAll()
				.antMatchers("/jewellery/gender/**").permitAll()
				.antMatchers("/ams/**").permitAll()
				.antMatchers("/jewellery/license/**").permitAll()
				.antMatchers("/jewellery/modeOfPayment/**").permitAll()
				.antMatchers("/jewellery/owner/**").permitAll()
				.antMatchers("/jewellery/payment/**").permitAll()
				.antMatchers("/jewellery/productpurchase/**").permitAll()
				.antMatchers("/jewellery/rate/**").permitAll()
				.antMatchers("/jewellery/return/**").permitAll()
				.antMatchers("/jewellery/role/**").permitAll()
				.antMatchers("/jewellery/sales/**").permitAll()
				.antMatchers("/jewellery/scheme/**").permitAll()
				.antMatchers("/jewellery/schemeCustomerReceipt/**").permitAll()
				.antMatchers("/jewellery/schemeReceipts/**").permitAll()
				.antMatchers("/jewellery/shop/**").permitAll()
				.antMatchers("/jewellery/stock/**").permitAll()
				.antMatchers("/jewellery/idForm/**").permitAll();
		       // .anyRequest().authenticated();
		httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();

	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
