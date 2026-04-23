package com.example.schedule_develop.user.entity;

import com.example.schedule_develop.schedule.enitity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    @Column(unique = true)
    private String email;
    private String password;

    public User(String userName,String email,String password){
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void update(String userName,String email){
        this.userName = userName;
        this.email = email;
    }
}
