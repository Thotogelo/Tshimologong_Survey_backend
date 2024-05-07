package com.surver.web.services;

import com.surver.web.models.SurveyDTO;
import com.surver.web.repo.SurveyRepo;
import com.surver.web.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

import static com.surver.web.exceptions.ExceptionDistributer.distributeException;
import static com.surver.web.utils.Operations.getSurveyStats;

@Service
public class SurveyServices {

    @Autowired
    private SurveyRepo surveyRepo;


    public ResponseEntity<Object> createSurvey(SurveyDTO surveyDTO){
        SurveyDTO survey = surveyRepo.verifySurvey(surveyDTO.getUser_id(), surveyDTO.getType());

        if(survey != null){
            return new ResponseEntity<>(distributeException("You can only participate in this survey once."), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(surveyRepo.save(surveyDTO), HttpStatus.OK);

    }

    public ResponseEntity<Object> getStats(String type){
        LinkedList<SurveyDTO> allSurvey = surveyRepo.getAllSameTypeSurveys(type);

        if(allSurvey.isEmpty()){ return  new ResponseEntity<>(distributeException("No surveys fount"), HttpStatus.BAD_REQUEST);}

        return new ResponseEntity<>(getSurveyStats(allSurvey), HttpStatus.OK);
    }
}
