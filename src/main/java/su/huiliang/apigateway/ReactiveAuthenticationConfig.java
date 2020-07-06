package su.huiliang.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManagerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

import java.util.Collections;

@Configuration
public class ReactiveAuthenticationConfig {

    private String ldapUrl = "ldaps://localhost:689";

    @Bean
    ReactiveAuthenticationManager authenticationManager() {
        ActiveDirectoryLdapAuthenticationProvider adlap =
                new ActiveDirectoryLdapAuthenticationProvider("corp.ad.su.net", ldapUrl);
        AuthenticationManager authenticationManager = new ProviderManager(Collections.singletonList(adlap));
        return new ReactiveAuthenticationManagerAdapter(authenticationManager);
    }

}
