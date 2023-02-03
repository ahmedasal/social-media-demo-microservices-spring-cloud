package com.social.users.Service;

import com.social.users.keycloak.Credentials;
import com.social.users.keycloak.KeycloakConfig;
import com.social.users.keycloak.UserDTO;
import com.social.data.model.User;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class KeycloakService {
    @PersistenceContext
    EntityManager em;

    public void addUser(UserDTO userDTO){
        CredentialRepresentation credential = Credentials.createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmailId());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);
        user.setEmailVerified(false);

        //convert from userDto to userEntity
        User userEntity = UserDtoToEntity.converter(userDTO);
        em.persist(userEntity);
//        user.setId("555555555"); //TODO it didnt work

        UsersResource instance = getInstance();
        Response response=instance.create(user);


        System.out.println(user.getId());
        System.out.println(response.getStatus());
    }



    public List<UserRepresentation> getUser(String userName){
        UsersResource usersResource = getInstance();
        List<UserRepresentation> user = usersResource.search(userName, true);
        return user;

    }

    public void updateUser(String userId, UserDTO userDTO){
        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmailId());
        user.setCredentials(Collections.singletonList(credential));

        UsersResource usersResource = getInstance();
        usersResource.get(userId).update(user);
    }
    public void deleteUser(String userId){
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .remove();
    }


    public void sendVerificationLink(String userId){
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .sendVerifyEmail();
    }

    public void sendResetPassword(String userId){
        UsersResource usersResource = getInstance();

        usersResource.get(userId)
                .executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));
    }

    public UsersResource getInstance(){
        return KeycloakConfig.getInstance().realm(KeycloakConfig.realm).users();
    }


}
