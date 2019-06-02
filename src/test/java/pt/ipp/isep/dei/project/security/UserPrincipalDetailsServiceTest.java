package pt.ipp.isep.dei.project.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import pt.ipp.isep.dei.project.model.user.User;
import pt.ipp.isep.dei.project.model.user.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
 class UserPrincipalDetailsServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    UserPrincipalDetailsService userPrincipalDetailsService;

    @Test
    void loadUserByUsername() {
        String userName = "Arya";
        User user = new User("Arya", "Dana123", "ADMIN,DEVELOPER", "everything,work");
        Mockito.when(userRepository.findByUsername(userName)).thenReturn(user);
        UserDetails expectedResult = new UserPrincipal(user);
        UserDetails actualResult = userPrincipalDetailsService.loadUserByUsername(userName);
        assertEquals(expectedResult.getUsername(), actualResult.getUsername());
        assertEquals(expectedResult.getAuthorities(), actualResult.getAuthorities());
        assertEquals(expectedResult.getPassword(), actualResult.getPassword());
    }
}