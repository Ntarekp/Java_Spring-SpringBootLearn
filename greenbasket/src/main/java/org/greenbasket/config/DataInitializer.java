
package org.greenbasket.config;

import org.greenbasket.entity.User;
import org.greenbasket.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadAdminData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if an admin user already exists (adjust the condition as needed)
            if (!userRepository.existsByUsername("admin")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                // It is critical to encode the password using the PasswordEncoder bean
                admin.setPassword(passwordEncoder.encode("admin123"));
                // Assuming your User entity has an enum 'Role', e.g., User.Role.ADMIN
                admin.setRole(User.Role.ADMIN);

                userRepository.save(admin);
                System.out.println("Default admin user created with username 'admin' and password 'admin123'");
            }
        };
    }
}
