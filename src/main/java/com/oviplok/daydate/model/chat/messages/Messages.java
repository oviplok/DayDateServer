package com.oviplok.daydate.model.chat.messages;

import jakarta.persistence.Embeddable;

import java.util.Date;

@Embeddable
public class Messages {
    String userId;
    String messageText;
    Date messageTime;
    boolean isRead;
}
