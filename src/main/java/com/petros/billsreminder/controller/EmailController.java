package com.petros.billsreminder.controller;

import com.petros.billsreminder.dto.EmailRequestDTO;
import com.petros.billsreminder.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO request) {
        emailService.sendEmail(request.getToEmail(),request.getSubject(),request.getBody());

        return ResponseEntity.ok("Email was sent successfully");

    }
}
