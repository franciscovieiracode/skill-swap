package com.skill_swap.skill_swap.services;

import com.skill_swap.skill_swap.dto.CreateRequestDto;
import com.skill_swap.skill_swap.dto.SkillsResponseDto;
import com.skill_swap.skill_swap.enums.RequestStatus;
import com.skill_swap.skill_swap.exceptions.SkillException;
import com.skill_swap.skill_swap.models.Skill;
import com.skill_swap.skill_swap.models.SkillRequest;
import com.skill_swap.skill_swap.models.User;
import com.skill_swap.skill_swap.models.UserOfferedSkill;
import com.skill_swap.skill_swap.repositories.SkillRequestRepository;
import com.skill_swap.skill_swap.repositories.SkillsRepository;
import com.skill_swap.skill_swap.repositories.UserOfferedSkillsRepository;
import com.skill_swap.skill_swap.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class SkillRequestService {

    @Autowired
    SkillRequestRepository requestRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserOfferedSkillsRepository userOfferedSkillsRepository;

    @Autowired
    NotificationService notificationService;



    public SkillsResponseDto addRequest(CreateRequestDto requestDto, UserDetails userDetails) {


        SkillRequest skillRequest = new SkillRequest();

        User userRequesting = userRepository.findByEmail(userDetails.getUsername());

        if (userRequesting == null)
            throw new UsernameNotFoundException("Invalid User");

        Optional<User> userReceipting = userRepository.findById(requestDto.receiptingId());

        if (userReceipting.isEmpty())
            throw new UsernameNotFoundException("Invalid User");

        Optional<UserOfferedSkill> skill = userOfferedSkillsRepository.findById(UUID.fromString(requestDto.skillId()));

        if (skill.isEmpty())
            throw new SkillException("Skill does not exist");

        skillRequest.setRequester(userRequesting);
        skillRequest.setRecipient(userReceipting.get());
        skillRequest.setUserOfferedSkill(skill.get());
        skillRequest.setLocalDate(LocalDate.now());
        skillRequest.setRequestStatus(RequestStatus.PENDING);

        requestRepository.save(skillRequest);


        notificationService.sendNotificationToUser(
                userReceipting.get().getEmail(),
                userRequesting.getName() + " has requested your skill"
        );

        return new SkillsResponseDto("Request made");
    }

}
