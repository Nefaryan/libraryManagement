package co.develhope.libraryManagement.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO {

    private String username;
    private String password;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String city;
    @Column(unique = true)
    private String fiscalCode;
    @Column(unique = true)
    private String telephoneNumber;
    @Column(unique = true)
    private String email;


}
