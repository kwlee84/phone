package controllers;

import java.util.List;

import com.avaje.ebean.Model.Finder;

import models.Account;
import models.User;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.mvc.*;
import play.mvc.Http.Context;
import views.html.login.*;

public class LoginApp extends Controller {
	//
	public static final String AUTH_TOKEN = "authToken";
	
	public Result loginForm() {
		//
		return ok(login.render());
	}
	
    public Result login() {
    	Form<Account> userForm = new Form<Account>(Account.class);
    	DynamicForm bindedForm = Form.form().bindFromRequest();
    	
    	User user = User.findUserByEmailAndPassword(bindedForm.get("email"), bindedForm.get("password"));
    	
    	if(user == null) {
    		return unauthorized(unauthorized.render());
    	}
    	
    	String authToken = user.createAuthToken();
    	//response().setCookie(AUTH_TOKEN, authToken, 60*60);
    	session("authToken", authToken);
    	session("email", user.getEmail());
    	
        return redirect(routes.LineApp.index());
    }
    
    public Result logout() {
    	//
    	//response().discardCookie(AUTH_TOKEN);
    	User.findByAuthToken(session().get("authToken")).deleteAuthToken();
    	session().remove("authToken");
    	session().remove("email");
    	
    	return redirect(routes.LoginApp.login());
    	
    }
    
    public Result template() {
    	//
    	//response().discardCookie(AUTH_TOKEN);
    	User.findByAuthToken(session().get("authToken")).deleteAuthToken();
    	session().remove("authToken");
    	session().remove("email");
    	
    	return ok(template.render());
    	
    }
    
}