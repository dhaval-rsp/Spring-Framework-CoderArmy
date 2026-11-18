package in.strikes.L36_springSecurityDemo.service;

import in.strikes.L36_springSecurityDemo.dto.UserRegisterRequestDto;
import in.strikes.L36_springSecurityDemo.dto.UserRegisterResponseDto;
import in.strikes.L36_springSecurityDemo.entity.User;
import in.strikes.L36_springSecurityDemo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRegisterResponseDto register(
            UserRegisterRequestDto registerRequestDto){

        User user = new User();
        user.setUsername(registerRequestDto.getUsername());

        String encodedPassword =
                passwordEncoder.encode(registerRequestDto.getPassword());

        user.setPassword((encodedPassword));
        user.setEnabled(true);

        userRepository.save(user);

        UserRegisterResponseDto responseDto =
                new UserRegisterResponseDto();

        responseDto.setUsername(user.getUsername());
        responseDto.setMessage("User " + user.getUsername() + " saved successfully");

        return responseDto;
    }
}
