package pt.ipp.isep.dei.project.controller.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pt.ipp.isep.dei.project.model.user.UserRepository;

@RestController
public class LoginWebController {

    @Autowired
    private UserRepository userRepository;


}