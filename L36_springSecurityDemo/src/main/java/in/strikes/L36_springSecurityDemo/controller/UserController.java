package in.strikes.L36_springSecurityDemo.controller;

import in.strikes.L36_springSecurityDemo.dto.UserRegisterRequestDto;
import in.strikes.L36_springSecurityDemo.dto.UserRegisterResponseDto;
import in.strikes.L36_springSecurityDemo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(
            @RequestBody UserRegisterRequestDto registerRequestDto) {

        UserRegisterResponseDto userRegisterResponseDto =
                authService.register(registerRequestDto);

        return ResponseEntity.ok(userRegisterResponseDto);
    }

    @GetMapping("/toke")
    public CsrfToken getToken (CsrfToken csrfToken) {
        return csrfToken;
    }
}
