package com.esintcorp.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;

import com.esintcorp.security.authentication.CustomAuthenticationFailureHandler;
import com.esintcorp.security.authentication.CustomAuthenticationSuccessHandler;
import com.esintcorp.security.authentication.SystemUserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<CorsFilter>();
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:3000");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

//		config.addAllowedOrigin(Collections.singletonList(CorsConfiguration.ALL));
//        config.setAllowedHeaders(Collections.singletonList(CorsConfiguration.ALL));
//        config.setAllowedMethods(Collections.singletonList(CorsConfiguration.ALL));
//		source.registerCorsConfiguration("/**", config);
//		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean(new CorsFilter(source));
//		bean.setOrder(0);
//		return bean;

        filter.setFilter(new CorsFilter(r -> config));
        filter.setUrlPatterns(Collections.singleton("/*"));
        filter.setOrder(SecurityProperties.DEFAULT_FILTER_ORDER - 1);

        return filter;
	}
//	@Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**");
//            }
//        };
//    }
	
	@Autowired
	private SystemUserDetailsService systemUserDetailsService;
	
	
    /**
     * Configures the database authentication provider and the method we'll use to encrypt the passwords.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(systemUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Use the {@link BCryptPasswordEncoder} to encrypt passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	List<String> finalPublicUrls = Stream.of("/", "/login", "/register", "/subscription", "/payment").collect(Collectors.toList());

    	http
        .csrf()
        .requireCsrfProtectionMatcher(
        	new NoAntPathRequestMatcher(
        		Stream.of(finalPublicUrls/*, ""/*noneCsrfUrls*/)
        		.flatMap(Collection::stream)
        		.collect(Collectors.toList())
        	)
        );

        //Authorize all of the endpoints in the app except for these (which require public access)
        http.authorizeRequests().antMatchers(finalPublicUrls.toArray(new String[0])).permitAll().anyRequest().authenticated();
        //.antMatchers(finalPublicUrls.toArray(new String[0])).permitAll()
//        .anyRequest().authenticated();

        //Configure the login endpoint that will authorize users
        http.formLogin()
            .usernameParameter("userId")
            .passwordParameter("password")
            .loginPage("/login")
            .successHandler(customAuthenticationSuccessHandler)
            .failureHandler(customAuthenticationFailureHandler)
            .permitAll();

        //Does the logout only for POST requests, deleting cookies and with a success handler without a redirection.
        http.logout()
        	.deleteCookies("JSESSIONID")
        	.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.ACCEPTED));
    }

    /**
     * Specifies the authentication provider to use (based on our configuration above).
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
