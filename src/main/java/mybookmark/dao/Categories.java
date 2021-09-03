package mybookmark.dao;

import javax.persistence.Entity;

@Entity
public class Categories extends MyBookMark implements CategoriesInterface {

	private Long divs;
	
	public Categories() {
		
	}
	
	public Categories(String name, Long divs) {
		super.setName(name);
		this.setDivs(divs);
	}
	
	public Categories(String name, String description, Long divs) {
		this(name, divs);
		super.setDescription(description);
	}

	public void setDivs(Long divs) {
		this.divs = divs;
	}
	
	public Long getDivs() {
		return this.divs;
	}
	
}
