package mybookmark.dao;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Settings extends MyBookMark implements SettingsInterface {
	
	@Enumerated(EnumType.STRING)
	private Theme theme;

	public Settings() {
		
	}
	
	public Settings(String name, Theme theme) {
		super.setName(name);
		this.setTheme(theme);
	}
	
	public Settings(String name, String description, Theme theme) {
		this(name, theme);
		super.setDescription(description);
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
	
}
