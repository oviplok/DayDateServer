package com.oviplok.daydate.repository.user;

import com.oviplok.daydate.model.user.User;
import com.oviplok.daydate.model.user.connections.Connections;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserDao {
    @Autowired
    private UserRepository repository;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<User> getUserByKeyword(String keyword){
        //return repository.findAll();
        List<User> users = new ArrayList<>();
        Streamable.of(repository.findUserByKeyword(keyword)).forEach(users::add );
        return users;
    }

    public String getUserSex(String id){
        return repository.findById(id).get().getSex();
    }

    public User addUser(User user){
        return repository.save(user);
    }

    public boolean isUserExist(String id){
        return repository.existsById(id);
    }

    public void deleteUser(User user){
        repository.delete(user);
    }

    public void deleteById(String id){
        repository.deleteById(id);
    }

    public Optional<User> getUserById(String id){return repository.findById(id);}

    public List<Connections> getUserConnections(String currentUserId){
        List<Connections> connections = new ArrayList<>();
        Streamable.of(repository.getUserConnections(currentUserId)).forEach(connections::add);
        return connections;
    }

    // TODO: закончить функцию и прочекать
    public List<User> getUsersForSwipeList(String currentUserId, String userPrefer){
        List<User> users = new ArrayList<>();
//        Streamable.of(repository.findUserByKeyword(userPrefer)).forEach(users::add );

        Query query = new Query(Criteria
                .where("sex").is(userPrefer)
                .and("connections.left." + currentUserId).exists(false)
                .and("connections.right." + currentUserId).exists(false)
//                .and("connections.match"+currentUserId).exists(false)
        );

//        users = repository.findUsersPartners(userPrefer,currentUserId);
        users = mongoTemplate.find(query,User.class);
        return users;
    }

    public void updateConnectionOnRight(String currentUserId, String partnerId){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(currentUserId));
        Update update = new Update();
        update.set("connections.right." + partnerId, true);
        mongoTemplate.updateFirst(query, update, User.class);
    }

    public void updateConnectionOnLeft(String currentUserId, String partnerId){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(currentUserId));
        Update update = new Update();
        update.set("connections.left." + partnerId, true);
        mongoTemplate.updateFirst(query, update, User.class);
    }





    public List<User> getAllUsers(){
        //return repository.findAll();
        List<User> users = new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(users::add );
        return users;
    }
//    User oldUser = repository.findById(id);
//
//        repository.updateUserById(id, user);
    public boolean updateUserInfo(String id, User user) {
        Optional<User> existingUserOptional = repository.findById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setId(id);
            existingUser.setName(user.getName());
            existingUser.setMail(user.getMail());
            existingUser.setPassword(user.getPassword());
            existingUser.setPhone(user.getPhone());
            existingUser.setSex(user.getSex());
            existingUser.setProfileImageUrl(user.getProfileImageUrl());
            repository.save(existingUser);
            return true;
        }
        else {
            return false;
        }
    }
}
