package edu.leicester.co2103.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Module {

	@Id
	private String code;
	private String title;
	private int level;
	private boolean optional;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn (name = "module")
	private List<Session> sessions;

	public Module() {
		sessions = new ArrayList<Session>();
	}

	public Module(String code, String title, int level, boolean optional) {
		this(code, title, level, optional, new ArrayList<>());
	}

	public Module(String code, String title, int level, boolean optional, List<Session> sessions) {
		this.code = code;
		this.title = title;
		this.level = level;
		this.optional = optional;
		this.sessions = sessions;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if (!(o instanceof Module))
			return false;
		Module module = (Module) o;
		return Objects.equals(this.code, module.code) 
				&& Objects.equals(this.title, module.title) 
				&& Objects.equals(this.level, module.level) 
				&& Objects.equals(this.optional, module.optional)
				&& Objects.equals(this.sessions, module.sessions);
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(this.code, this.title, this.level, this.optional, this.sessions);
	}

	@Override
	public String toString() {
		return "Module [code=" + code + ", title=" + title + ", level=" + level + ", optional=" + optional
				+ ", sessions=" + sessions + "]";
	}

    public List<Session> getConvenors() {
        return null;
    }
	
	
}
