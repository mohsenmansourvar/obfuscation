package com.obfuscation.service;

import com.obfuscation.domain.Room;
import com.obfuscation.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    @Transactional
    public void save(Room room) {
        roomRepository.save(room);
    }

    @Override
    @Transactional
    public void update(long id, Room newRoom) {
        Room room = getById(id);

        if (newRoom.getClassNumber() != null) {
            room.setClassNumber(newRoom.getClassNumber());
        }
        if (newRoom.getCapacity() != 0) {
            room.setCapacity(newRoom.getCapacity());
        }
        roomRepository.save(room);
    }

    @Override
    @Transactional
    public void delete(long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Room getById(long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no room found by id"));
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
