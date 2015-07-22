package controllers;

import play.*;
import play.mvc.*;

import views.html.account.*;

public class AccountApp extends Controller {

    public Result index() {
    	Form<Account> userForm = new Form<Account>(User.class);
    	
        return ok(list.render("Success~!", userForm));
    }
    
    public Result register() {
    	return ok(TODO);
    }

}
