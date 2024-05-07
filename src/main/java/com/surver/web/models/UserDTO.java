package com.surver.web.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class UserDTO {
    @Id
    private String id;
    private String fullNames;
    private String email;
    private String dateOfBirth;
    @Indexed(unique = true)
    private String mobileNumber;
}
