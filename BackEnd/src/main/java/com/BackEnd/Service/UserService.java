package com.BackEnd.Service;

import com.BackEnd.DTO.LoginRequestDTO;
import com.BackEnd.DTO.SignupRequestDTO;
import com.BackEnd.DTO.UserDetailsDTO;
import com.BackEnd.Entity.User;
import com.BackEnd.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();


    //Signup
    public UserDetailsDTO signup(SignupRequestDTO signupRequestDTO) {
        if (userRepo.findByEmail(signupRequestDTO.getEmail())!=null) {
            return null;
        } else {
            User user=new User();
            user.setUsername(signupRequestDTO.getUsername());
            user.setEmail(signupRequestDTO.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(signupRequestDTO.getPassword()));
            userRepo.save(user);
            return new UserDetailsDTO(user.getId(),user.getUsername(),user.getEmail());
        }
    }

    //Login
    public UserDetailsDTO login(LoginRequestDTO loginRequestDTO){
        User user = userRepo.findByEmail(loginRequestDTO.getEmail());
        if(user==null){
            return null;
        }
        else if (!bCryptPasswordEncoder.matches(loginRequestDTO.getPassword(),user.getPassword())){
            return null;
        }
        else {

            return new UserDetailsDTO(user.getId(),user.getUsername(),user.getEmail());

        }
    }

    //Logout
    public ResponseEntity<String> logout(){
        return new ResponseEntity<>("Sucessfully LogOut", HttpStatus.OK);
    }


    //Userupdate
    public  UserDetailsDTO userUpdate( int id,UserDetailsDTO userDetails){


            Optional<User> existinguser = userRepo.findById((long) id);
            if (existinguser.isPresent()) {
                User updateuser = existinguser.get();
                updateuser.setUsername(userDetails.getUsername());
                updateuser.setEmail(userDetails.getEmail());
                userRepo.save(updateuser);
                return new UserDetailsDTO(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail());
            } else {
                throw new RuntimeException("User not found with id: " + id);
            }

    }

    //getuserdetails
    public List<User> getAllUsers() {
            return userRepo.findAll();
        }

        //delete user using id
        public void deleteUser(int id) {
            if (userRepo.existsById((long) id)) {
                userRepo.deleteById((long) id);
            } else {
                throw new RuntimeException("User not found with id: " + id);
            }
        }

}
