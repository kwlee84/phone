package controllers;

import java.io.File;
import java.io.UnsupportedEncodingException;

import com.avaje.ebean.Ebean;

import models.AttachedFile;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security.Authenticated;

@Authenticated(Secured.class)
public class AttachedFileApp extends Controller {
	//
	public Result download(String id) throws UnsupportedEncodingException {
    	//
		AttachedFile attachedFile = Ebean.find(AttachedFile.class, id);
		response().setContentType(attachedFile.getContentType());  
		String fileName = new String(attachedFile.getFileName().getBytes("UTF-8"), "ISO-8859-1");
		response().setHeader("Content-disposition","attachment; filename=" + fileName);
		return ok(new File(attachedFile.getPath()));
    }

}
