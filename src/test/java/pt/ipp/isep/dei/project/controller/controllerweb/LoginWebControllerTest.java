package pt.ipp.isep.dei.project.controller.controllerweb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project.model.user.User;
import pt.ipp.isep.dei.project.model.user.UserService;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LoginWebControllerTest {

    @Mock
    UserService userService;
    @InjectMocks
    LoginWebController loginWebController;

    private User userAdmin;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        userAdmin = new User("Daniela", "dana123", "ADMIN", "Everything");
    }

    @Test
    void getUserRoleSuccess() {
        // Act

        Mockito.when(userService.getUsernameFromToken()).thenReturn("Daniela");

        Mockito.when(userService.findByUsername("Daniela")).thenReturn(userAdmin);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>("ADMIN", HttpStatus.OK);

        ResponseEntity<Object> actualResult = loginWebController.getUserRole();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeGetUserRoleNoSuchElement() {
        // Act
        Mockito.when(userService.getUsernameFromToken()).thenReturn("John");
        Mockito.when(userService.findByUsername("John")).thenThrow(NoSuchElementException.class);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        ResponseEntity<Object> actualResult = loginWebController.getUserRole();

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}