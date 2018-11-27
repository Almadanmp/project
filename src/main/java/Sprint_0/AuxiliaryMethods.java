package Sprint_0;

/**
 * Auxiliary Methods to be used on other Classes
 */
public class AuxiliaryMethods {

    /**
     * Method to restrain input name so they cant be null or empty
     * @param name name inserted by user
     * @return will return true if the name is valid or it will throw an exception if Invalid
     */
    public static boolean isNameValid(String name) {
        if (name != null || !name.isEmpty()) {
            {
                return true;
            }
        }
        throw new IllegalArgumentException("Please Insert Valid Name");
    }
}