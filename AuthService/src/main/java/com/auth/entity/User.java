package com.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    private String userId;
    private String name;
    private String email;
    private String password;
    private String about;
}
