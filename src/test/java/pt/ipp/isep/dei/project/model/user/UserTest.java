package pt.ipp.isep.dei.project.model.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    private User validUser;

    @BeforeEach
    void arrangeArtifacts() {
        validUser = new User("Dana", "Dana123", "ADMIN,DEVELOPER", "everything,work");
    }

    @Test
    void seeIfGetPassword() {
        String expectedResult = "Dana123";
        String actualResult = validUser.getPassword();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetUsername() {
        String expectedResult = "Dana";
        String actualResult = validUser.getUsername();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetRoles() {
        String expectedResult = "ADMIN,DEVELOPER";
        String actualResult = validUser.getRoles();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetPermissions() {
        String expectedResult = "everything,work";
        String actualResult = validUser.getPermissions();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetActive() {
        int expectedResult = 1;
        int actualResult = validUser.getActive();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetID() {
        long expectedResult = 0;
        long actualResult = validUser.getId();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetRolesList() {
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("ADMIN");
        expectedResult.add("DEVELOPER");
        List<String> actualResult = validUser.getRoleList();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetRolesListNoRoles() {
        User user = new User();
        List<String> expectedResult = new ArrayList<>();
        List<String> actualResult = user.getRoleList();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetPermissionList() {
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("everything");
        expectedResult.add("work");
        List<String> actualResult = validUser.getPermissionList();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetPermissionListNoPermissions() {
        User user = new User();
        List<String> expectedResult = new ArrayList<>();
        List<String> actualResult = user.getPermissionList();
        assertEquals(expectedResult, actualResult);
    }

}
