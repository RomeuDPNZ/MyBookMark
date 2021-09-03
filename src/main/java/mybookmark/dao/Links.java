package mybookmark.dao;

import javax.persistence.Entity;

@Entity
public class Links extends Categorized implements CategorizedInterface {

	private String link;

	public Links() {
		
	}
	
	public Links(String name, String link, Long category) {
		this.setLink(link);
		super.setName(name);
		super.setCategory(category);
	}
	
	public Links(String name, String description, String link, Long category) {
		this(name, link, category);
		super.setDescription(description);
	}
	
	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return this.link;
	}
	
}
