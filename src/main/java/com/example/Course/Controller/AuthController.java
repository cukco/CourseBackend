package com.example.Course.Controller;

import com.example.Course.Models.AppUser;
import com.example.Course.Models.Student;
import com.example.Course.Repositories.AppUserRepository;
import com.example.Course.Repositories.StudentRepository;
import com.example.Course.Security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static class AuthRequest {
        @jakarta.validation.constraints.NotBlank(message = "Name is required for registration")
        public String name;
        
        @jakarta.validation.constraints.NotBlank(message = "Email is mandatory")
        @jakarta.validation.constraints.Email(message = "Email should be valid")
        public String email;
        
        @jakarta.validation.constraints.NotBlank(message = "Password is required")
        public String password;
    }

    public static class AuthResponse {
        public String token;
        public String message;
        public String role;

        public AuthResponse(String token, String message, String role) {
            this.token = token;
            this.message = message;
            this.role = role;
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@jakarta.validation.Valid @RequestBody AuthRequest request) {
        if (userRepository.findByEmail(request.email).isPresent()) {
            return ResponseEntity.badRequest().body("Email này đã được sử dụng!");
        }

        AppUser newUser = new AppUser();
        newUser.setEmail(request.email);

        newUser.setPassword(passwordEncoder.encode(request.password));

        newUser.setRole("ROLE_STUDENT");

        userRepository.save(newUser);

        Student newStudent = new Student();
        newStudent.setEmail(request.email);
        newStudent.setName(request.name);
        studentRepository.save(newStudent);
        return ResponseEntity.ok("Đăng ký thành công!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@jakarta.validation.Valid @RequestBody AuthRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email, request.password)
        );

        String token = jwtUtils.generateToken(request.email);
        String role = auth.getAuthorities().iterator().next().getAuthority();

        return ResponseEntity.ok(new AuthResponse(token, "Đăng nhập thành công!", role));
    }
}