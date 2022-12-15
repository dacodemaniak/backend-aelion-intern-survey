package survey.backend.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import survey.backend.error.jwt.JwtTokenMissingException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import survey.backend.components.JwtUtil;
import survey.backend.service.UserAuthService;
import org.springframework.security.core.userdetails.UserDetails;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserAuthService userAuthService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get HTTP Header
        String header = request.getHeader("Authorization");

        // Is a bearer ?
        if (header == null || !header.startsWith("bearer")) {
            try {
                throw new JwtTokenMissingException();
            } catch (JwtTokenMissingException e) {
                throw new RuntimeException(e);
            }
        }

        String token = header.substring("bearer".length() + 1);

        String userName = jwtUtil.getUserLogin(token);

        UserDetails userDetails = userAuthService.loadUserByUsername(userName);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}
