package com.petros.billsreminder.controller;


import com.petros.billsreminder.core.enums.ReminderType;
import com.petros.billsreminder.core.exceptions.ReminderNotFoundException;
import com.petros.billsreminder.core.exceptions.UserNotFoundException;
import com.petros.billsreminder.core.filters.Paginated;
import com.petros.billsreminder.core.filters.ReminderFilters;
import com.petros.billsreminder.dto.ReminderInsertDTO;
import com.petros.billsreminder.dto.ReminderReadOnlyDTO;
import com.petros.billsreminder.model.Reminder;
import com.petros.billsreminder.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
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
            , @RequestBody ReminderInsertDTO dto) throws UserNotFoundException, ReminderNotFoundException {
        ReminderReadOnlyDTO updateReminder = service.updateReminder(id, dto);
        return ResponseEntity.ok(updateReminder);
    }

    @DeleteMapping("/reminders/{userId}")
    public ResponseEntity<Void> deleteReminder(@PathVariable Long userId) throws ReminderNotFoundException {
        service.deleteReminder(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{userId}/reminders/filters")
    public ResponseEntity<List<ReminderReadOnlyDTO>> getRemindersByType(@PathVariable Long userId, @RequestParam ReminderType type) {

        List<ReminderReadOnlyDTO> reminders = service.getRemindersByUserIdAndType(userId, type);
        return ResponseEntity.ok(reminders);
    }

    @GetMapping("/reminders/paginated")
    public ResponseEntity<Page<ReminderReadOnlyDTO>> getPaginatedReminders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ReminderReadOnlyDTO> reminders = service.getPaginatedReminders(page, size);
        return ResponseEntity.ok(reminders);
    }

    /**
     * POST /api/reminders/filter
     * Returns filtered + paginated reminders based on filter body.
     */
    @PostMapping("/reminders/filter")
    public ResponseEntity<Paginated<ReminderReadOnlyDTO>> getFilteredAndPaginatedReminders(
            @Nullable @RequestBody ReminderFilters filters) {

        if (filters == null) filters = ReminderFilters.builder().build();

        Paginated<ReminderReadOnlyDTO> dtoPaginated = service.getRemindersFilteredPaginated(filters);
        return ResponseEntity.ok(dtoPaginated);
    }



}
