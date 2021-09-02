package org.ada.school.controller;

import org.ada.school.dto.UserDto;
import org.ada.school.model.User;
import org.ada.school.repository.UserDocument;
import org.ada.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping( "/v1/user" )
public class UserController {

    private final UserService userService;

    public UserController( @Autowired UserService userService ) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDocument>> all() {
        return ResponseEntity.ok( userService.all() );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<UserDocument> findById( @PathVariable String id ) {
        return ResponseEntity.ok( userService.findById( id ) );
    }

    @PostMapping
    public ResponseEntity<UserDocument> create(@RequestBody UserDto userDto ) {
        return ResponseEntity.ok( userService.create( new UserDocument( userDto ) ) );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<UserDocument> update( @RequestBody UserDto userDto, @PathVariable String id ) {
        return ResponseEntity.ok( userService.update( userDto, id ) );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete( @PathVariable String id ) {
        return ResponseEntity.ok( userService.deleteById( id ) );
    }

    @GetMapping( "/nameOrLastNameLike/{queryText}")
    public ResponseEntity<List<UserDocument>> findByNameOrLastName( @PathVariable String queryText ) {
        return ResponseEntity.ok( userService.findByNameOrLastName(queryText) );
    }

    @GetMapping( "/createdAfter/{startDate}")
    public ResponseEntity<List<UserDocument>> findByCreatedAtAfter( @PathVariable String startDate ) throws ParseException {
        SimpleDateFormat newDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date = newDate.parse(startDate);
        return ResponseEntity.ok( userService.findByCreatedAtAfter(date) );
    }

}
