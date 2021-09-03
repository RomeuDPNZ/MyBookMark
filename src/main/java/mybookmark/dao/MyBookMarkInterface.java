package mybookmark.dao;

import java.util.Date;

public interface MyBookMarkInterface {

	public Long getId();

	public void setId(Long id);

	public void setName(String name);

	public String getName();
	
	public String getDescription();

	public void setDescription(String description);
	
	public Date getLastUpdatedOn();

	public void setLastUpdatedOn(Date lastUpdatedOn);

	public Date getCreatedOn();

	public void setCreatedOn(Date createdOn);
	
	public String getLastUpdatedOnString();

	public void setLastUpdatedOnString(String lastUpdatedOn) throws Exception;

	public String getCreatedOnString();

	public void setCreatedOnString(String createdOn) throws Exception;

}
