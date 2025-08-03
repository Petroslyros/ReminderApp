package com.petros.billsreminder.repository;

import com.petros.billsreminder.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepo extends JpaRepository<Reminder,Long> {

}
