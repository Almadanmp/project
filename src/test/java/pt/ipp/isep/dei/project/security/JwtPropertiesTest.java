package pt.ipp.isep.dei.project.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JwtPropertiesTest {

    @Test
    void seeIfEmptyConstructor() {
        JwtProperties expectedResult = new JwtProperties();
        JwtProperties actualResult = new JwtProperties();
        assertEquals(expectedResult.getClass(), actualResult.getClass());
    }
}