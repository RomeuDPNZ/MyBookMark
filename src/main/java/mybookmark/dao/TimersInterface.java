package mybookmark.dao;

import java.util.Date;

public interface TimersInterface {

	public void setDateOf(Date dateOf);

	public Date getDateOf();
	
	public String getDateOfString();

	public void setDateOfString(String dateOf) throws Exception;
	
}
