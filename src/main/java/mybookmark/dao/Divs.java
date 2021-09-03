package mybookmark.dao;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Divs extends MyBookMark implements DivsInterface {
	
	@Enumerated(EnumType.STRING)
	private Position position;
	
	public Divs() {
		
	}
	
	public Divs(String name) {
		super.setName(name);
		this.setPosition(Position.BOTTOM);
	}
	
	public Divs(String name, String description) {
		this(name);
		super.setDescription(description);
	}
	
	public Divs(String name, Position position) {
		super.setName(name);
		this.setPosition(position);
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return this.position;
	}
	
}
