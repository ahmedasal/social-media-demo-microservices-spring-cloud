package com.social.authentication.keycloak;

import com.social.data.model.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String userName;
    private String emailId;
    private String password;
    private String firstname;
    private String lastName;
//    private String  birthday;

}