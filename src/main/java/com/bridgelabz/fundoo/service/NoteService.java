package com.bridgelabz.fundoo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.NoteRepo;
import com.bridgelabz.fundoo.repository.UserRepo;
import com.bridgelabz.fundoo.util.JwtToken;
import com.bridgelabz.fundoo.util.Response;

@Service
public class NoteService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private NoteRepo noteRepo;

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private JwtToken jwtToken;
	
	@Autowired
	private Response response;
	
	
	public Response saveNote(Note note, String token) {
		int id = jwtToken.decodeTokenUserId(token);
		User user = userRepo.getById(id);
		note.bindUserToNotes(user);
		noteRepo.save(note);
		response.setStatusCode(200);
		response.setStatusMessage("Note is saved successfully..!");
		response.setToken(token);
		return response;
	}

	public Response deleteNote(int id) {
		Note validNote = noteRepo.findById(id).get();
		noteRepo.delete(validNote);
		response.setStatusCode(200);
		response.setStatusMessage("Note is deleted successfully..!");
		response.setToken(validNote);
		return response;
	}

	public List<Note> getAllNote(String token) {
		int id = jwtToken.decodeTokenUserId(token);
		User user = userRepo.findById(id).get();
		List<Note> notes = user.getNotes();
		return notes;
	}

	public Response updateNote(Note note, String token) {
		int id = jwtToken.decodeTokenUserId(token);
		User user = userRepo.getById(id);
		note.bindUserToNotes(user);
		noteRepo.save(note);
		response.setStatusCode(200);
		response.setStatusMessage("Note is updated successfully..!");
		response.setToken(note);
		return response;
	}
	
}
