package com.timesheet.controller;

import com.timesheet.dto.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Objects;
import java.util.logging.Logger;

@Controller
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ChatController {

    private final Logger log = Logger.getLogger(ChatController.class.getName());

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        log.info("send message: " + chatMessage.getContent());
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        log.info("add user: " + chatMessage.getEmployeeID());
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("employeeID", chatMessage.getEmployeeID());
        return chatMessage;
    }
}
