package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.UserRequestDto;
import survey.backend.entities.User;
import survey.backend.service.UserAuthService;

@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserAuthService userAuthService;

    @GetMapping("{id}")
    public User findOne(@PathVariable int id) {
        throw new RuntimeException("Method not implemented yet");
    }

    @PostMapping()
    public User add(@RequestBody UserRequestDto userDto) {
        // @todo Returns only a UserResponseDto
        return userAuthService.add(userDto);
    }

}
