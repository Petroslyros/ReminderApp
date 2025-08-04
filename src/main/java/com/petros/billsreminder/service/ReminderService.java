package com.petros.billsreminder.service;

import com.petros.billsreminder.dto.ReminderInsertDTO;
import com.petros.billsreminder.dto.ReminderReadOnlyDTO;
import com.petros.billsreminder.mapper.Mapper;
import com.petros.billsreminder.model.Reminder;
import com.petros.billsreminder.model.User;
import com.petros.billsreminder.repository.ReminderRepo;
import com.petros.billsreminder.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderService implements IReminderService {

    private final ReminderRepo reminderRepo;
    private final UserRepo userRepo;
    private final Mapper mapper;

    @Override
    public ReminderReadOnlyDTO createReminder(@RequestBody ReminderInsertDTO dto) {
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Reminder reminder= mapper.reminderInsertDTOToEntity(dto,user);
        reminder.setUser(user);

        Reminder savedReminder = reminderRepo.save(reminder);

        return mapper.toReadOnlyDTO(savedReminder);
    }

    @Override
    public List<ReminderReadOnlyDTO> getAllReminders() {
        return reminderRepo.findAll().stream()
                .map(mapper::toReadOnlyDTO)
                .toList();
    }

    public List<Reminder> getReminderByUserId(Long userId) {
        return reminderRepo.findByUserId(userId);
    }

    public ReminderReadOnlyDTO updateReminder(Long id, ReminderInsertDTO dto) {
        Reminder existingReminder = reminderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reminder not found"));

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingReminder.setTitle(dto.getTitle());
        existingReminder.setType(dto.getType());
        existingReminder.setDueDate(dto.getDueDate());
        existingReminder.setNotes(dto.getNotes());
        existingReminder.setUser(user);

        Reminder updatedReminder = reminderRepo.save(existingReminder);
        return mapper.toReadOnlyDTO(updatedReminder);

    }

    public void deleteReminder(Long id) {
        Reminder reminder = reminderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reminder Not Found"));

        reminderRepo.delete(reminder);
    }



}
