package com.petros.billsreminder.service;

import com.petros.billsreminder.dto.ReminderInsertDTO;
import com.petros.billsreminder.dto.ReminderReadOnlyDTO;
import com.petros.billsreminder.model.Reminder;
import com.petros.billsreminder.model.User;
import com.petros.billsreminder.repository.ReminderRepo;
import com.petros.billsreminder.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderService implements IReminderService {

    private final ReminderRepo reminderRepo;
    private final UserRepo userRepo;

    @Override
    public ReminderReadOnlyDTO createReminder(ReminderInsertDTO dto) {
        return null;
    }

    @Override
    public List<ReminderReadOnlyDTO> getAllReminders() {
        return List.of();
    }

    public List<Reminder> getReminderByUserId(Long userId) {
        return reminderRepo.findByUserId(userId);
    }

    public Reminder saveReminder(ReminderInsertDTO dto) {
        User user = userRepo.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found"));

        Reminder reminder = new Reminder();
        reminder.setTitle(dto.getTitle());
        reminder.setType(dto.getType());
        reminder.setDueDate(dto.getDueDate());
        reminder.setNotes(dto.getNotes());
        reminder.setUser(user);

        return reminderRepo.save(reminder);
    }
}
