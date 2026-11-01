package in.strikes.L21_interceptorDemo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    public AuthenticationInterceptor() {
        System.out.println("AuthenticationInterceptor Loaded");
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        String apikey = request.getHeader("x-api-key");

        System.out.println("PREHANDLE EXECUTED");
        System.out.println("API KEY :" + apikey);

        if(apikey != null && !apikey.equals("secret123")){
            return false;
        }
        return true;
    }
}
