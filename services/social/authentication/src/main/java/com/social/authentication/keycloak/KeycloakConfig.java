package com.social.authentication.keycloak;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

public class KeycloakConfig {

    static Keycloak keycloak = null;
    final static String serverUrl = "http://localhost:8080/";
    public final static String realm = "social-media";
    final static String clientId = "backend";
    final static String clientSecret = "ZKvCmNMDKnj6eHJmzr4bsL8bAOXI95TD";
    final static String userName = "ahmed";
    final static String password = "root";


    public KeycloakConfig() {
    }

    public static Keycloak getInstance() {
        if (keycloak == null) {

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .build();

        }
        return keycloak;
    }
}