package mybookmark.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.text.SimpleDateFormat;

@MappedSuperclass
public abstract class MyBookMark implements MyBookMarkInterface {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	@Column(name = "lastUpdatedOn", insertable = false, updatable = false)
	private Date lastUpdatedOn;
	@Column(name = "createdOn", insertable = false, updatable = false)
	private Date createdOn;
	private Long orderOf;
	
	@Transient
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public MyBookMark() {
		this.setDescription("");
		this.setOrderOf(1l);
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getLastUpdatedOn() {
		return this.lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public String getLastUpdatedOnString() { 
		return this.sdf.format(this.getLastUpdatedOn()).toString();
	}

	public void setLastUpdatedOnString(String lastUpdatedOn) throws Exception {
		this.setLastUpdatedOn(this.sdf.parse(lastUpdatedOn));
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedOnString() {
		return this.sdf.format(this.getCreatedOn()).toString();
	}

	public void setCreatedOnString(String createdOn) throws Exception {
		this.setCreatedOn(this.sdf.parse(createdOn));
	}
	
	public Long getOrderOf() {
		return this.orderOf;
	}

	public void setOrderOf(Long orderOf) {
		this.orderOf = orderOf;
	}
	
}
