package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.util.ErrorMessage;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class NoteService {
    private NoteMapper noteMapper;
    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public Integer saveOne(Note note) {
        return noteMapper.insertOne(note);
    }

    public List<Note> findAllByUserIdOrderByIdDesc(Integer userId) {
        return noteMapper.selectAllByUserIdOrderByIdDesc(userId);
    }

    public Integer updateOne(Note note) {
        Note foundNote = noteMapper.selectOneByUserIdAndId(note.getUserId(), note.getId());
        Integer updatedId;
        if (Objects.nonNull(foundNote)) {
            updatedId = noteMapper.updateOne(note);
        } else {
            updatedId = -1;
        }
        return updatedId;
    }

    public boolean isValidNote(Note note, BindingResult bindingResult) {
        boolean isValidNote;
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<ObjectError> concernErrors = allErrors.stream().filter(error ->
                ErrorMessage.TITLE_REQUIRED_MESSAGE.equals(error.getDefaultMessage())
                        || ErrorMessage.DESCRIPTION_REQUIRED_MESSAGE.equals(error.getDefaultMessage())).collect(Collectors.toList());
        if (concernErrors.isEmpty()) {
            isValidNote = true;
        } else {
            isValidNote = false;
        }
        return isValidNote;
    }

    public int deleteOneByUserIdAndId(Integer userId, Integer noteId) {
        int number = 0;
        Note foundNote = noteMapper.selectOneByUserIdAndId(userId, noteId);
        if (Objects.nonNull(foundNote)) {
            noteMapper.deleteOneByUserIdAndId(userId, noteId);
            number = 1;
        }
        return number;
    }

    public List<Note> findAllByUsernameOrderByIdDesc(String username) {
        return noteMapper.selectAllByUsernameOrderByIdDesc(username);
    }
}
