package co.develhope.libraryManagement.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO {

    private String name;
    private String surname;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private String city;


}
