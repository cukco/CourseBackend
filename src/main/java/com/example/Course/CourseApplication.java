package com.example.Course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.Course.Models.AppUser;
import com.example.Course.Repositories.AppUserRepository;

@SpringBootApplication
public class CourseApplication {
	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}

	@Bean
	public CommandLineRunner seedAdmin(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (userRepository.findByEmail("admin@myedu.com").isEmpty()) {
				AppUser admin = new AppUser();
				admin.setEmail("admin@myedu.com");
				admin.setPassword(passwordEncoder.encode("admin123"));
				admin.setRole("ROLE_ADMIN");
				userRepository.save(admin);
				System.out.println("Admin account created: admin@myedu.com / admin123");
			}
		};
	}
}
