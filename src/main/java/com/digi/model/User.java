package com.digi.model;

import com.digi.enums.Status;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    private int id;
    @Column(name = "first_name")
    private String name;
    @Column(name = "last_name")
    private String surname;
    private int year;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "verification_code")
    private String verificationCode;
    @Column(name = "reset_token")
    private String resetToken;
}
