package com.skill_swap.skill_swap.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Sends a simple text notification to a specific user.
     *
     * @param email Recipient username
     * @param message  Notification message
     */
    public void sendNotificationToUser(String email, String message) {
        messagingTemplate.convertAndSend("/topic/notifications/" + email, message);
    }

    /**
     * Sends a structured JSON notification to a specific user.
     * Useful if you want to include multiple fields (from, type, skillId, etc.)
     *
     * @param username Recipient username
     * @param payload  Notification payload
     */
    public void sendNotificationToUser(String username, NotificationPayload payload) {
        messagingTemplate.convertAndSend("/topic/notifications/" + username, payload);
    }

    /**
     * DTO representing structured notifications.
     */
    public static class NotificationPayload {
        private String from;
        private String message;
        private String skillId; // Optional, can be null

        public NotificationPayload(String from, String message, String skillId) {
            this.from = from;
            this.message = message;
            this.skillId = skillId;
        }

        // Getters and setters
        public String getFrom() { return from; }
        public void setFrom(String from) { this.from = from; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }

        public String getSkillId() { return skillId; }
        public void setSkillId(String skillId) { this.skillId = skillId; }
    }
}