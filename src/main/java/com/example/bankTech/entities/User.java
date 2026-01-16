package com.example.bankTech.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String document;

    private String password;

    private BigDecimal ballance = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public BigDecimal getBallance() {
        return ballance;
    }

    public void setBallance(BigDecimal ballance) {
        this.ballance = ballance;
    }
}
