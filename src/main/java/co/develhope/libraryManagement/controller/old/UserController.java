package co.develhope.libraryManagement.controller.old;

import co.develhope.libraryManagement.model.entities.User;
import co.develhope.libraryManagement.service.UserService;
import co.develhope.libraryManagement.utils.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/User")
@PreAuthorize("hasRole('"+ Roles.CUSTOMER + "') OR hasRole('"+Roles.ADMIN+"')")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        try {
            logger.info("Get all user");
            return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUser());
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/getUserById")
    public ResponseEntity<?> getUserById(@RequestParam Long id){
        try{
            logger.info("Get User by Id");
            return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/InsertNewUser")
    public ResponseEntity<?> insertNewUser(@RequestBody User user) {
       try {
           logger.info("Insert a user");
           return ResponseEntity.status(HttpStatus.OK).body(userService.addNewUser(user));
       }catch (Exception e){
           logger.error(e.toString());
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody  User user, @RequestParam Long id) {
        try{
            logger.info("Update a user");
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user,id));
        }catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/DeleteUser")
    public ResponseEntity<?> deleteUser(){
        try{
            logger.info("Delete all user ");
            return ResponseEntity.status(HttpStatus.OK).body(userService.deleteAllUser());
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteUserById")
    public ResponseEntity<?> deleteUserById(@RequestParam Long id) {
        try{
            logger.info("Delete a user by id");
            return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUserById(id));
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}













