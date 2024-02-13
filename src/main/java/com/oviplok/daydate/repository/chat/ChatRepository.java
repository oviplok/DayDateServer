package com.oviplok.daydate.repository.chat;

import com.oviplok.daydate.model.chat.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ChatRepository extends MongoRepository<Chat,String> {
    @Query("{'usersID': ?0}")
    List<Chat> findByUserId(String userId);
}
