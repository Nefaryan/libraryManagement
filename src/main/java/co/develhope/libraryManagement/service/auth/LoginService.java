package co.develhope.libraryManagement.service.auth;

import co.develhope.libraryManagement.model.DTO.LoginDTO;
import co.develhope.libraryManagement.model.DTO.LoginRTO;
import co.develhope.libraryManagement.model.entities.User;
import co.develhope.libraryManagement.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class LoginService {


    @Autowired
    private PasswordEncoder passwordEncoder;


    public static String JWT_SECRET = "0813ec65-7824-4dbd-b13a-54bfbc99930b";

    @Autowired
    private UserRepository userRepository;

    public LoginRTO login(LoginDTO loginDTO){
        if(loginDTO == null) return null;
        User userFromDB = userRepository.findByEmail(loginDTO.getEmail());
        if(userFromDB == null || !userFromDB.isActive()) return null;

        boolean canLogin = this.canUserLogin(userFromDB, loginDTO.getPassword());
        if(!canLogin) return null;

        String JWT = generateJWT(userFromDB);

        userFromDB.setPassword(null);
        LoginRTO out = new LoginRTO();
        out.setJWT(JWT);
        out.setUser(userFromDB);

        return out;
    }

    public boolean canUserLogin(User user, String password){
        return passwordEncoder.matches(password, user.getPassword());
    }

    //https://www.baeldung.com/java-date-to-localdate-and-localdatetime
    static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    public static String getJWT(User user){
        Date expiresAt = convertToDateViaInstant(LocalDateTime.now().plusDays(15));
        //https://mkyong.com/java8/java-8-how-to-convert-a-stream-to-array/
        String[] roles = user.getRoles().stream().map(role -> role.getName()).toArray(String[]::new);
        return JWT.create()
                .withIssuer("develhope-demo")
                .withIssuedAt(new Date())
                .withExpiresAt(expiresAt)
                .withClaim("roles", String.join(",", roles))
                .withClaim("id", user.getId())
                .sign(Algorithm.HMAC512(JWT_SECRET));

    }


    public String generateJWT(User user) {
        String JWT = getJWT(user);

        user.setJwtCreatedOn(LocalDateTime.now());
        userRepository.save(user);

        return JWT;
    }

}
