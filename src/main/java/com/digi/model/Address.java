package com.digi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "address")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {
    @Id
    @Column(name = "address_id")
    private int id;
    private String country;
    private String city;
    private String street;
    @Column(name = "user_id")
    private int userId;
}
