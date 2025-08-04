package com.petros.billsreminder.controller;


import com.petros.billsreminder.dto.ReminderInsertDTO;
import com.petros.billsreminder.dto.ReminderReadOnlyDTO;
import com.petros.billsreminder.model.Reminder;
import com.petros.billsreminder.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class ReminderController {

    private final ReminderService service;

    @GetMapping("/users/{userId}/reminders")
    public ResponseEntity<List<ReminderReadOnlyDTO>> getRemindersPerUserId(@PathVariable Long userId) {
        List<ReminderReadOnlyDTO> reminders = service.getReminderByUserId(userId);
        return ResponseEntity.ok(reminders);
    }

    @PostMapping("/reminders")
    public ResponseEntity<ReminderReadOnlyDTO> createReminder(@RequestBody ReminderInsertDTO dto) {
        ReminderReadOnlyDTO savedReminder = service.createReminder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReminder);
    }

    @PutMapping("/reminders/{id}")
    public ResponseEntity<ReminderReadOnlyDTO> updateReminder(@PathVariable Long id
            , @RequestBody ReminderInsertDTO dto) {
        ReminderReadOnlyDTO updateReminder = service.updateReminder(id, dto);
        return ResponseEntity.ok(updateReminder);
    }

    @DeleteMapping("/reminders/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable Long id) {
        service.deleteReminder(id);
        return ResponseEntity.noContent().build();
    }



}
