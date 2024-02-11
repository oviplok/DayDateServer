package com.oviplok.daydate.repository.user;

import com.oviplok.daydate.model.user.User;
import com.oviplok.daydate.model.user.connections.Connections;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {

    void updateUserById(String id, User user);

    // TODO: Погуглить запросы
    @Query("{'$or':[{'name': {$regex : ?0, $options: 'i'}}, {'phone': {$regex : ?0, $options: 'i'}}, {'mail': {$regex : ?0, $options: 'i'}}]}")
    List<User> findUserByKeyword(String keyword);

    @Query(value = "{'id' : ?0}", fields = "{'connections' : 1}")
    List<Connections> getUserConnections(String id);

    @Query("{ 'sex' : ?0, 'connections.left.?1': { $exists: false }, 'connections.right.?1': { $exists: false } }")
    List<User> findUsersPartners(String userPrefer, String userId);

//    void updateUserById


//    @Query(value ="SELECT * FROM springserverdb.user WHERE concat(name, phone, mail) LIKE concat('%',:query,'%')", nativeQuery = true)
//    public List<User> findUserByKeyword(@Param("query") String keyword);
//
//    // TODO: Проверить запрос и модель нейронкой
//    @Query(value ="SELECT connections FROM springserverdb.user WHERE id=:id", nativeQuery = true)
//    public List<Connections> getUserConnections(@Param("id") String id);
//
//
//    @Query(value ="SELECT * FROM springserverdb.user WHERE sex LIKE :userPrefer and connections.left", nativeQuery = true)
//    List<User> getUsersByPreference(@Param("userPrefer")String userPrefer,@Param("currentUser")String currentUser);
//
////    @Query(value ="SELECT connections FROM springserverdb.user WHERE id=:id", nativeQuery = true)
////    public List<User> AddConnection(@Param("id") String id);
}
