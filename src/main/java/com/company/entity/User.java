package com.company.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        @Size(max = 50)
        @Column(nullable = false, length = 50)
        private String username;

        @NotBlank
        @Size(max = 100)
        @Column(nullable = false, length = 100)
        private String password;

        @NotBlank
        @Size(max = 100)
        @Email
        @Column(nullable = false, length = 100)
        private String email;

        @Builder.Default
        @Column(nullable = false)
        private boolean enabled = true;

        @Builder.Default
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<Role> roles = new HashSet<>();

        // Helper methods for roles management
        public void addRole(Role role) {
            this.roles.add(role);
            role.getUsers().add(this);
        }

        public void removeRole(Role role) {
            this.roles.remove(role);
            role.getUsers().remove(this);
        }

        // Custom toString() excluding sensitive data
        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    ", enabled=" + enabled +
                    '}';
        }
    }