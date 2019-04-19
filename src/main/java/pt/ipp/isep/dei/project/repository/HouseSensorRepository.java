package pt.ipp.isep.dei.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.room.RoomSensor;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseSensorRepository extends CrudRepository<RoomSensor, String> {

    List<RoomSensor> findAll();

    List<RoomSensor> findAllById(String id);

    List<RoomSensor> findAllByRoomId(String name);

    Optional<RoomSensor> findByName(String name);

}
