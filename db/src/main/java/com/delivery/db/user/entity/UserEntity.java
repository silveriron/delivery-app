package com.delivery.db.user.entity;

import com.delivery.db.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "users")
@NoArgsConstructor
@Getter
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @Column(nullable = false, unique = true, length = 100)
    private String name;
    @Column(nullable = false, length = 200)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column(nullable = false)
    private LocalDate birthDay;

    public UserEntity(String email, String name, String password, LocalDate birthDay) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = UserRole.USER;
        this.birthDay = birthDay;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}


