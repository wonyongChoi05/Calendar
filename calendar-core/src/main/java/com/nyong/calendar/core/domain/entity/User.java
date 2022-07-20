package com.nyong.calendar.core.domain.entity;

import com.nyong.calendar.core.util.Encryptor;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "users")
@Getter
@Entity
public class User extends BaseEntity {

    private String name;

    private String email;

    private String password;

    private LocalDate birthday;

    public boolean isMatch(Encryptor encryptor, String password) {
        return encryptor.isMatch(password, this.password);
    }
}
