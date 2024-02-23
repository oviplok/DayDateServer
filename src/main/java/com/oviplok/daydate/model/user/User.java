package com.oviplok.daydate.model.user;

import com.oviplok.daydate.model.user.connections.Connections;
import jakarta.persistence.Embedded;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Setter
@Getter
@Document(collection = "users")
public class User {
    //TODO FIND ID GENERATOR
    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;

    private String name;
    private String mail;
    private String password;
    private String phone;
    private String sex;
    private String profileImageUrl;
    @Embedded
    private Connections connections;


    public User(String name, String mail, String password, String phone, String sex, String profileImageUrl, Connections connections) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
        this.profileImageUrl = profileImageUrl;
        this.connections = connections;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void setConnections(Connections connections) {
        this.connections = connections;
    }
}
