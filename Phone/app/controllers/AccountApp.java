package controllers;

import java.util.List;

import com.avaje.ebean.Model.Finder;
import com.avaje.ebean.annotation.Transactional;

import models.Account;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import play.mvc.Security.Authenticated;
import views.html.account.*;

@Authenticated(Secured.class)
public class AccountApp extends Controller {
	//
    public Result index() {
    	Form<Account> userForm = new Form<Account>(Account.class);
    	
    	List<Account> accounts = Account.findAll();
    	
        return ok(list.render(accounts, userForm));
    }
    
    @Transactional
    public Result register() {
    	//
    	Form<Account> accountForm = Form.form(Account.class).bindFromRequest();
    	
    	if(accountForm.hasErrors()){
			Logger.debug(accountForm.errorsAsJson().toString());
			return badRequest();
		}
    	
    	Account account = accountForm.get();
    	account.insert();
    	
    	return redirect(routes.AccountApp.index());
    }
    
    @Transactional
    public Result remove() {
    	//
    	DynamicForm requestData = Form.form().bindFromRequest();
    	String accountNumber = requestData.get("accountNumber");
    	
    	Account.find(accountNumber).delete();
    	
    	return redirect(routes.AccountApp.index());
    }
}