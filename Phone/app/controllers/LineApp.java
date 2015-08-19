package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.h2.util.StringUtils;

import models.Account;
import models.AttachedFile;
import models.Line;
import models.PayBackStyle;
import models.Person;
import models.Sequence;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import play.libs.Json;
import util.FileUtil;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model.Finder;
import com.avaje.ebean.annotation.Transactional;

import views.html.line.*;

@Authenticated(Secured.class)
public class LineApp extends Controller {

	
    public Result index() {
    	DynamicForm requestData = Form.form().bindFromRequest();
    	String personId = requestData.get("personId");
    	List<Line> lines = null;
    	if(StringUtils.isNullOrEmpty(personId)) {
    		lines = Line.findAll();
    	} else {
    		lines = Line.findByPerson(personId);
    	}
    	List<Person> persons = Person.findAll();
    	return ok(list.render(lines, persons, personId));
    }
    
    public Result registerForm() {
    	List<Account> accounts = Account.findAll();
    	Form<Line> lineForm = new Form<Line>(Line.class);
    	List<PayBackStyle> payBackStyles = PayBackStyle.valuesAsList();
    	List<Person> persons = Person.findAll();
        return ok(newForm.render(accounts, lineForm, payBackStyles, persons));
    }
    
    @Transactional
    public Result register() {
		Line line = Form.form(Line.class).bindFromRequest().get();
    	FilePart filePart = request().body().asMultipartFormData().getFile("capture");
    	if (filePart != null) {
    		File file = FileUtil.saveFile(filePart.getFile());
    		AttachedFile captureFile = new AttachedFile(file.getPath(), filePart.getFilename(), filePart.getContentType());
    		captureFile.setId(String.valueOf(Sequence.getSequence(Sequence.ATTACHED_FILE)));
    		line.setCaptureFile(captureFile);
    	}
    	line.getBusinessInfo().setId(String.valueOf(Sequence.getSequence(Sequence.BUSINESS)));
    	line.setId(String.valueOf(Sequence.getSequence(Sequence.LINE)));
    	if("".equals(line.getAccount().getAccountNumber())) {
    		line.setAccount(null);
    	}
    	line.insert();
    	
        return redirect(routes.LineApp.index());
    }
    
    public Result show(String id) {
    	Line line = Line.find(id);
    	return ok(show.render(line));
    }
    
    public Result editForm(String id) {
    	List<Account> accounts = Account.findAll();
    	Form<Line> lineForm = new Form<Line>(Line.class);
    	List<PayBackStyle> payBackStyles = PayBackStyle.valuesAsList();
    	lineForm = lineForm.fill(Line.find(id));
    	
    	return ok(editForm.render(lineForm, accounts, payBackStyles));
    }
    
    @Transactional
    public Result edit() {
    	Line line = Form.form(Line.class).bindFromRequest().get();
    	FilePart filePart = request().body().asMultipartFormData().getFile("capture");
    	if (filePart != null) {
    		File file = FileUtil.saveFile(filePart.getFile());
    		AttachedFile captureFile = new AttachedFile(file.getPath(), filePart.getFilename(), filePart.getContentType());
    		captureFile.setId(String.valueOf(Sequence.getSequence(Sequence.ATTACHED_FILE)));
    		line.setCaptureFile(captureFile);
    	}
    	Line.setUpdatedValues(line.getId(), line).update();
    	
    	return redirect(routes.LineApp.show(line.getId()));
    }
    
    @Transactional
    public Result remove() {
    	DynamicForm requestData = Form.form().bindFromRequest();
    	String id = requestData.get("id");
    	
    	Line line = Line.find(id);
    	if(line.getCaptureFile() != null) {
    		FileUtils.deleteQuietly(new File(line.getCaptureFile().getPath()));
    	}
    	line.delete();
    	
    	return redirect(routes.LineApp.index());
    }

    @Transactional
    public Result removeAttachedFile() {
    	DynamicForm bindedForm = Form.form().bindFromRequest();
    	String lineId = bindedForm.get("lineId");
    	String attachedFileId = bindedForm.get("attachedFileId");
    	Line line = Line.find(lineId);
    	line.setCaptureFile(null);
    	line.update();
    	AttachedFile.find(attachedFileId).delete();
    	
    	return ok(Json.toJson("ok"));
    }
}
