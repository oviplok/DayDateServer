package com.oviplok.daydate.controller;

import com.oviplok.daydate.model.user.User;
import com.oviplok.daydate.repository.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    // TODO: addUser, deleteUserById, getUserById,                  Checked
    //  updateUserById, getUserSex,                                 needCheck
    //  getPartners,  onRight,  onLeft,  isConnectionMatch,         needCheck
    //  getUserMatchId(getMatches), FetchMatchInformation(getUserById), needCheck
    //  deleteUserConnection,
    //  UserAuth, UpdateUserSettings,updateUserPrefer, updateUserPasswordAndEmail

    @Autowired
    private UserDao userDao;

    @PostMapping("/user/add")
    public User addUser(@RequestBody User user){
        return userDao.addUser(user);
    }

    @DeleteMapping("/user/{usr_id}/delete_profile")
    public void deleteUserById(@PathVariable("usr_id") String id){
        userDao.deleteById(id);
    }

    @GetMapping("/user/{usr_id}")
    public Optional<User> getUserById(@PathVariable("usr_id") String id){
        return userDao.getUserById(id);
    }

    @PutMapping("/user/update/{usr_id}")
    public ResponseEntity<String> updateUserInfo(@PathVariable("usr_id") String id, @RequestBody User user){
        boolean result =userDao.updateUserInfo(id,user);
        if(result){
            return new ResponseEntity<>("User information updated successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("User information is not updated", HttpStatus.OK);
        }

    }

    @GetMapping("/user/{usr_id}/get_sex")
    public String getUserSex(@PathVariable("usr_id") String id){
        return userDao.getUserSex(id);
    }

    @GetMapping("/user/{usr_id}/partners/{pref_sex}")
    public List<User> getPartners(@PathVariable("usr_id") String id, @PathVariable("pref_sex") String pref_sex){
        return userDao.getUsersForSwipeList(id,pref_sex);
    }

    // TODO: MAKE PUT
    @PostMapping("/user/{usr_id}/partners/right/{partners_id}")
    public void onRight(@PathVariable("usr_id") String user_id, @PathVariable("partners_id") String partners_id){
        userDao.updateConnectionOnRight(user_id,partners_id);
    }

    @PostMapping("/user/{usr_id}/partners/left/{partners_id}")
    public void onLeft(@PathVariable("usr_id") String user_id, @PathVariable("partners_id") String partners_id){
        userDao.updateConnectionOnLeft(user_id,partners_id);
    }

    @PutMapping("/user/{usr_id}/partners/matches/is_match/{partners_id}")
    public boolean isMatch(@PathVariable("usr_id") String user_id, @PathVariable("partners_id") String partners_id){
        return userDao.isMatch(user_id,partners_id);
    }

    @GetMapping("/user/{usr_id}/partners/matches/get_id")
    public List<String> getUsersMatchesId(@PathVariable("usr_id") String user_id){
        return userDao.getMatches(user_id);
    }
    //Really need?
    @GetMapping("user/{usr_id}/partners/matches/get_info/{partners_id}")
    public User getMatchInfo(@PathVariable("usr_id") String user_id, @PathVariable String partners_id){
        Optional<User> opt_user= userDao.getUserById(partners_id);
        return opt_user.get();
    }







//    //НЕ ИСПОЛЬЗУЕТСЯ
//    @GetMapping("/user/get-all")
//    public List<User> getAllUsers(){
//        return userDao.getAllUsers();
//
//    }
//
//    @GetMapping(value = "/user/get/{key_word}")
//    public List<User> getUsersByKeyword(@PathVariable("key_word") String key_word){
//        return userDao.getUserByKeyword(key_word);
//    }

//    @PostMapping("/user/add")
//    public User addUser(@RequestBody User user){
//        return userDao.addUser(user);
//    }
}
