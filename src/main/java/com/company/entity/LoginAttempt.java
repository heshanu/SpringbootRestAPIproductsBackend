package com.company.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "login_attempts")
@AllArgsConstructor
@NoArgsConstructor
public class LoginAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(name = "attempt_timestamp")
    private LocalDateTime timestamp;

    private String ipAddress;
    private boolean success;
}
