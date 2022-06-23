package lk.ijse.dep8.note.repository.custom.impl;

import lk.ijse.dep8.note.entity.Note;
import lk.ijse.dep8.note.repository.CrudRepositoryImpl;
import lk.ijse.dep8.note.repository.custom.NoteRepository;
import org.springframework.stereotype.Repository;

@Repository
public class NoteRepositoryImpl extends CrudRepositoryImpl<Note, Integer> implements NoteRepository {
}
