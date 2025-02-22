package tech.duhacks.duhacks.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.duhacks.duhacks.model.User;
import tech.duhacks.duhacks.service.UserService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        userService.signUp(user);
        return ResponseEntity.ok("User sign up successfully");
    }

    @GetMapping("/signIn")
    public ResponseEntity<User> signIn(@RequestBody User user) {
        var User = userService.signIn(user);
        return ResponseEntity.ok(User);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok("User is Deleted with this id " + id );
    }

}
