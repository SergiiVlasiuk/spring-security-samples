package org.vl.oauth2.postgesql.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    /*
    see
    https://docs.spring.io/spring-security/site/docs/current/reference/html5/#servlet-authentication-userdetailsservice
     */
    @Bean
    @Profile("indatabase")
//    UserDetailsManager userDetailsManager(DataSource dataSource) {
    UserDetailsManager users(DataSource dataSource) {
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
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(user);
//        users.createUser(admin);
        return users;
    }


//    @Bean
//    JdbcClientDetailsService clientDetailsService() {
//        return new JdbcClientDetailsService(dataSource);
//    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(NoOpPasswordEncoder.getInstance())
                .checkTokenAccess("permitAll()")
                .tokenKeyAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
//                .jdbc(dataSource)
                .withClient("guest_app")
                .scopes("AUTHORIZED_READ", "READ_ALL_GUESTS", "WRITE_GUEST", "UPDATE_GUEST")
                .secret("secret")
                .autoApprove(true)
                .authorities("ROLE_GUESTS_AUTHORIZED_CLIENT", "ROLE_CLIENT")
                .authorizedGrantTypes("client_credentials")
                .and()
                .withClient("api_audit")
                .scopes("AUTHORIZED_READ", "READ_ALL_GUESTS")
                .secret("secret")
                .autoApprove(true)
                .authorities("ROLE_GUESTS_AUTHORIZED_CLIENT", "ROLE_CLIENT")
                .authorizedGrantTypes("client_credentials");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new InMemoryTokenStore());
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsServiceBean())
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }
}
