package org.ada.school.repository;

import org.ada.school.dto.UserDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document
public class UserDocument {

    @Id
    String id;
    String name;

    @Indexed( unique = true )
    String email;
    String lastName;
    Date createdAt;

    public UserDocument() { }

    public UserDocument(UserDto userDto ) {
        id = UUID.randomUUID().toString();
        name = userDto.getName();
        lastName = userDto.getLastName();
        email = userDto.getEmail();
        createdAt = new Date();
    }
    public void update( UserDto userDto ) {
        name = userDto.getName();
        lastName = userDto.getLastName();
        email = userDto.getEmail();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}

