package in.strikes.L19_filterDemo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) servletRequest;

        HttpServletResponse httpResponse =
                (HttpServletResponse) servletResponse;

        String token = httpRequest.getHeader("token");
        String apikey = httpRequest.getHeader("x-api-key");

        if(token == null || !token.equals("12345")){
            //httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setStatus(401);

            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write(
                    "{\n" +
                            "    \"message\" : \"Invalid token\"\n" +
                            "}"
            );

            return;
        }

        if(apikey == null || !apikey.equals("secret123")){
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write(
                    "{\n" +
                            "    \"message\" : \"Invalid or missing API key\"\n" +
                            "}"
            );

            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
