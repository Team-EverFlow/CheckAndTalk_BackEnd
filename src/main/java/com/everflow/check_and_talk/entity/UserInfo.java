package com.everflow.check_and_talk.entity;

import com.everflow.check_and_talk.enums.ProviderEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private String email;

    @Enumerated(EnumType.STRING)
    private ProviderEnum provider;

    private String providerId;

    private String username;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
