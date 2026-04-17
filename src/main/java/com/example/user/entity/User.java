package com.example.user.entity;

import com.example.schedule_develop.enitity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    private Long id;
    private String userName;
    private String email;

    public User(String userName,String email){
        this.userName = userName;
        this.email = email;
    }

    public void update(String userName,String email){
        this.userName = userName;
        this.email = email;
    }
}
