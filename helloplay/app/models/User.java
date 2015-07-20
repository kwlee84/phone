package models;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;

@Entity
public class User extends Model {
	private static Finder<Long, User> finder = new Finder<>(User.class);
	
	@Id
	public Long id;
	@Required
	@MaxLength(20)
	public String userName;
	@Required
	@Email
	public String email;
	@Required
	public String password;
	public File photo;
	public File photo2;
	public Date created;
	
	
	public User(){}
	
	public User(Long id, String userName, String email, String password) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	
	public static void create(User user) {
		user.save();
	}
	
	
	public static User findById(Long id) {
		return finder.byId(id);
	}
	
	public static void update(User user) {
		user.update();
	}
	
	public static void delete(User user) {
		user.delete();
	}
	
	public static List<User> findAll() {
		/*List<User> users = new ArrayList<User>();
		users.add(new User(1L, "scott1", "kwlee@nextree.co.kr", "password"));
		users.add(new User(2L, "scott2", "kwlee@nextree.co.kr", "password"));
		users.add(new User(3L, "scott3", "kwlee@nextree.co.kr", "password"));
		users.add(new User(4L, "scott4", "kwlee@nextree.co.kr", "password"));
		
		return users;*/
		return finder.all();
	}
	
	public static User findByEmail(String email) {
		//
		return finder.where().eq("email", email).findUnique();
		
	}
}
