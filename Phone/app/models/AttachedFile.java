package models;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.io.FileUtils;

import com.avaje.ebean.Model;

@Entity
public class AttachedFile extends Model {
	//
	private static Finder<String, AttachedFile> finder = new Finder<String, AttachedFile>(AttachedFile.class);
	
    @Id
    private String id;
	private String path;
	private String fileName;
	private String contentType;
	
	public AttachedFile (String path, String fileName, String contentType) {
		//
		this.path = path;
		this.fileName = fileName;
		this.contentType = contentType;
	}
	
	public static AttachedFile find(String id) {
		return finder.byId(id);
	}
	public void delete() {
		FileUtils.deleteQuietly(new File(this.path));
		super.delete();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public String getFileName() {
		return fileName;
	}
	public String getContentType() {
		return contentType;
	}
	
	
	
}
