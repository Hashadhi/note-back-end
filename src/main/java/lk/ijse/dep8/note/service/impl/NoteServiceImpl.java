package lk.ijse.dep8.note.service.impl;

import lk.ijse.dep8.note.dto.NoteDTO;
import lk.ijse.dep8.note.entity.Note;
import lk.ijse.dep8.note.entity.User;
import lk.ijse.dep8.note.repository.custom.NoteRepository;
import lk.ijse.dep8.note.repository.custom.UserRepository;
import lk.ijse.dep8.note.service.NotesService;
import lk.ijse.dep8.note.service.exception.NotFoundException;
import lk.ijse.dep8.note.service.exception.UnAuthorizedAccessException;
import lk.ijse.dep8.note.service.util.EntityDTOTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class NoteServiceImpl implements NotesService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final EntityDTOTransformer transformer;

    public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository, EntityDTOTransformer transformer) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.transformer = transformer;
    }

    @Override
    public NoteDTO saveNote(NoteDTO note) throws NotFoundException {
        Note noteEntity = transformer.getNoteEntity(note);
        noteEntity.setUser(getUser(note.getUserId()));
        return transformer.getNoteDTO(noteRepository.save(noteEntity));
    }

    @Override
    public void deleteNote(String userId, int noteId) throws NotFoundException {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new NotFoundException("Invalid note id"));
        if(getUser(userId) != note.getUser()) throw new UnAuthorizedAccessException("User is not authorized to delete the note");
        noteRepository.deleteById(noteId);
    }

    @Override
    public List<NoteDTO> getAllNotes(String userId) {
        return noteRepository.findAllNotesByUser(getUser(userId)).stream().map(transformer::getNoteDTO).collect(Collectors.toList());
    }

    private User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Invalid user Id"));
    }
}
