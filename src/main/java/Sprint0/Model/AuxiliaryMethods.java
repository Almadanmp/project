package Sprint0.Model;

import java.util.List;

public class AuxiliaryMethods {

    /**
     * Method to check if an input list is Valid. It cannot be empty or Null
     * @param values - list inputed
     * @return
     */
    public static boolean checkIfListValid(List<Double> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("List is not valid");
        }
        return true;
    }
}

