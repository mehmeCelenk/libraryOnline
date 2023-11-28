package com.book.libary.model.request;

import com.book.libary.util.PasswordUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    private String email;
    private String password;

    @JsonIgnore
    public String getEncodePasword(){
        return PasswordUtils.encode(password);
    }
}
