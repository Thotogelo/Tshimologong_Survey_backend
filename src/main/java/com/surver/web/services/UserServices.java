package com.surver.web.services;

import com.surver.web.models.SurveyDTO;
import com.surver.web.models.UserDTO;
import com.surver.web.models.UserSurveyDTO;
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
public class UserServices {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SurveyServices surveyServices;


    public ResponseEntity<Object> createSurvey(UserSurveyDTO survey, String type){

        SurveyDTO surveyDTO = survey.getSurvey();
        UserDTO user = userRepo.verifyUser(survey.getUser().getMobileNumber(), survey.getUser().getEmail());

        if(user == null){
            user = userRepo.save(survey.getUser());
        }

        surveyDTO.setUser_id(user.getId());
        surveyDTO.setType(type);
        surveyDTO.setDateOfBirth(user.getDateOfBirth());

        return new ResponseEntity<>(surveyServices.createSurvey(surveyDTO), HttpStatus.OK);

    }


}
