package com.book.libary.model.response;


import com.book.libary.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String email;
    private Set<String> role;

    public UserResponse(final User user){
        email = user.getEmail();
        role = user.getRoles();

    }
}
