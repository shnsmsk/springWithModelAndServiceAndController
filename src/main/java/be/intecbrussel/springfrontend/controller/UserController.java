package be.intecbrussel.springfrontend.controller;

import be.intecbrussel.springfrontend.model.ChangePasswordRequest;
import be.intecbrussel.springfrontend.model.CreateUserRequest;
import be.intecbrussel.springfrontend.model.UserResponse;
import be.intecbrussel.springfrontend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create") // INSERT INTO users (username, password, email, role) VALUES (....)
    public void create(@RequestBody CreateUserRequest request) throws ResponseStatusException {
        /*
        * Example JSON Request:
        * {
        *     "username": "johndoe",
        *     "password": "12345678",
        *     "email": "john@doe.com"
        * }
        * */
        userService.create(request);
    }


    @PutMapping("/changePassword") // UPDATE users SET password = ... WHERE
    public boolean changePassword(@RequestBody ChangePasswordRequest request) {

        /*
         *  Example JSON Request:
         * {
         *     "username": "johndoe",
         *     "oldPassword": "12345678",
         *     "newPassword": "87654321"
         * }
         * */
        return userService.changePassword(request);
    }


    @PutMapping("/changePassword/{username}") // UPDATE users SET password = ... WHERE
    public boolean changePassword(@PathVariable String username, @RequestParam String oldPassword, @RequestParam String newPassword) {
        /*
        * Example queryString request: http://localhost:8080/users/changePassword?username=johndoe&oldPassword=12345&newPassword=54321
        * */
        return userService.changePassword(username, oldPassword, newPassword);
    }


    @PostMapping("/find/id/{id}") // SELECT * FROM users WHERE username = '...' AND password = 'My password here'
    public Optional<UserResponse> read(@PathVariable Long id) {
        /*
        *  Example Request: http://localhost:8080/users/find/id/1
        * */
        return userService.read(id);
    }


    @PostMapping("/find/username/{username}") // SELECT * FROM users WHERE username = '...'
    public Optional<UserResponse> readByUsername(@PathVariable String username) {

        /*
        * Example Request: http://localhost:8080/users/find/username/johndoe
        * */
        return userService.readByUsername(username);
    }


    @PostMapping("/login/username/{username}") // SELECT * FROM users WHERE username = '...' AND password = 'My password here'
    public Optional<UserResponse> readByUsernameAndPassword(@PathVariable String username, @RequestParam String password) {

        /*
        * Example Request: http://localhost:8080/users/login/username/johndoe?password=12345
         */
        return userService.readByUsernameAndPassword(username, password);
    }


    @PostMapping("/find/email/") // SELECT * FROM users WHERE email = '...'
    public Optional<UserResponse> readByEmailAndPassword(@RequestParam String email, @RequestParam String password) {

        /*
        * Example Request: http://localhost:8080/find/email/email=john@doe.com&password=12345
         */
        return userService.readByEmailAndPassword(email, password);
    }


    @PostMapping("/find/all") // SELECT * FROM users
    public List<UserResponse> readAll() {

        /*
        * Example Request: http://localhost:8080/users/find/all
         */
        return userService.readAll();
    }


    @PostMapping("/find/all/paged/{page}/{size}") // OR 'offset', 'limit' BETWEEN 1 AND Integer.MAX_VALUE
    public List<UserResponse> readAllInPages(@PathVariable Integer pageNo, @PathVariable String sortBy) {

        /*
        * Example Request: http://localhost:8080/users/find/all/paged/2/2
         */
        return userService.readAllInPages(pageNo, sortBy);
    }
}
