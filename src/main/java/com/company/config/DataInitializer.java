package com.company.config;

import com.company.entity.Role;
import com.company.entity.User;
import com.company.repo.RoleRepo;
import com.company.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@Configuration
public class DataInitializer {

    private final RoleRepo roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(RoleRepo roleRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        // Create roles if they don't exist
        createRoleIfNotFound(Role.ROLE_ADMIN);
        createRoleIfNotFound(Role.ROLE_USER);

        // Create admin user if not exists
        createAdminIfNotFound();
    }

    private void createRoleIfNotFound(String roleName) {
        if (roleRepository.findByName(roleName).isEmpty()) {
            Role role = Role.builder()
                    .name(roleName)
                    .build();
            roleRepository.save(role);
        }
    }
    private void createAdminIfNotFound() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = User.builder()
                    .username("admin")  // Must set username
                    .email("admin@example.com")  // Must set email
                    .password(passwordEncoder.encode("admin123"))
                    .enabled(true)
                    .build();

            Role adminRole = roleRepository.findByName(Role.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Admin role not found"));

            admin.addRole(adminRole);
            userRepository.save(admin);
        }
    }
}