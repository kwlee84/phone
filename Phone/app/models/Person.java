package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;

@Entity
public class Person extends Model {
	
	private static Finder<String, Person> finder = new Finder<String, Person>(Person.class);
	
	@Id
	private String id;
	private String name;
	
	public static Person find(String id) {
		return finder.byId(id);
	}
	
	public static List<Person> findAll() {
		return finder.all();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
