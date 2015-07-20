package controllers;

import java.io.File;
import java.util.List;

import models.User;
import play.*;
import play.cache.Cache;
import play.data.Form;
import play.mvc.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import views.html.*;

public class UserApp extends Controller {
	public Result searchUser() {
		Form<UserSearch> userSearchForm = play.data.Form.form(UserSearch.class).bindFromRequest();
		UserSearch userSearch = userSearchForm.get();
		
		User searchResult = User.findByEmail(userSearch.email);
		Logger.debug(searchResult.userName);
		
		return TODO;
	}
	public Result logout() {
		//Login
		session().clear();
		response().discardCookie("greeting");
		Logger.debug("Logout....");
		flash("successmsg", "Logout successfully!");
		
		return redirect(routes.Application.index());
	}
	
	public Result login() {
		//Login
		session("user.id", "kwlee@nextree.co.kr");
		response().setCookie("greeting", "kwlee@nextree.co.kr", 60*60);
		
		User user = User.findById(1L);
		Cache.set(user.email, user);
		
		return redirect(routes.Application.index());
	}
	
	public Result editUser() {
		Form<User> userForm = play.data.Form.form(User.class).bindFromRequest();
		if(userForm.hasErrors()){
			Logger.debug(userForm.errorsAsJson().toString());
			return badRequest(newuser.render(userForm));
		}
		User.update(userForm.get());
		
		return redirect(routes.UserApp.userList());
	}
	
	public Result userList() {
		return ok(userlist.render(User.findAll(), new Form<UserSearch>(UserSearch.class)));
	}
	
	public Result user(Long id) {
		return ok(userview.render(User.findById(id)));
	}
	
	public Result editUserForm(Long id) {
		Form<User> userForm = new Form<User>(User.class);
		userForm = userForm.fill(User.findById(id));
		return ok(edituser.render(userForm));
	}
	
	public Result newUserForm() {
		Form<User> userForm = new Form<User>(User.class);
		
		return ok(newuser.render(userForm));
	}
	
	public Result newUser() {
		Form<User> userForm = play.data.Form.form(User.class).bindFromRequest();
		
		if(userForm.hasErrors()){
			Logger.debug(userForm.errorsAsJson().toString());
			return badRequest(newuser.render(userForm));
		}
		
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart photo = body.getFile("photo");
		List<FilePart> files = body.getFiles();
		for(FilePart filePart : files) {
			Logger.debug(filePart.getKey());
		}
		
		//ajax콜 했을경우
		//File file = request().body().asRaw().asFile();
		
		if(photo!=null) {
			File file = photo.getFile();
			Logger.debug(photo.getFilename());
			Logger.debug(photo.getContentType());
		}
		
		
		User user = userForm.get();
		Logger.debug(user.userName);
		Logger.debug(user.email);
		Logger.debug(user.password);
		Logger.debug(user.created.toString());
		
		User.create(user);
		
		return redirect(routes.UserApp.userList());
	}
	
	
}
