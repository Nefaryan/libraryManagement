package co.develhope.libraryManagement.model.DTO;

import co.develhope.libraryManagement.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRTO {

    private User user;
    private String JWT;
}
