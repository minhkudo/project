/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 *
 * @author MinhKudo
 */
@Configuration
@EnableWebSecurity
public class WebMvcConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource1Config dataSource1;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

//
////    @Autowired
////    private UserDetailsServiceImpl userDetailsService;
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        return bCryptPasswordEncoder;
//    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//        // Sét đặt dịch vụ để tìm kiếm User trong Database.
//        // Và sét đặt PasswordEncoder.
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//
//    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*"); // Có thể chỉ định rõ: https://techmaster.vn
        config.addAllowedHeader("*"); // Có thể chỉ định rõ: Arrays.asList("authorization", "content-type", "x-auth-token")
        config.addAllowedMethod("*"); // Có thể chỉ định rõ: Arrays.asList("GET", "POST", "PUT", "DELETE")
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//
        http.csrf().disable();
//
////        // Các trang không yêu cầu login
        http.authorizeRequests().antMatchers("/loginTeach", "/loginAdmin", "/loginStudent").permitAll()
                .antMatchers("/logoutTeach", "/logoutAdmin", "/logoutStudent").permitAll()
                .antMatchers(HttpMethod.POST, "/loginTeach", "/loginAdmin", "/loginStudent").permitAll()
                .antMatchers(HttpMethod.GET,"/pageAdmin/**").permitAll()
                .antMatchers(HttpMethod.GET,"/pageTeach/**").permitAll()
                .antMatchers(HttpMethod.GET,"/pageStudent/**").permitAll()
                .antMatchers("/pageAdmin/**").hasRole("ADMIN")
                .antMatchers("/pageTeach/**").hasRole("USER")
                .antMatchers("/pageStudent/**").hasRole("MEMBER")
                .anyRequest().authenticated() // Các request còn lại đều cần được authenticated
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .addFilter(new ApiJWTAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //  
        // Trang /userInfo yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
        //        // Nếu chưa login, nó sẽ redirect tới trang /login.
        //        http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
        //
        //        // Trang chỉ dành cho ADMIN
        //        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
        //
        //        // Khi người dùng đã login, với vai trò XX.
        //        // Nhưng truy cập vào trang yêu cầu vai trò YY,
        //        // Ngoại lệ AccessDeniedException sẽ ném ra.
        //        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        // Cấu hình cho Login Form.
        //        http.authorizeRequests().and().formLogin()//
        //                // Submit URL của trang login
        //                .loginProcessingUrl("/j_spring_security_check") // Submit URL
        //                .loginPage("/login")//
        //                .defaultSuccessUrl("/userAccountInfo")//
        //                .failureUrl("/login?error=true")//
        //                .usernameParameter("username")//
        //                .passwordParameter("password")
        //                // Cấu hình cho Logout Page.
        //                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
        // Cấu hình Remember Me.
        //        http.authorizeRequests().and() //
        //                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
        //                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
        //        Minh Kudo
    }

//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
//        db.setDataSource(dataSource);
//        return db;
//    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        // Vô hiệu hóa toàn bộ bảo mật đối với các request vào các đường dẫn sau
        // Không phải trải qua filter
        web.ignoring().antMatchers(
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/css/**",
                "/img/**",
                "/images/**",
                "/js/**"
        );
    }
}
