package com.surver.web.repo;

import com.surver.web.models.SurveyDTO;
import com.surver.web.models.UserDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepo extends MongoRepository<UserDTO, String> {

    @Query("{'$and': [{'mobileNumber': ?0}, {'email': ?1}]}")
    UserDTO verifyUser(String mobileNumber, String email);

}
