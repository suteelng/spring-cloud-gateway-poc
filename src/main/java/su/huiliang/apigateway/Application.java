package su.huiliang.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.security.oauth2.gateway.TokenRelayGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .httpBasic();
        return http.build();
    }
}
