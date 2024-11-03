package com.BackEnd.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsDTO {
    private int id;
    private String username;
    private String email;

    public UserDetailsDTO(int id, String username, String email ){

        this.id=id;
        this.username = username;
        this.email=email;
    }
}
