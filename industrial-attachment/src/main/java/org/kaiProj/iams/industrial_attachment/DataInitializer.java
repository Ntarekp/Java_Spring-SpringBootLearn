package org.kaiProj.iams.industrial_attachment;

import org.kaiProj.iams.industrial_attachment.entity.User;
import org.kaiProj.iams.industrial_attachment.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDefaultAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = User.builder()
                        .username("admin")
                        .email("admin@example.com")
                        .password(passwordEncoder.encode("admin123"))
                        .role("ADMIN")
                        .fullName("Administrator")
                        .approved(true)
                        .build();
                userRepository.save(admin);
            }
        };
    }
}