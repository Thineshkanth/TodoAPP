package com.BackEnd.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDTO {

    private String username;
    private String email;
    private String password;

}
