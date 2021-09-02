package org.ada.school.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface UserRepository extends MongoRepository <UserDocument, String> {

    List<UserDocument> findByNameOrLastName(String name, String lastName);

    List<UserDocument> findByCreatedAtAfter(Date startDate);
}

