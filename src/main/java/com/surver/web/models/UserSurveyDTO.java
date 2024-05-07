package com.surver.web.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSurveyDTO {
    private UserDTO user;
    private SurveyDTO survey;

    // Getters and setters
}