package com.bridgelabz.fundoo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

	public Response deleteNote(Note note, int id) {
		Note validNote = noteRepo.findById(id).get();
		noteRepo.delete(validNote);
		response.setStatusCode(200);
		response.setStatusMessage("Note is deleted successfully..!");
		response.setToken(note);
		return response;
	}
}
