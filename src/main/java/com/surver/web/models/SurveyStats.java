package com.surver.web.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyStats {

    private int totalSurveys;
    private String avarageAge;
    private String oldAge;
    private String youngAge;

    private double pizzaLovers;
    private double pastaLovers;
    private double papAndWorsLovers;

    private double movieLovers;
    private double radioLovers;
    private double eatOutLovers;
    private double tvLovers;


}
