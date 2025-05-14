package com.company.entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "security_logs")
public class SecurityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String eventType;
    private String ipAddress;

    @Column(name = "event_timestamp")
    private LocalDateTime timestamp;

    private String description;
    private String status;
}
