package com.timesheet.dto;

public class ChatMessage {

    private MessageType type;
    private String content;
    private String sender;
    private Integer employeeID;
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public ChatMessage() {
    }

    public ChatMessage(MessageType type, String content, String sender, Integer employeeID) {
        this.type = type;
        this.content = content;
        this.sender = sender;
        this.employeeID = employeeID;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }
}
