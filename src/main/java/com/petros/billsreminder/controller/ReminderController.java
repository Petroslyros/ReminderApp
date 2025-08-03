package com.petros.billsreminder.controller;


import com.petros.billsreminder.dto.ReminderInsertDTO;
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
    public ResponseEntity<List<Reminder>> getRemindersPerUserId(@PathVariable Long userId) {
        List<Reminder> reminders = service.getReminderByUserId(userId);
        return ResponseEntity.ok(reminders);
    }

    @PostMapping("/reminders")
    public ResponseEntity<Reminder> createReminder(@RequestBody ReminderInsertDTO dto) {
        Reminder savedReminder = service.saveReminder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReminder);
    }
}
