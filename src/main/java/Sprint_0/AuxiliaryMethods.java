package Sprint_0;

public class AuxiliaryMethods {

    public static boolean isNameValid(String name) {
        if (name != null && !name.isEmpty()) {
            {
                return true;
            }
        }
        throw new IllegalArgumentException("Please Insert Valid Name");
    }
}