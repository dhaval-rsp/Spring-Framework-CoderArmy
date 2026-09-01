package in.coderarmy;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("in.coderarmy")

public class AppConfig {

    @Bean
    public User createUser() {
        return new User("Dhaval", 26);
    }
}
