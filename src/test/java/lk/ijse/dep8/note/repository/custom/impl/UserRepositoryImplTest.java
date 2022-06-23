package lk.ijse.dep8.note.repository.custom.impl;

import lk.ijse.dep8.note.config.WebAppConfig;
import lk.ijse.dep8.note.config.WebRootConfig;
import lk.ijse.dep8.note.entity.User;
import lk.ijse.dep8.note.repository.custom.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Transactional()
@SpringJUnitConfig({WebRootConfig.class, WebAppConfig.class})
class UserRepositoryImplTest {


    @Autowired
    private UserRepository userRepository;


    @Test
    void save() {
        //given
        User hashadhi = new User(UUID.randomUUID().toString(), "hash@gmail.com", "1234", "Hashadhi");
        //when
        User savedUser = userRepository.save(hashadhi);
        //then
        assertEquals(hashadhi, savedUser);
    }

    @Test
    void deleteById() {
        //given
        User hashadhi = new User(UUID.randomUUID().toString(), "hash@gmail.com", "1234", "Hashadhi");
        userRepository.save(hashadhi);
        //when
        assertTrue(userRepository.existsById(hashadhi.getId()));
        userRepository.deleteById(hashadhi.getId());
        //then
        assertFalse(userRepository.existsById(hashadhi.getId()));

    }

    @Test
    void existsById() {

    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        //given
        User hashadhi = new User(UUID.randomUUID().toString(), "hash@gmail.com", "1234", "Hashadhi");
        User lahiru = new User(UUID.randomUUID().toString(), "hash21@gmail.com", "1234", "Lahiru");
        userRepository.save(hashadhi);
        userRepository.save(lahiru);
        //when
        List<User> users = userRepository.findAll();
        //then
        assertTrue(users.size()>=2);
    }

    @Test
    void count() {
        //given
        User hashadhi = new User(UUID.randomUUID().toString(), "hash@gmail.com", "1234", "Hashadhi");
        User lahiru = new User(UUID.randomUUID().toString(), "hash21@gmail.com", "1234", "Lahiru");
        userRepository.save(hashadhi);
        userRepository.save(lahiru);
        assertTrue(userRepository.findAll().size()>=2);

    }
}