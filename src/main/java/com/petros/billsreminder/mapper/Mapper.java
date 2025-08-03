package com.petros.billsreminder.mapper;

import com.petros.billsreminder.dto.ReminderInsertDTO;
import com.petros.billsreminder.dto.ReminderReadOnlyDTO;
import com.petros.billsreminder.dto.UserInsertDTO;
import com.petros.billsreminder.dto.UserReadOnlyDTO;
import com.petros.billsreminder.model.Reminder;
import com.petros.billsreminder.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Mapper {

    public User mapDtoToUserEntity(UserInsertDTO dto) {
        return new User(null, dto.getUsername(),dto.getEmail(),dto.getPassword(),dto.getFirstname(),dto.getLastname(),null,dto.getGender(),null);

    }

    public UserReadOnlyDTO mapUserEntityToReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                user.getGender(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public List<UserReadOnlyDTO> mapUserListToReadOnlyDTOList(List<User> users) {
        return users.stream()
                .map(this::mapUserEntityToReadOnlyDTO)
                .toList();
    }

    public Reminder mapDtoToReminderEntity(ReminderInsertDTO dto, User user) {
        return new Reminder(null,dto.getTitle(),dto.getType(),dto.getDueDate(),dto.getNotes(),null);
    }



}
