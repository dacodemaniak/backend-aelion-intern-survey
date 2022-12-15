package survey.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import survey.backend.dto.UserRequestDto;
import survey.backend.entities.User;
import survey.backend.entities.UserRole;
import survey.backend.repository.UserRepository;
import survey.backend.repository.UserRoleRepository;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAuthService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        // Get User from it's login
        User user = userRepository.findByUserLogin(login).get();

        // List roles for Identified User
        List<UserRole> roles = user.getRoles().stream().toList();

        // Get Granted Authorities from UserRoles
        List<GrantedAuthority> grantedAuthorities = roles.stream().map(role -> {
            return new SimpleGrantedAuthority(role.getRole());
        }).collect(Collectors.toList());

        // Finally returns a full Core User
        return new org.springframework.security.core.userdetails.User(login, user.getUserPassword(), grantedAuthorities);
    }
    @Transactional
    public void add(UserRequestDto userDto) {
        if (this.userRepository.findByUserLogin((userDto.getUserLogin())).isPresent()) {
            // @todo Move to explicit exception (UserAlreadyExistsException)
            throw new RuntimeException("User already exists");
        }

        // Create a new instance of User
        User user = new User();
        user.setUserLogin(userDto.getUserLogin());
        user.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));

        this.userRepository.save(user);

        user.setRoles(userDto.getRoles().stream().map(r -> {
            UserRole ur = new UserRole();
            ur.setRole(r);
            ur.setUser(user);
            this.userRoleRepository.save(ur);
            return ur;
        }).collect(Collectors.toSet()));


    }
}
