package pt.ipp.isep.dei.project.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginViewModelTest {

    private LoginViewModel login = new LoginViewModel();


    @Test
    void setAndGetUsername() {
        String username = "Arya";
        login.setUsername(username);
        String expectedResult = username;
        String actualResult = login.getUsername();
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void setAndGetPassword() {
        String password = "Arya";
        login.setPassword(password);
        String expectedResult = password;
        String actualResult = login.getPassword();
        assertEquals(expectedResult, actualResult);

    }

}