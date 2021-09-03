package mybookmark.dao;

import javax.persistence.Entity;

@Entity
public class AnnualReminder extends Reminder implements AnnualReminderInterface {

	Long month;
	
	public AnnualReminder() {
		
	}
	
	public AnnualReminder(String name, Long day, Long month, Long daysBefore, Long daysAfter, Long category) throws Exception {
		this.setMonth(month);
		super.setName(name);
		super.setDay(day);
		super.setDaysBefore(daysBefore);
		super.setDaysAfter(daysAfter);
		super.setCategory(category);
	}
	
	public AnnualReminder(String name, String description, Long day, Long month, Long daysBefore, Long daysAfter, Long category) throws Exception {
		this(name, day, month, daysBefore, daysAfter, category);
		super.setDescription(description);
	}
	
	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}
	
}
