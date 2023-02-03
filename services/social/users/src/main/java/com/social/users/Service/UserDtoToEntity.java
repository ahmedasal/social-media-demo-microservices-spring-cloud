package com.social.users.Service;

import com.social.users.keycloak.UserDTO;
import com.social.data.model.User;

public class UserDtoToEntity {
    public static User converter(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstname());
        user.setSecondName(userDTO.getLastName());
        user.setEmail(userDTO.getEmailId());
//        user.setBirthday(userDTO.getBirthday());
        return user;
    }
}