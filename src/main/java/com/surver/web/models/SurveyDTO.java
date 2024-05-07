package com.surver.web.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("surveys")
public class SurveyDTO {

    @Id
    private String id;
    private String type;
    private String dateOfBirth;
    private String favouriteFood;
    private String likeMovies;
    private String listenToRadio;
    private String eatOut;
    private String watchTV;
    private String user_id;

}
