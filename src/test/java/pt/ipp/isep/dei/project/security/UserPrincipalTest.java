package pt.ipp.isep.dei.project.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import pt.ipp.isep.dei.project.model.user.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


 class UserPrincipalTest {
    private User user = new User("Arya", "Dana123", "ADMIN,DEVELOPER", "everything,work");
    private UserPrincipal userPrincipal = new UserPrincipal(user);

    @Test
    void seeIfGetAuthorities() {
        List<String>roles = new ArrayList<>();
        roles.add("everything");
        roles.add("work");
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_DEVELOPER");

        Collection<? extends GrantedAuthority> actualResult =  userPrincipal.getAuthorities();
        assertEquals(roles.size(),actualResult.size());
    }


    @Test
    void seeIfGetPassword(){
        String expectedResult = "Dana123";
        String actualResult = userPrincipal.getPassword();
        assertEquals(expectedResult,actualResult);
    }


    @Test
    void seeIfGetUsername(){
        String expectedResult = "Arya";
        String actualResult = userPrincipal.getUsername();
        assertEquals(expectedResult,actualResult);
    }


    @Test
    void seeIfIsAccountNonExpired(){
        assertTrue(userPrincipal.isAccountNonExpired());

    }

    @Test
    void isAccountNonLocked(){
        assertTrue(userPrincipal.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired(){
        assertTrue(userPrincipal.isCredentialsNonExpired());
    }



    @Test
    void isEnabled(){
        assertTrue(userPrincipal.isEnabled());
    }

}