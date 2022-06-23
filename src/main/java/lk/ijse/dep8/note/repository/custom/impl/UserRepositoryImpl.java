package lk.ijse.dep8.note.repository.custom.impl;

import lk.ijse.dep8.note.entity.User;
import lk.ijse.dep8.note.repository.CrudRepositoryImpl;
import lk.ijse.dep8.note.repository.custom.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends CrudRepositoryImpl<User, String> implements UserRepository {
}
