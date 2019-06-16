package pt.ipp.isep.dei.project.controller.controllerweb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;
import pt.ipp.isep.dei.project.model.user.User;
import pt.ipp.isep.dei.project.model.user.UserService;

import java.util.NoSuchElementException;

@RestController
@ApplicationScope
@RequestMapping("/loginWeb")
public class LoginWebController {

    @Autowired
    UserService userService;

    @GetMapping("/getUserRole")
    public ResponseEntity<Object> getUserRole() {
        String currentUserName = userService.getUsernameFromToken();
        try {
            User user = userService.findByUsername(currentUserName);
            return new ResponseEntity<>(user.getRoleList().get(0), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
