package com.bridgelabz.fundoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.model.Note;

@Repository
public interface NoteRepo extends JpaRepository<Note, Integer> {


}
