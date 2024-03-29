package lk.ijse.dep8.note.repository.custom;

import lk.ijse.dep8.note.entity.Note;
import lk.ijse.dep8.note.entity.User;
import lk.ijse.dep8.note.repository.CrudRepository;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note , Integer> {

    List<Note> findAllNotesByUser(User user);
}
