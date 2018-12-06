package Sprint0_Test.Model_Test;

import Sprint0.Model.TypeArea;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Type_Area_Test {

    @Test
    public void seeTypeOfGeographicAreaConstructor(){
        //Arrange
        String expectedResult = "Rua";
        String actualResult;
        //Act
        TypeArea typeArea1 = new TypeArea("Rua");
        actualResult = typeArea1.getTypeOfGeographicArea();

        //Assert
        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void seeSetGeographicAreaTypeWithSameDesignationFromConstructor(){
        //Arrange
        String expectedResult = "Rua";
        String actualResult;
        TypeArea typeArea1 = new TypeArea("Rua");

        //Act
        typeArea1.setTypeOfGeographicArea("Rua");
        actualResult = typeArea1.getTypeOfGeographicArea();

        //Assert
        assertEquals(expectedResult,actualResult);
    }
    @Test
    public void seeSetGeographicAreaTypeWithDifferentDesignationFromConstructor(){
        //Arrange
        String expectedResult = "Freguesia";
        String actualResult;
        TypeArea typeArea1 = new TypeArea("Rua");

        //Act
        typeArea1.setTypeOfGeographicArea("Freguesia");
        actualResult = typeArea1.getTypeOfGeographicArea();

        //Assert
        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void seeGetGeographicAreaTypeAfterSet(){
        //Arrange
        String expectedResult = "Porto";
        String actualResult;
        TypeArea typeArea1 = new TypeArea("Rua");

        //Act
        typeArea1.setTypeOfGeographicArea("Porto");
        actualResult = typeArea1.getTypeOfGeographicArea();

        //Assert
        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void seeEqualsBetweenTwoGeographicAreaTypesWithSameDesignation(){
        //Arrange
        Boolean expectedResult = true;
        Boolean actualResult;
        TypeArea typeArea1 = new TypeArea("Rua");
        TypeArea typeArea2 = new TypeArea("Rua");
        //Act
        actualResult = typeArea1.equals(typeArea2);
        //Assert
        assertEquals(expectedResult,actualResult);
    }
    @Test
    public void seeEqualsBetweenTwoGeographicAreaTypesWithDifferentDesignation(){
        //Arrange
        Boolean expectedResult = false;
        Boolean actualResult;
        TypeArea typeArea1 = new TypeArea("Rua");
        TypeArea typeArea2 = new TypeArea("Freguesia");
        //Act
        actualResult = typeArea1.equals(typeArea2);
        //Assert
        assertEquals(expectedResult,actualResult);
    }
    @Test
    public void seeEqualsBetweenSameObject(){
        //Arrange
        Boolean expectedResult = true;
        Boolean actualResult;
        TypeArea typeArea1 = new TypeArea("Rua");
        //Act
        actualResult = typeArea1.equals(typeArea1);
        //Assert
        assertEquals(expectedResult,actualResult);
    }
    @Test
    public void seeEqualsBetweenObjectsFromDifferentClasses(){
        //Arrange
        Boolean expectedResult = false;
        Boolean actualResult;
        TypeArea typeArea1 = new TypeArea("Rua");
        int number = 1;
        //Act
        actualResult = typeArea1.equals(number);
        //Assert
        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void hashCodeDummyTest() {
        TypeArea t1 = new TypeArea("Rua");
        int expectedResult = 1;
        int actualResult = t1.hashCode();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfNameValid() {
        //Arrange
        TypeArea a1 = new TypeArea("Humidade");

        //Act
        boolean result = a1.isNameValid("Vale");

        //Assert
        assertTrue(result);
    }

    @Test
    public void seeIfNameInvalidNameEmpty() {

        //Arrange
        TypeArea a1 = new TypeArea("Humidade");
        //Act

        //Assert
                Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    a1.isNameValid("");
                });
    }
    @Test
    public void seeIfNameInvalidNameWithNumbers() {

        //Arrange
        TypeArea a1 = new TypeArea("Humidade");
        //Act

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            a1.isNameValid("Humidade1");
        });
    }
    @Test
    public void seeIfNameInvalidNameNull() {

        //Arrange
        TypeArea a1 = new TypeArea("Humidade");
        //Act

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            a1.isNameValid(null);
        });
    }
}
