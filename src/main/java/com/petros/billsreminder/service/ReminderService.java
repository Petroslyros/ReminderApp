package com.petros.billsreminder.service;

import com.petros.billsreminder.core.enums.ReminderType;
import com.petros.billsreminder.core.exceptions.ReminderNotFoundException;
import com.petros.billsreminder.core.exceptions.UserNotFoundException;
import com.petros.billsreminder.core.filters.Paginated;
import com.petros.billsreminder.core.filters.ReminderFilters;
import com.petros.billsreminder.core.filters.ReminderSpecification;
import com.petros.billsreminder.dto.ReminderInsertDTO;
import com.petros.billsreminder.dto.ReminderReadOnlyDTO;
import com.petros.billsreminder.mapper.Mapper;
import com.petros.billsreminder.model.Reminder;
import com.petros.billsreminder.model.User;
import com.petros.billsreminder.repository.ReminderRepository;
import com.petros.billsreminder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReminderService implements IReminderService {

    private final ReminderRepository reminderRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;

    @Override
    public ReminderReadOnlyDTO createReminder(@RequestBody ReminderInsertDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Reminder reminder= mapper.reminderInsertDTOToEntity(dto,user);
        reminder.setUser(user);

        Reminder savedReminder = reminderRepository.save(reminder);

        return mapper.toReadOnlyDTO(savedReminder);
    }

    @Override
    public List<ReminderReadOnlyDTO> getAllReminders() {
        return reminderRepository.findAll().stream()
                .map(mapper::toReadOnlyDTO)
                .toList();
    }

    public List<ReminderReadOnlyDTO> getReminderByUserId(Long userId) {
        List<Reminder> reminders = reminderRepository.findByUserId(userId);
        return reminders.stream()
                .map(mapper::toReadOnlyDTO)
                .toList();
    }

    public ReminderReadOnlyDTO updateReminder(Long id, ReminderInsertDTO dto) throws ReminderNotFoundException, UserNotFoundException {
        Reminder existingReminder = reminderRepository.findById(id)
                .orElseThrow(() -> new ReminderNotFoundException(id));

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserNotFoundException(dto.userId()));

        existingReminder.setTitle(dto.title());
        existingReminder.setType(dto.type());
        existingReminder.setDueDate(dto.dueDate());
        existingReminder.setNotes(dto.notes());
        existingReminder.setUser(user);

        Reminder updatedReminder = reminderRepository.save(existingReminder);
        return mapper.toReadOnlyDTO(updatedReminder);

    }

    public void deleteReminder(Long id) throws ReminderNotFoundException {
        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new ReminderNotFoundException(id));

        reminderRepository.delete(reminder);
    }

    public List<ReminderReadOnlyDTO> getRemindersByUserIdAndType(Long userId, ReminderType type) {
        List<Reminder> reminders = reminderRepository.findByUserIdAndType(userId, type);
        return reminders.stream()
                .map(mapper::toReadOnlyDTO)
                .toList();
    }

    public Page<ReminderReadOnlyDTO> getPaginatedReminders (int page, int size) {
        String defaultSort = "id";
        Pageable pageable = PageRequest.of(page, size, Sort.by(defaultSort).ascending());

        log.debug("Paginated reminders returned with page={} and size={}", page, size);

        return reminderRepository.findAll(pageable)
                .map(mapper::toReadOnlyDTO);

    }

    public Paginated<ReminderReadOnlyDTO> getRemindersFilteredPaginated(ReminderFilters reminderFilters) {
        var filtered = reminderRepository.findAll(
                getSpecsFromFilters(reminderFilters),
                reminderFilters.getPageable()
        );

        log.debug("Filtered reminders returned with page={} and size={}",
                reminderFilters.getPage(), reminderFilters.getPageSize());

        return new Paginated<>(filtered.map(mapper::toReadOnlyDTO));
    }

    private Specification<Reminder> getSpecsFromFilters(ReminderFilters reminderFilters) {
        return ReminderSpecification.typeIs(reminderFilters.getType())
                .and(ReminderSpecification.titleLike(reminderFilters.getTitle()));
    }


}
