package pt.ipp.isep.dei.project.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.repository.TypeAreaRepository;

@Service
public class TypeAreaService {

    @Autowired
    private TypeAreaRepository typeAreaRepository;

    /**
     * This method creates a new Type of Geographic Area and adds it to a list.
     *
     * @param name String of the new Area Type that one wishes to create and add to a list.
     * @return true or false depending on if it adds the type to the list or not.
     */
    public TypeArea createTypeAreaRepository(String name) {
        return new TypeArea(name);
    }

    /**
     * This method adds a previously created Area Type to a List of Area Types.
     * If an equal area already exists (has the same name) it will be deleted and a new one will be created its place
     * (immutable object approach).
     *
     * @param type Type of Geographic Area one wishes to add to a list.
     * @return true if the type area was successfully added, false otherwise.
     */
    public boolean addTypeArea(TypeArea type) {
        TypeArea typeArea = typeAreaRepository.findByName(type.getName());
        if (typeArea != null) {
            typeAreaRepository.delete(typeArea);
        }
        typeAreaRepository.save(type);
        return true;
    }

    /**
     * This method builds a string of all the individual members of the geoAreaType list.
     *
     * @return builds a string of all the individual members of the geoAreaType list.
     */
    public String getAllAsString() {
        StringBuilder result = new StringBuilder("---------------\n");
        Iterable<TypeArea> typeAreas = typeAreaRepository.findAll();
        for (TypeArea ta : typeAreas) {
            result.append(ta.getId()).append(") Name: ").append(ta.getName()).append(" \n");
        }
        result.append("---------------\n");
        return result.toString();
    }

    /**
     * Method to get the TypeArea Repository Size
     *
     * @return repository size
     */
    public int getSize() {
        return typeAreaRepository.findAll().size();
    }

    /**
     * Method to get a TypeArea from the Repository through a given id
     *
     * @param id selected id
     * @return Type Area corresponding to the given id
     */
    public TypeArea getTypeAreaById(int id) {

        return typeAreaRepository.findById(new Long(id)).get(); // TODO understand optional(Daniela)

    }

}
