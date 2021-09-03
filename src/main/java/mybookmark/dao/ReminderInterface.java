package mybookmark.dao;

public interface ReminderInterface {

	public Long getDaysBefore();
	
	public void setDaysBefore(Long daysBefore);
	
	public Long getDaysAfter();
	
	public void setDaysAfter(Long daysAfter);
	
	public Long getDay();
	
	public void setDay(Long day);
	
}
