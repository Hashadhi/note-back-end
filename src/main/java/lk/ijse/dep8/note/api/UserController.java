package lk.ijse.dep8.note.api;


import lk.ijse.dep8.note.dto.UserDTO;
import lk.ijse.dep8.note.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public UserDTO registerUser(@RequestBody @Validated UserDTO user, Errors errors) {
        if (errors.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldErrors().get(0).getDefaultMessage());
        }
        return userService.registerUser(user);

    }

    @GetMapping(path = "/{userId:[A-Fa-z-0-9\\-]{36}}", produces = "application/json")
    public UserDTO getUserInfo(@PathVariable String userId) {

        return userService.getUserInfo(userId);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(path = "/{userId:[A-Fa-z-0-9\\-]{36}}", consumes = "application/json")
    public void updateUserInfo(@PathVariable String userId, @RequestBody @Validated UserDTO user) {
        user.setId(userId);
        userService.updateUser(user);


    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{userId:[A-Fa-z-0-9\\-]{36}}")
    public void deleteUser(@PathVariable String userId) {

        userService.deleteUser(userId);

    }
}
