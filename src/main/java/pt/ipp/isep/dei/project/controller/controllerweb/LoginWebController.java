package pt.ipp.isep.dei.project.controller.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ipp.isep.dei.project.model.bridgeservices.GeographicAreaHouseService;
import pt.ipp.isep.dei.project.model.user.UserService;

@RestController
@RequestMapping("/login")
@Scope("request")
public class LoginWebController {

    @Autowired
    GeographicAreaHouseService geographicAreaHouseService;
    @Autowired
    UserService userService;


}
