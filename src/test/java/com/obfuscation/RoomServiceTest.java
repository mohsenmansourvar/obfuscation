package com.obfuscation;

import com.obfuscation.domain.Room;
import com.obfuscation.service.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @Test()
    void givenNull() {
        List<String> names = Arrays.asList("mohsen", "mary", "liam");
        System.out.println(names);
    }

    @Test
    void save() {
        Room room = createRoom();
        roomService.save(room);

        Room roomById = roomService.getById(room.getId());

        assertEquals("125", roomById.getClassNumber());
        assertEquals(45, roomById.getCapacity());
    }

    @Test
    void delete() {
        Room room = createRoom();
        roomService.save(room);

        roomService.delete(room.getId());

        assertThrows(IllegalArgumentException.class, () -> roomService.getById(room.getId()));
    }

    @Test
    void update() {
        Room room = createRoom();
        roomService.save(room);

        Room updatedRoom = Room.builder()
                .classNumber("126")
                .build();
        roomService.update(room.getId(), updatedRoom);

        Room roomById = roomService.getById(room.getId());

        assertNotNull(roomById);
        assertEquals("126", roomById.getClassNumber());
        assertEquals(45, roomById.getCapacity());
    }

    @Test
    void getAllRooms() {
        Room room1 = createRoom();
        roomService.save(room1);

        Room room2 = Room.builder()
                .classNumber("120")
                .capacity(40)
                .build();
        roomService.save(room2);

        Iterable<Room> allRooms = roomService.getAllRooms();

        assertNotNull(allRooms);
    }

    private Room createRoom() {
        return Room.builder()
                .classNumber("125")
                .capacity(45)
                .build();
    }
}
