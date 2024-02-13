package com.oviplok.daydate.model.chat;

import com.oviplok.daydate.model.chat.messages.Messages;
import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Chat {
    //TODO FIND ID GENERATOR
    @Id
    private String id;

    @Embedded
    private Messages messages;

    private List<String> usersID;

    public Chat(List<String> usersID) {
//        String id,
        //TODO FIND ID GENERATOR
        this.usersID = usersID;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    public List<String> getUsersID() {
        return usersID;
    }

    public void setUsersID(List<String> usersID) {
        this.usersID = usersID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
