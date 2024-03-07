package cz.strancice.ttkacik.backendforfrontend.service.keycloak;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class KeycloakConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
