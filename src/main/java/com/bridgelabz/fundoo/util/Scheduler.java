package com.bridgelabz.fundoo.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.repository.NoteRepo;

public class Scheduler {
	
	@Autowired
	private NoteRepo noteRepo;
	
	@Scheduled(cron = "20 * * * * ?")
	public void cronJobSch() throws Exception {
		List<Note> notesList = noteRepo.findAll();
		notesList.stream().filter(p -> p.isInTrash()).forEach(note -> {
			System.out.println("Note deleted with id: " + note.getId());
			noteRepo.delete(note);});
	}

}
