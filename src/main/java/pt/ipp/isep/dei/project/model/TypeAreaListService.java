package pt.ipp.isep.dei.project.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.repository.TypeAreaRepository;

@Service
public class TypeAreaListService {

    @Autowired
    private TypeAreaRepository typeAreaRepository;

        /**
     * This method creates a new Type of Geographic Area and adds it to a list.
     *
     * @param name String of the new Area Type that one wishes to create and add to a list.
     * @return true or false depending on if it adds the type to the list or not.
     */
    public TypeArea createTypeAreaRepository(String name) {
        TypeArea typeArea = new TypeArea(name);
        return typeArea;
    }

    /**
     * This method adds a previously stated Area Type to a List of Area Types.
     *
     * @param type Type of Geographic Area one wishes to add to a list.
     * @return true or false depending on the list containing or not the type input already.
     */
    public boolean addTypeArea(TypeArea type) {
        // TypeArea typeArea = typeAreaRepository.findByName(type.getName());
        //if (typeArea!=null) {
        //  typeAreaRepository.delete(typeArea);
        typeAreaRepository.save(type);
        return true;
    }

    /**
     * This method builds a string of all the individual members of the geoAreaType list.
     *
     * @return builds a string of all the individual members of the geoAreaType list.
     */
    public String toString() {
        StringBuilder result = new StringBuilder("---------------\n");
        Iterable<TypeArea> typeAreas = typeAreaRepository.findAll();

        result.append("---------------\n");
        return typeAreaRepository.toString();
    }

//
//    /**
//     * This method receives an index as parameter and gets a type area from Type Area list.
//     *
//     * @param index the index of the type area
//     * @return returns Type Area that corresponds to index.
//     */
//    public TypeArea get(int index) {
//        if (this.typeAreas.isEmpty()) {
//            throw new IndexOutOfBoundsException("The type area list is empty.");
//        }
//        return this.typeAreas.get(index);
//    }


//    @Override
//    public boolean equals(Object testObject) {
//        if (this == testObject) {
//            return true;
//        }
//        if (!(testObject instanceof TypeAreaListService)) {
//            return false;
//        }
//        TypeAreaListService list = (TypeAreaListService) testObject;
//        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
//    }
//
//    @Override
//    public int hashCode() {
//        return 1;
//    }
}
