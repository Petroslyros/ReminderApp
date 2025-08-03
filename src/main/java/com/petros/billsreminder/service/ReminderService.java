package com.petros.billsreminder.service;

import com.petros.billsreminder.dto.ReminderInsertDTO;
import com.petros.billsreminder.dto.ReminderReadOnlyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderService implements IReminderService {

    @Override
    public ReminderReadOnlyDTO createReminder(ReminderInsertDTO dto) {
        return null;
    }

    @Override
    public List<ReminderReadOnlyDTO> getAllReminders() {
        return List.of();
    }
}
