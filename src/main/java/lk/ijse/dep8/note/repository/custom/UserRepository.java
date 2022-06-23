package lk.ijse.dep8.note.repository.custom;

import lk.ijse.dep8.note.entity.User;
import lk.ijse.dep8.note.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    boolean existsUserByEmail(String email);
}
