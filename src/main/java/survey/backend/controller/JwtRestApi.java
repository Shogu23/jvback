package survey.backend.controller;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import survey.backend.exception.DisabledUserException;
import survey.backend.exception.InvalidUserCredentialsException;
import survey.backend.service.UserAuthService;
import survey.backend.util.JwtUtil;
import survey.backend.dto.Request;
import survey.backend.dto.Response;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class JwtRestApi {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("{id}")
    public User findOne(@PathVariable int id) {
        throw new RuntimeException("Method not implemented yet");
    }

    @PostMapping("/api/user/login")
    public ResponseEntity<Response> generateJwtToken(@RequestBody Request request) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getUserPass()));
        } catch (DisabledException e) {
            throw new DisabledUserException("User Inactive");
        } catch (BadCredentialsException e) {
            throw new InvalidUserCredentialsException("Invalid Credentials");
        }

        User user = (User) authentication.getPrincipal();
        Set<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

        String token = jwtUtil.generateToken(authentication);

        Response response = new Response();
        response.setToken(token);
        response.setRoles(new ArrayList<>(roles));

        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @PostMapping("/api/user/signup")
    public ResponseEntity<String> signup(@RequestBody Request request) {
        userAuthService.add(request);

        return new ResponseEntity<String>("User successfully registered", HttpStatus.OK);
    }

}