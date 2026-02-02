package com.adacapstone.seattleinyourlens;

import com.adacapstone.seattleinyourlens.repository.UserRepository;
//import org.apache.catalina.User;
import com.adacapstone.seattleinyourlens.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SeattleInYourLensApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeattleInYourLensApplication.class, args);
    }


    @Bean
    CommandLineRunner initUser(UserRepository userRepository) {
        return args -> {
            // only create this mock user Seattle1, assume
            if (userRepository.count() == 0) {
                User mockUser = new User();
                mockUser.setUsername("Seattle1");
                mockUser.setPassword("password123");
                userRepository.save(mockUser);
            }

            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("admin123");
                admin.setRole("ADMIN");
                userRepository.save(admin);
            }
        };
    }
}
