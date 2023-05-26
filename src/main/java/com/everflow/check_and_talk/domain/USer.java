package com.everflow.check_and_talk.domain;

// 회원정보 (디비,,, 미래의 나 파이팅!)

import com.everflow.check_and_talk.dto.Role;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class USer implements Serializable {

    @Id
    private Long id;
    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}
