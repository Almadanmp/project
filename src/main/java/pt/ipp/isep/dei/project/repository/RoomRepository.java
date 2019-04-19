package pt.ipp.isep.dei.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.room.Room;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<Room, String> {
    List<Room> findAll();

    Optional<Room> findByRoomName(String name);

    List<Room> findAllByEnergyGridId(String name);

}
