package com.example.poslovna_informatika.security;

import com.example.poslovna_informatika.repositories.PreduzeceRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PreduzeceRepository preduzeceRepository;

    public WebSecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder, PreduzeceRepository preduzeceRepository) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.preduzeceRepository = preduzeceRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/", "/register", "/templates/navigation.html").permitAll()
                .antMatchers(HttpMethod.GET, "/api/mesto", "/api/pdv", "/api/stopa-pdv").permitAll()
                .antMatchers("/api/grupa-robe", "/api/jedinica-mere").permitAll()
                .antMatchers(HttpMethod.GET, "/api/preduzece").hasAuthority("administrator")
                .antMatchers(HttpMethod.POST, "/api/preduzece", "/api/mesto").hasAuthority("administrator")
                .antMatchers(HttpMethod.PUT, "/api/preduzece", "/api/mesto").hasAuthority("administrator")
                .antMatchers(HttpMethod.DELETE, "/api/preduzece", "/api/mesto").hasAuthority("administrator")
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), preduzeceRepository))
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/*.html", "/fonts/**");
    }
}
