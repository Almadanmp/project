package Sprint_0;

import Sprint_0.DummyClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DummyClassTest {


    /**
     * Dummy method just to be able to run mutation tests.
     * Delete after implementing your own tests.
     *
     * @return
     */
    @Test
    public void dummyMethodTest() {
        String expectedResult = "Hello World!";
        String result;

        DummyClass dummyClass = new DummyClass();
        result = dummyClass.dummyMethod();

        assertEquals(expectedResult, result);

    }
}