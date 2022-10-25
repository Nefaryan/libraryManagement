package co.develhope.libraryManagement.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestorePasswordDTO {

    private String newPassword;
    private String resetPasswordCode;


}
