package com.challenge.notification.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.notification.model.Notification;
import com.challenge.notification.service.NotificationService;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> sendMessage(@RequestBody Notification notification) {
        log.info("Creating message: " + notification);
        notificationService.sendMessage(notification);
        return ResponseEntity.ok("Message created: " + notification);
    }

    @GetMapping("/v")
    public ResponseEntity<String> version() {
        log.info("Version: " + LocalDateTime.now());
        return ResponseEntity.ok("1.3");
    }
}
