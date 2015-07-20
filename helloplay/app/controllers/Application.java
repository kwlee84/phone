package controllers;

import models.User;
import play.*;
import play.cache.Cache;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public Result index() {
    	String email = session().get("user.id");
    	String msg = "Welcome";
    	if(email!=null) {
    		User user = (User)Cache.get(email);
    		msg += (" " + user.userName);
    	}
    	
        return ok(index.render(msg));
    }

}
