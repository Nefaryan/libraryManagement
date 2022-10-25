package co.develhope.libraryManagement.controller.auth;

import co.develhope.libraryManagement.model.DTO.*;
import co.develhope.libraryManagement.service.auth.LoginService;
import co.develhope.libraryManagement.service.auth.PasswordService;
import co.develhope.libraryManagement.service.auth.SignupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private SignupService signupService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private PasswordService passwordService;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDTO signupDTO) throws Exception {
        try{
            logger.info("Registration successful");
            return ResponseEntity.status(HttpStatus.OK).body(signupService.signup(signupDTO));
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/signup/{role}")
    public ResponseEntity<?> signup(@RequestBody SignupDTO signupDTO, @PathVariable String role) throws Exception {
        try{
            logger.info("Registration in progress ");
            return ResponseEntity.status(HttpStatus.OK).body(signupService.signup(signupDTO,role));
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/signup/activation")
    public ResponseEntity<?> signup(@RequestBody SignupActivationDTO signupActivationDTO) throws Exception {
        try {
            logger.info("User activating");
            return ResponseEntity.status(HttpStatus.OK).body(signupService.activate(signupActivationDTO));
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRTO> login(@RequestBody LoginDTO loginDTO){
        try{
            logger.info("Try to login in the app");
            LoginRTO loginRTO = loginService.login(loginDTO);
            if(loginRTO == null) throw new LoginException("Cannot Login");
            return ResponseEntity.status(HttpStatus.OK).body(loginRTO);
        } catch (LoginException ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/request")
    public ResponseEntity<?> passwordRequest (@RequestBody RequestPasswordDTO requestPasswordDTO){
        try{
            logger.info("Received request password");
            return ResponseEntity.status(HttpStatus.OK).body(passwordService.request(requestPasswordDTO));
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
        }
    }

    @PostMapping("/restore")
    public ResponseEntity<?> passwordRestore(@RequestBody RestorePasswordDTO restorePasswordDTO){
        try {
            logger.info("Restoring Password");
            return ResponseEntity.status(HttpStatus.OK).body(passwordService.restore(restorePasswordDTO));
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
        }
    }




}
