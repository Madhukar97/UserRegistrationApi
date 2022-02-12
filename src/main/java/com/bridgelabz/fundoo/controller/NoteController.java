package com.bridgelabz.fundoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.service.IService;
import com.bridgelabz.fundoo.service.NoteService;
import com.bridgelabz.fundoo.util.Response;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class NoteController {

	@Autowired
	private IService iService;
	
	@Autowired
	private NoteService noteService;
	
	@PutMapping("/users/saveNote/")
	public ResponseEntity<Response> saveNote( @RequestHeader String token, @RequestBody Note note){
		Response response = noteService.saveNote(note , token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/deleteNote/{id}")
	public ResponseEntity<Response> deleteNote(@PathVariable int id, @RequestBody Note note){
		Response response = noteService.deleteNote(note , id);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	
}
