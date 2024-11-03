package com.BackEnd.Controller;

import com.BackEnd.DTO.LoginRequestDTO;
import com.BackEnd.DTO.LoginResponseDTO;
import com.BackEnd.DTO.SignupRequestDTO;
import com.BackEnd.DTO.UserDetailsDTO;
import com.BackEnd.Entity.User;
import com.BackEnd.Service.UserService;
import com.BackEnd.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<UserDetailsDTO> signup(@RequestBody SignupRequestDTO signupRequestDTO) {

        UserDetailsDTO userDetailsDTO = userService.signup(signupRequestDTO);
        if (userDetailsDTO != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userDetailsDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        UserDetailsDTO userDetails = userService.login(loginRequestDTO);
        if (userDetails != null) {
            // Return success response with user details
            String jwtToken = jwtUtil.generateToken(userDetails);
            LoginResponseDTO response = new LoginResponseDTO("Login successful", true);
            response.setAccesstoken(jwtToken);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            // Return error response
            LoginResponseDTO response = new LoginResponseDTO("Invalid credentials", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return userService.logout();
    }

}
