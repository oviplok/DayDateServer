package com.oviplok.daydate.controller;

import com.oviplok.daydate.model.chat.Chat;
import com.oviplok.daydate.repository.chat.ChatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatController {
    //TODO: findUserChats, deleteChat(And Match), Socket D:
    @Autowired
    private ChatDao chatDao;

    @GetMapping("/chat/get/{usr_id}")
    public List<Chat> findUserChats(@PathVariable("usr_id") String id){
        return chatDao.findUsersChats(id);
    }

    @DeleteMapping("chat/delete/{chat_id}")
    public void deleteChat(@PathVariable("chat_id") String id){
        chatDao.deleteChatAndMatch(id);
    }
}
