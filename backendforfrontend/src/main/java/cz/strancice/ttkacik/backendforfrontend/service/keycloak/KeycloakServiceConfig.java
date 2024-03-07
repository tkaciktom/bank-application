package cz.strancice.ttkacik.backendforfrontend.service.keycloak;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@EqualsAndHashCode
public class KeycloakServiceConfig {
    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;
}
