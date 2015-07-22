package controllers;

import models.Account;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.account.*;

public class AccountApp extends Controller {

    public Result index() {
    	Form<Account> userForm = new Form<Account>(Account.class);
    	
        return ok(list.render("Success~!", userForm));
    }
    
    public Result register() {
    	return ok(TODO);
    }

}
