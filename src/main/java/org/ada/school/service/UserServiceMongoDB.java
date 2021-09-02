package org.ada.school.service;

import org.ada.school.dto.UserDto;
import org.ada.school.model.User;
import org.ada.school.repository.UserDocument;
import org.ada.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceMongoDB implements UserService {

    private final UserRepository userRepository;

    public UserServiceMongoDB(@Autowired UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDocument create( UserDocument user ) {
        return userRepository.save( user );

    }

    @Override
    public UserDocument findById( String id ) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserDocument> all() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteById( String id ) {
        boolean exist = false;
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            exist = true;
        }
        return exist;
    }

    @Override
    public UserDocument update(UserDto userDto, String id ) {
        UserDocument user = null;
        if (userRepository.existsById(id)) {
            user = userRepository.findById(id).get();
            user.update(userDto);
            userRepository.save(user);
        }
        return user;
    }
}

