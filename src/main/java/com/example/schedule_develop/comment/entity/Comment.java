package com.example.schedule_develop.comment.entity;

import com.example.schedule_develop.enitity.BaseEntity;
import com.example.schedule_develop.enitity.Schedule;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String content;
    private String password;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Comment(String author, String content, String password, Schedule schedule) {
        this.author = author;
        this.content = content;
        this.password = password;
        this.schedule = schedule;
    }
}
