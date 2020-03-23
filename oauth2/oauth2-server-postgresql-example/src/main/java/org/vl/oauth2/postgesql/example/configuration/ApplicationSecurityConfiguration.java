package org.vl.oauth2.postgesql.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableResourceServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public GrantedAuthoritiesMapper authoritiesMapper(){
        SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
        authorityMapper.setConvertToUpperCase(true);
        authorityMapper.setDefaultAuthority("USER");
        return authorityMapper;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/css/*", "/js/*").permitAll()
                .antMatchers(POST, "/oauth/token").permitAll()
//                .antMatchers("/actuator", "/actuator/*").hasRole("GUESTS_AUTHORIZED_CLIENT")
//                .antMatchers("/actuator", "/actuator/*").hasRole("AUTHORIZED_READ")
//                .antMatchers("/actuator", "/actuator/*").hasAuthority("AUTHORIZED_READ")
//                .antMatchers("/actuator", "/actuator/*").hasAuthority("GUESTS_AUTHORIZED_CLIENT")
//                .antMatchers("/actuator", "/actuator/*").hasAuthority("ROLE_GUESTS_AUTHORIZED_CLIENT")
//                .antMatchers("/actuator", "/actuator/*").hasRole("AUTHORIZED_READ")
//                .antMatchers("/actuator", "/actuator/*").hasRole("CLIENT")
                .antMatchers("/actuator", "/actuator/*").hasRole("CLIENT")
                .antMatchers("/hello", "/hello/*").permitAll()
//                .antMatchers("/actuator", "/actuator/*").permitAll()
                .anyRequest().authenticated()

//                .and()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .and()
//                .logout().invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/logout-success").permitAll()
        ;
    }
}
