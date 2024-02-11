package com.oviplok.daydate.model.chat;

import com.oviplok.daydate.model.chat.messages.Messages;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Chat {
    @Id
    private String id;

    // TODO: ADD USERS ID

    private Messages messages;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
