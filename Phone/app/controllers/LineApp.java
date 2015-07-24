package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.avaje.ebean.Model.Finder;

import models.Account;
import models.Line;
import models.PayBackStyle;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import util.FileUtil;
import views.html.line.*;

public class LineApp extends Controller {

    public Result index() {
    	Finder<String, Line> finder = new Finder<String, Line>(Line.class);
    	List<Line> lines = finder.fetch("account").findList();
    	
    	return ok(list.render(lines));
    }
    
    public Result registerForm() {
    	Finder<String, Account> finder = new Finder<String, Account>(Account.class);
    	List<Account> accounts = finder.all();
    	Form<Line> lineForm = new Form<Line>(Line.class);
        return ok(newForm.render(accounts, lineForm, PayBackStyle.valuesAsList()));
    }
    
    public Result register() {
    	Form<Line> lineForm = Form.form(Line.class).bindFromRequest();
    	Line line = lineForm.get();
    	line.insert();
    	MultipartFormData body = request().body().asMultipartFormData();
    	FilePart filePart = body.getFile("capture");
    	if (filePart != null) {
    		File file = FileUtil.saveFile(filePart.getFile());
    		
    	}
        return redirect(routes.LineApp.index());
    }
    
    /*public static Result registerByImage() {
    	//
    	Form<ExamPaperCdo> examPaperForm = Form.form(ExamPaperCdo.class);
        ExamPaperCdo cdo = examPaperForm.bindFromRequest().get();
        
        MultipartFormData body = request().body().asMultipartFormData();
		for(FilePart filePart : body.getFiles()) {
			if (filePart != null) {
				File file = FileUtil.saveFile(filePart.getFile());
		        DCAttachedFile attachedFile = new DCAttachedFile(file.getPath(), filePart.getFilename(), filePart.getContentType());
				String fileUrl = attachedFileService.registerAttachedFile(attachedFile);
				cdo.addAttachedFileUrl(fileUrl);
	        }
		}
		String paperUsid = examPaperService.registerExamPaper(cdo);
        return redirect("/");
    }*/
    
}
