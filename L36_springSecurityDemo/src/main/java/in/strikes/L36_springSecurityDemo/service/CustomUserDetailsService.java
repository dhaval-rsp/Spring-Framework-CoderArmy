package in.strikes.L36_springSecurityDemo.service;

import in.strikes.L36_springSecurityDemo.entity.CustomUserDetails;
import in.strikes.L36_springSecurityDemo.entity.User;
import in.strikes.L36_springSecurityDemo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException(
                        "USER NOT FOUND"
                ));


        return new CustomUserDetails(user);
    }
}
