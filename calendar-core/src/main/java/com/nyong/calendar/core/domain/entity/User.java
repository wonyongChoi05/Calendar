package com.nyong.calendar.core.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "users")
@Getter
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String email;

    private String password;

    private LocalDateTime birthday;

    private LocalDateTime createAt = LocalDateTime.now();

}
