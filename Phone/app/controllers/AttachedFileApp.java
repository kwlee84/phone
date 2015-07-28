package controllers;

import java.io.File;

import com.avaje.ebean.Ebean;

import models.AttachedFile;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security.Authenticated;

@Authenticated(Secured.class)
public class AttachedFileApp extends Controller {
	//
	public Result download(String id) {
    	//
		AttachedFile attachedFile = Ebean.find(AttachedFile.class, id);
		response().setContentType(attachedFile.getContentType());  
		response().setHeader("Content-disposition","attachment; filename=" + attachedFile.getFileName()); 
		return ok(new File(attachedFile.getPath()));
    }

}
