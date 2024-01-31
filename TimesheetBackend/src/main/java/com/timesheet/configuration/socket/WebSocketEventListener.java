package com.timesheet.configuration.socket;

import com.manage.employeemanagementmodel.entity.Employee;
import com.timesheet.dto.ChatMessage;
import com.timesheet.repository.EmployeeRepository;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;
import java.util.logging.Logger;

@Component
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;
    private final EmployeeRepository employeeRepository;
    private final Logger log = Logger.getLogger(WebSocketEventListener.class.getName());

    public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate, EmployeeRepository employeeRepository) {
        this.messagingTemplate = messagingTemplate;
        this.employeeRepository = employeeRepository;
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        Integer employeeID = (Integer) Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("employeeID");
        if (employeeID != null) {
            log.info("user disconnected: " + employeeID);
            Employee employee = employeeRepository.findById(employeeID).orElse(null);
            String name = employee != null ? employee.getFullName() : "unknown";
            ChatMessage chatMessage = new ChatMessage(ChatMessage.MessageType.LEAVE, "send to " + employeeID, name, employeeID);
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }

}
