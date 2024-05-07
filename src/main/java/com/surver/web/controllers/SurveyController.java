package com.surver.web.controllers;

import com.surver.web.models.SurveyDTO;
import com.surver.web.models.UserDTO;
import com.surver.web.models.UserSurveyDTO;
import com.surver.web.services.SurveyServices;
import com.surver.web.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/survey/")
public class SurveyController {

    @Autowired
    private SurveyServices surveyServices;

    @Autowired
    private UserServices userServices;

    @PostMapping("post/participate/{surveyType}")
    public ResponseEntity<Object> createSurvey(@RequestBody UserSurveyDTO survey, @PathVariable("surveyType") String type) {
        return userServices.createSurvey(survey, type);
    }

    @GetMapping("get/stats/{surveyType}")
    public ResponseEntity<Object> getStats(@PathVariable("surveyType") String type) {
        return surveyServices.getStats(type);
    }
}

