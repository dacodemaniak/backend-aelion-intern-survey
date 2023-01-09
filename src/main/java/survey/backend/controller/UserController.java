package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import survey.backend.components.JwtUtil;
import survey.backend.dto.SignupMessage;
import survey.backend.dto.UserRequestDto;
import survey.backend.dto.UserResponseDto;
import org.springframework.security.core.userdetails.User;
import survey.backend.error.jwt.DisabledUserException;
import survey.backend.error.jwt.InvalidCredentialsException;
import survey.backend.service.UserAuthService;

import java.util.Set;
import java.util.stream.Collectors;
@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserAuthService userAuthService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("{id}")
    public User findOne(@PathVariable int id) {
        throw new RuntimeException("Method not implemented yet");
    }

    @PostMapping("signin")
    public UserResponseDto generateJwtToken(@RequestBody UserRequestDto request) {
        try {
            Authentication  authentication = this.authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUserLogin(),
                                    request.getUserPassword()
                            )
                    );
            // Got a Spring Security User
            User user = (User) authentication.getPrincipal();

            Set<String> roles = user
                    .getAuthorities()
                    .stream().map(r -> r.getAuthority())
                    .collect(Collectors.toSet());

            // Make a token from "authentication" object
            String token = jwtUtil.generateToken(authentication);

            // Create a Response DTO to send to client
            UserResponseDto response = new UserResponseDto();
            response.setJwtToken(token);
            response.setRoles(roles.stream().collect(Collectors.toList()));

            return response;

        } catch (DisabledException e) {
            throw new DisabledUserException();
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException();
        }
    }


    @PostMapping("signup")
    public SignupMessage signup(@RequestBody UserRequestDto request) {
        this.userAuthService.add(request);
        SignupMessage message = new SignupMessage("User was successfully registred");
        return message;
    }

}
