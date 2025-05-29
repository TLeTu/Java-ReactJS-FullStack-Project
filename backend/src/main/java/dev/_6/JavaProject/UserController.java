package dev._6.JavaProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.BadCredentialsException;

@RestController
@RequestMapping("/auth")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserInfoService service;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserInfoService service, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @GetMapping("/user/userProfile")
    public String userProfile() {
        return "Welcome this is user profile";
    }

    @PostMapping("/addNewUser")
    public ResponseEntity<UserInfo> addNewUser(@RequestBody UserInfo userInfo) {
        return new ResponseEntity<>(
                service.addUser(userInfo),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/generateToken")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        if (authRequest == null || authRequest.getUsername() == null || authRequest.getUsername().trim().isEmpty() ||
                authRequest.getPassword() == null || authRequest.getPassword().trim().isEmpty()) {
            logger.error("Invalid AuthRequest: username or password is null or empty");
            return new ResponseEntity<>("Username and password cannot be empty", HttpStatus.BAD_REQUEST);
        }
        logger.debug("Authenticating user: " + authRequest.getUsername());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            if (authentication.isAuthenticated()) {
                logger.debug("Authentication successful for user: " + authRequest.getUsername());
                return new ResponseEntity<>(jwtService.generateToken(authRequest.getUsername()), HttpStatus.OK);
            } else {
                logger.warn("Authentication failed for user: " + authRequest.getUsername());
                return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);
            }
        } catch (BadCredentialsException e) {
            logger.warn("Bad credentials for user: " + authRequest.getUsername());
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            logger.error("Authentication error for user: " + authRequest.getUsername() + ": " + e.getMessage());
            return new ResponseEntity<>("Authentication error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}