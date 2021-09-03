package mybookmark.dao;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Categorized extends MyBookMark implements CategorizedInterface {

	private Long category;
	
	public Categorized() {
		
	}
	
	public Long getCategory() {
		return this.category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}
	
}
