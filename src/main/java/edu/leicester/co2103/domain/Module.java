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

/*
 * Entity class for Module
 */
@Entity
public class Module {

	// code of the module (e.g. CO2103)
	@Id
	private String code;

	// title of the module (e.g. Software Engineering)
	private String title;

	// level of the module (1, 2 or 3)
	private int level;

	// optional or not (true or false)
	private boolean optional;

	// list of sessions in the module (one to many relationship with Session class)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "module")
	private List<Session> sessions;

	/*
	 * default constructor with empty list of sessions
	 */
	public Module() {
		sessions = new ArrayList<Session>();
	}

	/*
	 * constructor with parameters (without sessions)
	 * 
	 * @param code - code of the module
	 * 
	 * @param title - title of the module
	 * 
	 * @param level - level of the module
	 * 
	 * @param optional - optional or not
	 * 
	 * @param sessions - empty list of sessions
	 */
	public Module(String code, String title, int level, boolean optional) {
		this(code, title, level, optional, new ArrayList<>());
	}

	/*
	 * constructor with parameters (with sessions)
	 * 
	 * @param code - code of the module
	 * 
	 * @param title - title of the module
	 * 
	 * @param level - level of the module
	 * 
	 * @param optional - optional or not
	 * 
	 * @param sessions - list of sessions
	 */
	public Module(String code, String title, int level, boolean optional, List<Session> sessions) {
		this.code = code;
		this.title = title;
		this.level = level;
		this.optional = optional;
		this.sessions = sessions;
	}

	/*
	 * get the code of the module
	 * 
	 * @return code of the module
	 */

	public String getCode() {
		return code;
	}

	/*
	 * set the code of the module
	 * 
	 * @param code - code of the module
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/*
	 * get the title of the module
	 * 
	 * @return title of the module
	 */

	public String getTitle() {
		return title;
	}

	/*
	 * set the title of the module
	 * 
	 * @param title - title of the module
	 */

	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * get the level of the module
	 * 
	 * @return level of the module
	 */

	public int getLevel() {
		return level;
	}

	/*
	 * set the level of the module
	 * 
	 * @param level - level of the module
	 */

	public void setLevel(int level) {
		this.level = level;
	}

	/*
	 * get the optional or not of the module
	 * 
	 * @return optional or not of the module
	 */
	public boolean isOptional() {
		return optional;
	}

	/*
	 * set the optional or not of the module
	 * 
	 * @param optional - optional or not of the module
	 */
	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	/*
	 * get the list of sessions in the module
	 * 
	 * @return list of sessions in the module
	 */
	public List<Session> getSessions() {
		return sessions;
	}

	/*
	 * set the list of sessions in the module
	 * 
	 * @param sessions - list of sessions in the module
	 */
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	/*
	 * Compares this Module object with another object for equality. (override
	 * default equals method by comparing content of the object instead of
	 * reference)
	 * 
	 * @param o - object to be compared
	 * 
	 * @return true if the objects are the same; false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Module))
			return false;
		Module module = (Module) o;
		return Objects.equals(this.code, module.code)
				&& Objects.equals(this.title, module.title)
				&& Objects.equals(this.level, module.level)
				&& Objects.equals(this.optional, module.optional)
				&& Objects.equals(this.sessions, module.sessions);
	}

	/*
	 * Computes the hash code for this Module object based on its code, title,,
	 * level, optional and sessions.
	 * 
	 * @return the computed hash code
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.code, this.title, this.level, this.optional, this.sessions);
	}

	/*
	 * Returns a string representation of this Module object.
	 * 
	 * @return a string representation of the object, including its code, title,
	 * level, optional and sessions.
	 */
	@Override
	public String toString() {
		return "Module [code=" + code + ", title=" + title + ", level=" + level + ", optional=" + optional
				+ ", sessions=" + sessions + "]";
	}

	public List<Session> getConvenors() {
		return null;
	}

}
