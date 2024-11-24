package com.newblog.controller;

import com.newblog.dto.LoginDto;
import com.newblog.dto.SignUp;
import com.newblog.entity.User;
import com.newblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //http://localhost:8080/api/auth/sign-up

    @PostMapping("/sign-up")
    public ResponseEntity<String> createUser(@RequestBody SignUp signUp) {
        if (userRepository.existsByEmail(signUp.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (userRepository.existsByUsername(signUp.getUsername())) {
            return new ResponseEntity<>("user name already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        User user = new User();
        user.setName(signUp.getName());
        user.setEmail(signUp.getEmail());
        user.setUsername(signUp.getUsername());
        user.setPassword(passwordEncoder.encode(signUp.getPassword()));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully",HttpStatus.CREATED);

    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> SignIn(@RequestBody LoginDto loginDto){

        //1.supply loginDto.getUsername() to loadByUser method in CustomUserDetailService class
        //It will compare
        //Expected credentials loginDto.getUsername(), loginDto.getPassword()
        //with actual credentials given by load by user method this happens automatically
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //similar to session variable
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed in successfully",HttpStatus.OK);
    }
}




