package in.strikes.L36_springSecurityDemo.service;

import in.strikes.L36_springSecurityDemo.dto.UserRegisterRequestDto;
import in.strikes.L36_springSecurityDemo.dto.UserRegisterResponseDto;
import in.strikes.L36_springSecurityDemo.entity.Role;
import in.strikes.L36_springSecurityDemo.entity.User;
import in.strikes.L36_springSecurityDemo.repository.RoleRepository;
import in.strikes.L36_springSecurityDemo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRegisterResponseDto register(
            UserRegisterRequestDto registerRequestDto){

        User user = new User();
        user.setUsername(registerRequestDto.getUsername());

        // create encoded password from request dto
        String encodedPassword =
                passwordEncoder.encode(registerRequestDto.getPassword());

        // setting encoded password + enabling user
        user.setPassword((encodedPassword));
        user.setEnabled(true);

        // default role is user | creating its object
        Role role = roleRepository.findByName("ROLE_USER").get();

        // adding role object into entity.user.roles [which is hashset in our user class]
        user.getRoles().add(role);

        userRepository.save(user);

        // creating response dto so that we can print message for user that use has been added
        UserRegisterResponseDto responseDto =
                new UserRegisterResponseDto();

        responseDto.setUsername(user.getUsername());
        responseDto.setMessage("User " + user.getUsername() + " saved successfully");

        return responseDto;
    }

    // USER LOGIn
}
