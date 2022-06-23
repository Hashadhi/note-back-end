package lk.ijse.dep8.note.repository.custom.impl;

import lk.ijse.dep8.note.entity.Note;
import lk.ijse.dep8.note.entity.User;
import lk.ijse.dep8.note.repository.CrudRepositoryImpl;
import lk.ijse.dep8.note.repository.custom.NoteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NoteRepositoryImpl extends CrudRepositoryImpl<Note, Integer> implements NoteRepository {

    @Override
    public List<Note> findAllNotesByUser(User user) {
       return entityManager.createQuery("SELECT n FROM lk.ijse.dep8.note.entity.Note n WHERE n.user= :user" , Note.class).setParameter("user", user).getResultList();
    }
}
