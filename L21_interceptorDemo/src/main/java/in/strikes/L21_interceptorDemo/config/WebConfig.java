package in.strikes.L21_interceptorDemo.config;

import in.strikes.L21_interceptorDemo.interceptor.AuthenticationInterceptor;
import in.strikes.L21_interceptorDemo.interceptor.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public LoggingInterceptor loggingInterceptor;
    public AuthenticationInterceptor authenticationInterceptor;

    public WebConfig(LoggingInterceptor loggingInterceptor,
                     AuthenticationInterceptor authenticationInterceptor) {

        this.loggingInterceptor = loggingInterceptor;
        this.authenticationInterceptor = authenticationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/auth/login", "api/public/**")
                .order(1);

        registry.addInterceptor(loggingInterceptor)
                .order(2);
    }
}
