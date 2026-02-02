package com.adacapstone.seattleinyourlens.controller;

import com.adacapstone.seattleinyourlens.entity.User;
import com.adacapstone.seattleinyourlens.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:5173", "https://seattle-in-your-lens-frontend.vercel.app"})
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        return userRepository.findByUsername(loginRequest.getUsername())
                .filter(user -> user.getPassword().equals(loginRequest.getPassword()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
