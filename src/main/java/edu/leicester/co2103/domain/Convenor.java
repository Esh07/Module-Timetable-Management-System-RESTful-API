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

@Entity
public class Convenor {

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private Position position;

	@ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinColumn
	private List<Module> modules;
	
	public Convenor() {}
	
	public Convenor(String name, Position position) {
		this(name, position, new ArrayList<>());
	}

	public Convenor(String name, Position position, List<Module> modules) {
		super();
		this.name = name;
		this.position = position;
		this.modules = modules;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if (!(o instanceof Convenor))
			return false;
		Convenor convenor = (Convenor) o;
		return Objects.equals(this.id, convenor.id) 
				&& Objects.equals(this.name, convenor.name) 
				&& Objects.equals(this.position, convenor.position) 
				&& Objects.equals(this.modules, convenor.modules);
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(this.id, this.name, this.position, this.modules);
	}

	@Override
	public String toString() {
		return "Convenor [id=" + id + ", name=" + name + ", position=" + position + ", modules=" + modules + "]";
	}
	

}
