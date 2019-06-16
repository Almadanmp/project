package pt.ipp.isep.dei.project.controller.controllerweb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ipp.isep.dei.project.model.user.User;
import pt.ipp.isep.dei.project.model.user.UserService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/loginWeb")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"}, maxAge = 3600)
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
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
