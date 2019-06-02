package pt.ipp.isep.dei.project.dto.mappers;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.SensorTypeDTO;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SensorTypeMapperTest {

    @Test
    void seeIfObjectToDtoWorks() {
        SensorTypeDTO typeAreaDTO = new SensorTypeDTO();
        typeAreaDTO.setName("temperature");
        typeAreaDTO.setUnits("C");
        SensorType areaType = new SensorType("temperature", "C");
        assertEquals(typeAreaDTO, SensorTypeMapper.objectToDTO(areaType));
    }

    @Test
    void seeIfDtoToObjectWorks() {
        SensorTypeDTO typeAreaDTO = new SensorTypeDTO();
        typeAreaDTO.setName("temperature");
        typeAreaDTO.setUnits("C");
        SensorType areaType = new SensorType("temperature", "C");
        assertEquals(areaType, SensorTypeMapper.dtoToObject(typeAreaDTO));
    }

}