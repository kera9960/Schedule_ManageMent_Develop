package com.example.schedule_develop.comment.repository;

import com.example.schedule_develop.comment.entity.Comment;
import com.example.schedule_develop.schedule.enitity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentsBySchedule(Schedule schedule);
}
