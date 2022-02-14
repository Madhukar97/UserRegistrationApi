package com.bridgelabz.fundoo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String title;
	
	@Column
	private String content;
	
	@Column
	private String color;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	
	public void bindUserToNotes(User user) {
		this.user=user;
	}
}
