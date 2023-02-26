package pl.britenet.springbootstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.springbootstore.model.LoginCredentials;
import pl.britenet.springbootstore.model.LoginResponse;
import pl.britenet.springbootstore.service.AuthService;
import pl.britenet.store.model.Customer;

@CrossOrigin("http://127.0.0.1:5500")
@RestController
@RequestMapping("api/v2/auth")//   /api/v2/auth   <- działało poprzednio
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public boolean isLoggedIn(@RequestHeader("Authorization")String token){
        return this.authService.isLoggedIn(token);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginCredentials loginCredentials){
        return this.authService.login(loginCredentials);
    }

    @PostMapping("/register")
    public void register(@RequestBody Customer customer){
        this.authService.register(customer);
    }
}
