package cz.strancice.ttkacik.backendforfrontend.service.keycloak;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KeycloakService {

    private final KeycloakServiceConfig config;
    private final RestTemplate restTemplate;

    public String getJwtToken(String username, String password) {
        var tokenEndpoint = "%s/realms/%s/protocol/openid-connect/token".formatted(config.getKeycloakServerUrl(), config.getRealm());
        var requestBody = "client_id=%s&client_secret=%s&username=%s&password=%s&grant_type=password"
                .formatted(config.getClientId(), config.getClientSecret(), username, password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(tokenEndpoint, request, String.class);

        return response.getBody();
    }
}
