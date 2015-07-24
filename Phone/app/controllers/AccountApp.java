package controllers;

import java.util.List;

import com.avaje.ebean.Model.Finder;

import models.Account;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import views.html.account.*;

public class AccountApp extends Controller {
	//
    public Result index() {
    	Form<Account> userForm = new Form<Account>(Account.class);
    	Finder<String, Account> finder = new Finder<String, Account>(Account.class);
    	
    	List<Account> accounts = finder.all();
    	
        return ok(list.render(accounts, userForm));
    }
    
    public Result register() {
    	//
    	Form<Account> userForm = Form.form(Account.class).bindFromRequest();
    	
    	Account account = userForm.get();
    	account.insert();
    	
    	return redirect(routes.AccountApp.index());
    }
    
    public Result remove() {
    	//
    	DynamicForm requestData = Form.form().bindFromRequest();
    	String accountNumber = requestData.get("accountNumber");
    	
    	Finder<String, Account> finder = new Finder<String, Account>(Account.class);
    	Account account = finder.byId(accountNumber);
    	account.delete();
    	
    	return redirect(routes.AccountApp.index());
    }
}