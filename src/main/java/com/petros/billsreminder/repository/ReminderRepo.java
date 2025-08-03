package com.petros.billsreminder.repository;

import com.petros.billsreminder.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReminderRepo extends JpaRepository<Reminder,Long> {

        List<Reminder> findByUserId(Long userid);
}
