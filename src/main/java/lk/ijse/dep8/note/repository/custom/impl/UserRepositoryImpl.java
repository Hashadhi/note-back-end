package lk.ijse.dep8.note.repository.custom.impl;

import lk.ijse.dep8.note.entity.User;
import lk.ijse.dep8.note.repository.CrudRepositoryImpl;
import lk.ijse.dep8.note.repository.custom.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl extends CrudRepositoryImpl<User, String> implements UserRepository {

    @Override
    public boolean existsUserByEmail(String email) {
       return !entityManager.createQuery("SELECT u FROM lk.ijse.dep8.note.entity.User u WHERE u.email =:email").setParameter("email", email).getResultList().isEmpty();
    }
}
