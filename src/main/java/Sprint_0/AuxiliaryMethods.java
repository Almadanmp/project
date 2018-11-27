package Sprint_0;

public class AuxiliaryMethods {

    public static boolean isNameValid(String name) {
        if (name != null && !name.isEmpty()) {
            {
                return false;
            }
        }
        throw new IllegalArgumentException("Please Insert Valid Name");
    }
}