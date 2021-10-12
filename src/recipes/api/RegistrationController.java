package recipes.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import recipes.dao.UserRepository;
import recipes.dao.entity.User;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;


    @PostMapping("/api/register")
    public void registerUser(@Valid @RequestBody User user) {

        if (userRepository.findByUsername(user.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    user.setPassword(encoder.encode(user.getPassword()));
    user.setUsername(user.getEmail());
    user.setRole("ROLE_USER");
    userRepository.save(user);
    }
}
