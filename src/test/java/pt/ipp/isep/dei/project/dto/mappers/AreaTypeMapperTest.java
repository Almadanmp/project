package pt.ipp.isep.dei.project.dto.mappers;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.AreaTypeDTO;
import pt.ipp.isep.dei.project.model.areatype.AreaType;

import static org.junit.jupiter.api.Assertions.*;

class AreaTypeMapperTest {
    @Test
    void seeIfObjectToDtoWorks() {
        AreaTypeDTO typeAreaDTO = new AreaTypeDTO();
        typeAreaDTO.setName("City");
        typeAreaDTO.setID(15L);
        AreaType areaType = new AreaType("City");
        areaType.setId(15L);
        assertEquals(typeAreaDTO, AreaTypeMapper.objectToDTO(areaType));
    }
    @Test
    void seeIfDtoToObjectWorks(){
        AreaTypeDTO typeAreaDTO = new AreaTypeDTO();
        typeAreaDTO.setName("City");
        typeAreaDTO.setID(15L);
        AreaType areaType = new AreaType("City");
        areaType.setId(15L);
        assertEquals(areaType, AreaTypeMapper.dtoToObject(typeAreaDTO));
    }

}