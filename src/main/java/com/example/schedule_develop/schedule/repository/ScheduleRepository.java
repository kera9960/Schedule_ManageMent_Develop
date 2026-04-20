package com.example.schedule_develop.schedule.repository;

import com.example.schedule_develop.schedule.enitity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}
