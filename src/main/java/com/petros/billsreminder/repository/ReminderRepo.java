package com.petros.billsreminder.repository;

import com.petros.billsreminder.core.enums.ReminderType;
import com.petros.billsreminder.dto.ReminderReadOnlyDTO;
import com.petros.billsreminder.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReminderRepo extends JpaRepository<Reminder,Long> {

    List<Reminder> findByUserId(Long userid);

    @Query("SELECT r FROM Reminder r WHERE r.user.id = :userId AND r.type = :type")
    List<Reminder> findByUserIdAndType(@Param("userId") Long userId, @Param("type") ReminderType type);

}
