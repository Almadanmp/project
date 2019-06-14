package pt.ipp.isep.dei.project.dto.mappers;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.AreaTypeDTO;
import pt.ipp.isep.dei.project.model.areatype.AreaType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AreaTypeMapperTest {
    @Test
    void seeIfObjectToDtoWorks() {
        AreaTypeDTO typeAreaDTO = new AreaTypeDTO();
        typeAreaDTO.setName("City");
        AreaType areaType = new AreaType("City");
        assertEquals(typeAreaDTO, AreaTypeMapper.objectToDTO(areaType));
    }

    @Test
    void seeIfDtoToObjectWorks() {
        AreaTypeDTO typeAreaDTO = new AreaTypeDTO();
        typeAreaDTO.setName("City");
        AreaType areaType = new AreaType("City");
        assertEquals(areaType, AreaTypeMapper.dtoToObject(typeAreaDTO));
    }

}