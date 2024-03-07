package cz.strancice.ttkacik.backendforfrontend.service.user;

import cz.strancice.ttkacik.backendforfrontend.rest.api.UsersApiDelegate;
import cz.strancice.ttkacik.backendforfrontend.rest.model.UsersLoginPostRequest;
import cz.strancice.ttkacik.backendforfrontend.service.keycloak.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UsersApiDelegate {

    private final KeycloakService keycloakService;

    @Override
    public ResponseEntity<String> usersLoginPost(UsersLoginPostRequest usersLoginPostRequest) {
        var token = keycloakService.getJwtToken(usersLoginPostRequest.getUsername(), usersLoginPostRequest.getPassword());
        return ResponseEntity.ok(token);
    }
}
