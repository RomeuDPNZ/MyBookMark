package mybookmark.dao;

import java.util.Date;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Timers extends Categorized implements TimersInterface {
	
	private Date dateOf;

	public Timers() {
		
	}

	public void setDateOf(Date dateOf) {
		this.dateOf = dateOf;
	}

	public Date getDateOf() {
		return this.dateOf;
	}
	
	public String getDateOfString() {
		return super.sdf.format(this.getDateOf()).toString();
	}

	public void setDateOfString(String dateOf) throws Exception {
		this.setDateOf(super.sdf.parse(dateOf));
	}

}
