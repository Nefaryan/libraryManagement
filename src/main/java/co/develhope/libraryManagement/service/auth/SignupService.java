package co.develhope.libraryManagement.service.auth;

import co.develhope.libraryManagement.model.DTO.SignupActivationDTO;
import co.develhope.libraryManagement.model.DTO.SignupDTO;
import co.develhope.libraryManagement.model.entities.Role;
import co.develhope.libraryManagement.model.entities.User;
import co.develhope.libraryManagement.notifiation.MailNotificationService;
import co.develhope.libraryManagement.repositories.RoleRepository;
import co.develhope.libraryManagement.repositories.UserRepository;
import co.develhope.libraryManagement.utils.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class SignupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MailNotificationService mailNotificationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User signup(SignupDTO signupDTO) throws Exception {
        return this.signup(signupDTO, Roles.REGISTERED);
    }

    public User signup(SignupDTO signupDTO, String role) throws Exception {
        User userInDB = userRepository.findByEmail(signupDTO.getEmail());
        if(userInDB != null) throw new Exception("User already Exists");
        User user = new User();
        user.setName(signupDTO.getName());
        user.setEmail(signupDTO.getEmail());
        user.setSurname(signupDTO.getSurname());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        user.setDateOfBirth(signupDTO.getDateOfBirth());
        user.setCity(signupDTO.getCity());
        user.setFiscalCode(signupDTO.getFiscalCode());
        user.setTelephoneNumber(signupDTO.getTelephoneNumber());
        user.setActivationCode(UUID.randomUUID().toString());

        Set<Role> roles = new HashSet<>();
        Optional<Role> userRole = roleRepository.findByName(role.toUpperCase());
        if(!userRole.isPresent()) throw  new Exception("Cannot set role");
        roles.add(userRole.get());
        user.setRoles(roles);

         mailNotificationService.sendActivationEmail(user);
        return userRepository.save(user);
    }

    public User activate(SignupActivationDTO signupActivationDTO) throws Exception {
        User user = userRepository.findByActivationCode(signupActivationDTO.getActivationCode());
        if(user == null) throw new Exception("User Not found");
        user.setActive(true);
        user.setActivationCode(null);
        return userRepository.save(user);
    }
}
