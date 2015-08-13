package models;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.avaje.ebean.Model;

@Entity
@Table(name="admin_user")
public class User extends Model {
	
	private static Finder<String, User> finder = new Finder<String, User>(User.class);
	
	@Id
	private String id;
	@Column(unique = true, nullable = false)
	private String email;
	@Transient
	private String password;
	private String name;
	private String authToken;
	private String shaPassword;
	
	private static String getSha512(String value) {
        try {
            return MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8")).toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
	
	public String createAuthToken() {
		authToken = UUID.randomUUID().toString();
		save();
		return authToken;
	}
	
	public void deleteAuthToken() {
		authToken = null;
		save();
	}
	
	public static User find(String id) {
		return finder.byId(id);
	}
	
	public static User findUserByEmailAndPassword(String email, String password) {
		//
		if(User.find("1") == null) {
			User user = new User();
			user.setEmail("kwlee@nextree.co.kr");
			user.setId("1");
			user.setName("이기왕");
			user.setShaPassword(getSha512("123"));
			user.insert();
		}
		return finder.where().eq("email", email).eq("shaPassword", getSha512(password)).findUnique();
	}
	
	public static User findByAuthToken(String authToken) {
		//
		if(authToken == null) {
			return null;
		}
		try {
			return finder.where().eq("authToken", authToken).findUnique();
		} catch (Exception e) {
			return null;
		}
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
		this.shaPassword = getSha512(password);
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
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getShaPassword() {
		return shaPassword;
	}
	public void setShaPassword(String shaPassword) {
		this.shaPassword = shaPassword;
	}

}
