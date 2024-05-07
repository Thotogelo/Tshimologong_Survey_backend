package com.surver.web.repo;

import com.surver.web.models.SurveyDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.LinkedList;
import java.util.List;

public interface SurveyRepo extends MongoRepository<SurveyDTO, String> {

    @Query("{'userId': ?0, 'type': ?1}")
    SurveyDTO verifySurvey(String useID, String typer);

    @Query("{'type': ?0}")
    LinkedList<SurveyDTO> getAllSameTypeSurveys(String type);
}
