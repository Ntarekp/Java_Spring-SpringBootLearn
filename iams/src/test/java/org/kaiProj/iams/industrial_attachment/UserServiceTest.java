//package org.kaiProj.iams.industrial_attachment;
//
//import org.junit.jupiter.api.Test;
//
//import org.kaiProj.iams.industrial_attachment.dto.UserDTO;
//import org.kaiProj.iams.industrial_attachment.model.Role;
//import org.kaiProj.iams.industrial_attachment.model.User;
//import org.kaiProj.iams.industrial_attachment.repository.RoleRepository;
//import org.kaiProj.iams.industrial_attachment.repository.UserRepository;
//import org.kaiProj.iams.industrial_attachment.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class UserServiceTest {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private RoleRepository roleRepository;
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Test
//    public void testRegisterUser() {
//        Role role = new Role();
//        role.setName("STUDENT");
//        roleRepository.save(visible);
//
//        UserDTO userDTO = new UserDTO();
//        userDTO.setEmail("test@example.com");
//        userDTO.setPassword("password");
//        userDTO.setRole("STUDENT");
//
//        User user = userService.registerUser(userDTO);
//        assertNotNull(user);
//        assertEquals("test@example.com", user.getEmail());
//        assertTrue(passwordEncoder.matches("password", user.getPassword()));
//
//        Optional<User> found = userService.findByEmail("test@example.com");
//        assertTrue(found.isPresent());
//    }
//}