package mybookmark.dao;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Reminder extends Categorized implements ReminderInterface {

	Long day;
	Long daysBefore;
	Long daysAfter;
	
	public Reminder() {
		
	}

	public Long getDay() {
		return this.day;
	}

	public void setDay(Long day) {
		this.day = day;
	}
	
	public Long getDaysBefore() {
		return this.daysBefore;
	}
	
	public void setDaysBefore(Long daysBefore) {
		this.daysBefore = daysBefore;
	}
	
	public Long getDaysAfter() {
		return this.daysAfter;
	}
	
	public void setDaysAfter(Long daysAfter) {
		this.daysAfter = daysAfter;
	}
	
}
