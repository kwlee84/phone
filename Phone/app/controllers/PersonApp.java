package controllers;

import java.util.List;

import com.avaje.ebean.Model.Finder;

import models.Account;
import models.Person;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import play.mvc.Security.Authenticated;
import views.html.person.*;

//@Authenticated(Secured.class)
public class PersonApp extends Controller {
	//
    public Result index() {
    	Form<Person> personForm = new Form<Person>(Person.class);
    	
    	List<Person> persons = Person.findAll();
    	
        return ok(list.render(persons, personForm));
    }
    
    public Result register() {
    	//
    	Form<Person> personForm = Form.form(Person.class).bindFromRequest();
    	
    	if(personForm.hasErrors()){
			Logger.debug(personForm.errorsAsJson().toString());
			return badRequest();
		}
    	
    	Person person = personForm.get();
    	person.insert();
    	
    	return redirect(routes.PersonApp.index());
    }

    public Result remove() {
    	//
    	DynamicForm requestData = Form.form().bindFromRequest();
    	String id = requestData.get("id");
    	
    	Person.find(id).delete();
    	
    	return redirect(routes.PersonApp.index());
    }
}