package mybookmark.dao;

import javax.persistence.Entity;

@Entity
public class Chronometer extends Timers {

	public Chronometer() {
		
	}

	public Chronometer(String name, String dateOf, Long category) throws Exception {
		super.setName(name);
		super.setDateOfString(dateOf);
		super.setCategory(category);
	}
	
	public Chronometer(String name, String description, String dateOf, Long category) throws Exception {
		this(name, dateOf, category);
		super.setDescription(description);
	}
	
}
