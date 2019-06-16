package pt.ipp.isep.dei.project.model.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;
    private User userAdmin;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        userAdmin = new User("Daniela", "dana123", "ADMIN", "Everything");
    }

    @Test
    void findByUsername() {
        Mockito.when(userService.findByUsername("Daniela")).thenReturn(userAdmin);
        User actualResult = userService.findByUsername("Daniela");
        assertEquals(userAdmin, actualResult);
    }

    @Test
    void findByUsernameFails() {
        Mockito.when(userService.findByUsername("Kovacs")).thenReturn(null);
        User actualResult = userService.findByUsername("Kovacs");
        assertNull(actualResult);
    }
}