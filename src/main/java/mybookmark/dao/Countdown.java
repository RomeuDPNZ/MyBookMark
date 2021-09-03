package mybookmark.dao;

import javax.persistence.Entity;

@Entity
public class Countdown extends Timers {

	public Countdown() {
		
	}

	public Countdown(String name, String dateOf, Long category) throws Exception {
		super.setName(name);
		super.setDateOfString(dateOf);
		super.setCategory(category);
	}
	
	public Countdown(String name, String description, String dateOf, Long category) throws Exception {
		this(name, dateOf, category);
		super.setDescription(description);
	}

}
