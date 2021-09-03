package mybookmark.dao;

import javax.persistence.Entity;

@Entity
public class MonthlyReminder extends Reminder {

	public MonthlyReminder() {
		
	}
	
	public MonthlyReminder(String name, Long day, Long daysBefore, Long daysAfter, Long category) throws Exception {
		super.setName(name);
		super.setDay(day);
		super.setDaysBefore(daysBefore);
		super.setDaysAfter(daysAfter);
		super.setCategory(category);
	}	
	
	public MonthlyReminder(String name, String description, Long day, Long daysBefore, Long daysAfter, Long category) throws Exception {
		this(name, day, daysBefore, daysAfter, category);
		super.setDescription(description);
	}	

	
}