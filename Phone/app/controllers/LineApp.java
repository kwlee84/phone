package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import models.Account;
import models.AttachedFile;
import models.Line;
import models.PayBackStyle;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import util.FileUtil;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model.Finder;

import views.html.line.*;

@Authenticated(Secured.class)
public class LineApp extends Controller {

	
    public Result index() {
    	List<Line> lines = Ebean.find(Line.class).fetch("account").fetch("captureFile").findList();
    	return ok(list.render(lines));
    }
    
    public Result registerForm() {
    	//http://www.avaje.org/ebean/introquery_joinquery.html 참고
    	List<Account> accounts = Ebean.find(Account.class).findList();
    	Form<Line> lineForm = new Form<Line>(Line.class);
    	List<PayBackStyle> PayBackStyles = PayBackStyle.valuesAsList();
        return ok(newForm.render(accounts, lineForm, PayBackStyles));
    }
    
    public Result register() {
		Line line = Form.form(Line.class).bindFromRequest().get();
    	FilePart filePart = request().body().asMultipartFormData().getFile("capture");
    	if (filePart != null) {
    		File file = FileUtil.saveFile(filePart.getFile());
    		AttachedFile captureFile = new AttachedFile(file.getPath(), filePart.getFilename(), filePart.getContentType());
    		captureFile.insert();
    		line.setCaptureFile(captureFile);
    	}
    	line.insert();
        return redirect(routes.LineApp.index());
    }
    
    public Result show(String id) {
    	//Line line = Ebean.find(Line.class, id);
    	Line line = new Finder<String, Line>(Line.class).fetch("").findUnique();
    	return ok(show.render(line));
    }
    
    public Result editForm(String id) {
    	return TODO;
    }
    
    public Result edit() {
    	return TODO;
    }
    
    public Result remove() {
    	return TODO;
    }
    
}
