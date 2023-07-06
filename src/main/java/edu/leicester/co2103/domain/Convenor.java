package edu.leicester.co2103.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

/*
 * Entity class for Convenor
 */
@Entity
public class Convenor {

	// id of the convenor
	@Id
	@GeneratedValue
	private long id;

	// name of the convenor
	private String name;

	// position of the convenor
	private Position position;

	// list of modules taught by the convenor (many to many relationship)
	@ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinColumn
	private List<Module> modules;

	/*
	 * default constructor
	 */
	public Convenor() {
	}

	/*
	 * constructor with parameters (without modules)
	 * 
	 * @param name - name of the convenor
	 * 
	 * @param position - position of the convenor
	 * 
	 * @param modules - list of modules taught by the convenor
	 */
	public Convenor(String name, Position position) {
		this(name, position, new ArrayList<>());
	}

	/*
	 * constructor with parameters (with modules)
	 * 
	 * @param name - name of the convenor
	 * 
	 * @param position - position of the convenor
	 * 
	 * @param modules - list of modules taught by the convenor
	 */
	public Convenor(String name, Position position, List<Module> modules) {
		super();
		this.name = name;
		this.position = position;
		this.modules = modules;
	}

	/*
	 * add module to the list of modules taught by the convenor
	 * 
	 * @param module - module to be added
	 * 
	 * @return id of the module
	 */
	public long getId() {
		return id;
	}

	/*
	 * set id of the convenor
	 * 
	 * @param id - id of the convenor
	 */
	public void setId(long id) {
		this.id = id;
	}

	/*
	 * get name of the convenor
	 * 
	 * @return name of the convenor
	 */
	public String getName() {
		return name;
	}

	/*
	 * set name of the convenor
	 * 
	 * @param name - name of the convenor
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * get position of the convenor
	 * 
	 * @return position of the convenor
	 */
	public Position getPosition() {
		return position;
	}

	/*
	 * set position of the convenor
	 * 
	 * @param position - position of the convenor
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/*
	 * get list of modules taught by the convenor
	 * 
	 * @return list of modules taught by the convenor
	 */
	public List<Module> getModules() {
		return modules;
	}

	/*
	 * set list of modules taught by the convenor
	 * 
	 * @param modules - list of modules taught by the convenor
	 */
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	/*
	 * Compares this Convenor object with another object for equality. (override
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
		if (!(o instanceof Convenor))
			return false;
		Convenor convenor = (Convenor) o;
		return Objects.equals(this.id, convenor.id)
				&& Objects.equals(this.name, convenor.name)
				&& Objects.equals(this.position, convenor.position)
				&& Objects.equals(this.modules, convenor.modules);
	}

	/*
	 * Computes the hash code for this Convenor object based on its id, name,
	 * position, and modules.
	 * 
	 * @return the computed hash code
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.position, this.modules);
	}

	/*
	 * Returns a string representation of this Convenor object.
	 * 
	 * @return a string representation of the object, including the id, name,
	 * position, and modules
	 */
	@Override
	public String toString() {
		return "Convenor [id=" + id + ", name=" + name + ", position=" + position + ", modules=" + modules + "]";
	}

}
