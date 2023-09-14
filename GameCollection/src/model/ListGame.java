package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// create the database table
@Entity
@Table(name = "Games")
public class ListGame {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "PUBLISHER")
	private String publisher;

	// create the constructor
	public ListGame() {
		super();
	}

	// create parameterized constructor
	public ListGame(String title, String publisher) {
		super();
		this.title = title;
		this.publisher = publisher;
	}

	// get and set Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	// get and set Title
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	// get and set Publisher
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher= publisher;
	}

	// show the game details
	public String returnGameDetails() {
		return this.title+ ", by " + this.publisher;
	}
}
