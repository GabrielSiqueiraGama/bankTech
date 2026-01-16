package com.example.bankTech.entities;

import jakarta.persistence.*;

@Entity
@Table(name="tb_user_type")
public class UserType {

    @Id
    private Long id;

    private String description;

    public UserType(){}

    public UserType(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public enum enumUserType{

        USER(1L, "user"), MERCHANT(2L, "merchant");

        enumUserType(Long id, String description) {
            this.id = id;
            this.description = description;
        }

        private Long id;
        private String description;

        public UserType get(){
            return new UserType(id, description);
        }
    }
}
