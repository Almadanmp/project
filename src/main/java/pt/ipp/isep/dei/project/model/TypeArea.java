package pt.ipp.isep.dei.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The TypeArea class.
 * A TypeArea is has a name (designation).
 * We cannot create two TypeAreas with the same name.
 */
@Entity
public class TypeArea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    protected TypeArea() {
    }

    /**
     * Main and only Area Type Constructor
     *
     * @param nameGiven The name of the type of area
     */
    public TypeArea(String nameGiven) {
        this.name = nameGiven;
    }

    /**
     * Gets the type of a Geographical Area
     *
     * @return string with the name of the type
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets de Name of the Type Area. Will Validate if input characters for the name are valid
     *
     * @param name input name to be set as the type area name
     */
    void setName(String name) {
        if (nameIsValid(name)) {
            this.name = name;
            return;
        }
        throw new IllegalArgumentException("Please Insert Valid Name");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Method to check if the present type area is valid, that is, if its name is within acceptable parameters.
     *
     * @param name the name of the Type of Geographic Area.
     * @return is true if the name of the Type is valid, throws an exception if not.
     */

    boolean nameIsValid(String name) {
        return (name != null && !name.isEmpty() && !name.matches(".*\\d+.*"));
    }


    /**
     * Method 'equals' is required so that each 'Area Type' can be added to a 'Geographic Area'.
     */
    @Override
    public boolean equals(Object objectToTest) {
        if (this == objectToTest) {
            return true;
        }
        if (!(objectToTest instanceof TypeArea)) {
            return false;
        }
        TypeArea localVariable = (TypeArea) objectToTest;
        return localVariable.getName().equals(this.name);
    }

    @Override
    public int hashCode() {

        return 1;
    }


}
