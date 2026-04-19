package com.example.schedule_develop.comment.repository;

import com.example.schedule_develop.comment.entity.Comment;
import com.example.schedule_develop.enitity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    long countBySchedule(Schedule schedule);
}
