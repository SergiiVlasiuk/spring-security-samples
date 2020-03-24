package org.vl.oauth2.postgesql.example.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@EnableAuthorizationServer
@Configuration
@Slf4j
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

//    @PostConstruct
//    private void moreConfigs() {
//        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
//        log.info("moreConfigs has been loaded");
//        BaseClientDetails guestApp = new BaseClientDetails();
//        guestApp.setClientId("guest_app");
//        guestApp.setClientSecret("secret");
//        guestApp.setScope(Arrays.asList("AUTHORIZED_READ", "READ_ALL_GUESTS", "WRITE_GUEST", "UPDATE_GUEST"));
//        // OAuth2UserAuthority, SimpleGrantedAuthority
//        // !!!! please pay attantion that OAuth2UserAuthority functionality is not supported by default
//        guestApp.setAuthorities(Arrays.asList(
//                new SimpleGrantedAuthority("ROLE_GUESTS_AUTHORIZED_CLIENT"),
//                new OAuth2UserAuthority("ROLE_CLIENT", Map.of(
//                        "k1", "v1",
//                        "k2", "v2"
//                ))));
//        guestApp.setAuthorizedGrantTypes(Collections.singletonList("client_credentials"));
//        BaseClientDetails apiAudit = new BaseClientDetails();
//        apiAudit.setClientId("api_audit");
//        apiAudit.setClientSecret("secret");
//        apiAudit.setScope(Arrays.asList("AUTHORIZED_READ", "READ_ALL_GUESTS"));
//        // OAuth2UserAuthority, SimpleGrantedAuthority
//        apiAudit.setAuthorities(Arrays.asList(
//                new SimpleGrantedAuthority("ROLE_GUESTS_AUTHORIZED_CLIENT"),
//                new SimpleGrantedAuthority("ROLE_CLIENT")));
//        apiAudit.setAuthorizedGrantTypes(Collections.singletonList("client_credentials"));
//        clientDetailsService.addClientDetails(guestApp);
//        clientDetailsService.addClientDetails(apiAudit);
//    }

    /*
    see
    https://docs.spring.io/spring-security/site/docs/current/reference/html5/#servlet-authentication-userdetailsservice
     */
    @Bean
//    UserDetailsManager userDetailsManager(DataSource dataSource) {
    UserDetailsManager users(DataSource dataSource) {
//        UserDetails guest_app = User.builder()
//                .username("guest_app")
////                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//                .password("secret")
//                .authorities("ROLE_GUESTS_AUTHORIZED_CLIENT", "ROLE_CLIENT")
//                .build();
//        UserDetails user = User.builder()
//                .username("user")
////                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//                .password("user")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
////                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//                .password("admin")
//                .roles("USER", "ADMIN")
//                .build();
//
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(guest_app);
//        users.createUser(user);
//        users.createUser(admin);
//        return users;

        return new JdbcUserDetailsManager(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(NoOpPasswordEncoder.getInstance())
                .checkTokenAccess("permitAll()")
                .tokenKeyAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new JdbcTokenStore(dataSource));
    }
}
