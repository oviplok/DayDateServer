package com.oviplok.daydate.repository.chat;


import com.oviplok.daydate.model.chat.Chat;
import com.oviplok.daydate.model.chat.messages.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ChatDao {

    @Autowired
    private ChatRepository repository;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ChatDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void addNewChat(Chat chat){
        repository.save(chat);
    }

    //TODO Check how mongoTemplate works
    public String getNewChatID(String firstUserId,String secondUserId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("usersID").all(Arrays.asList(firstUserId, secondUserId)));
        Chat chat = mongoTemplate.findOne(query, Chat.class);
        if (chat == null) {
            return "ERROR";
            // Логика создания нового чата, если не найден существующий
        } else {
            return chat.getId();
        }
    }


    //TODO Delete match
    public void deleteChatAndMatch(String id){
        repository.deleteById(id);
    }


    public void addMessage(String chatId, String messageText,String userId){
        Query query = new Query(Criteria.where("id").is(chatId));

        Messages newMessage = new Messages();
        newMessage.setMessageText(messageText);
        newMessage.setRead(false);
        newMessage.setUserId(userId);
        newMessage.setMessageTime(LocalDateTime.now());

        Update update = new Update();
        update.addToSet("messages", newMessage);

        mongoTemplate.updateFirst(query, update, Chat.class);
    }
    public List<Chat> findUsersChats(String userId){
        return repository.findByUserId(userId);
    }
}
