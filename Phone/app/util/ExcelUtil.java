/*package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import namoo.play.exam.domain.entity.enumtype.DCDifficultyLevel;
import namoo.play.exam.domain.entity.enumtype.DCQuestionType;
import namoo.play.exam.domain.entity.paper.DCExamQuestionItem;
import namoo.play.exam.domain.shared.ExamQuestionCdo;
import namoo.play.exam.domain.util.exception.NamooExamException;

public class ExcelUtil {
	//
	public static List<ExamQuestionCdo> convertToExamQuestions(File excelFile) {
		//
		List<ExamQuestionCdo> examQuestionCdos = new ArrayList<ExamQuestionCdo>();
		try {
	    	Workbook workbook = Workbook.getWorkbook(excelFile);
	    	Sheet sheet = workbook.getSheet(0);
	    	int rowSize = sheet.getRows();
	    	
	    	for(int i=1; i< rowSize ; i++) {
	    		ExamQuestionCdo examQuestionCdo = new ExamQuestionCdo();
	    		examQuestionCdo.setQuestionNo(Integer.toString(i));
	    		examQuestionCdo.setQuestionType(DCQuestionType.getQuestionType(sheet.getCell(0,i).getContents()));
	    		examQuestionCdo.setDifficultyLevel(DCDifficultyLevel.getDifficultyLevel(sheet.getCell(1,i).getContents()));
	    		examQuestionCdo.setDirection(sheet.getCell(2,i).getContents());
	    		if(DCQuestionType.SingleChoice.equals(examQuestionCdo.getQuestionType()) || 
	    				DCQuestionType.MultiChoice.equals(examQuestionCdo.getQuestionType())) {
	    			examQuestionCdo.addQuestionItem(new DCExamQuestionItem("1", sheet.getCell(3,i).getContents()));
	    			examQuestionCdo.addQuestionItem(new DCExamQuestionItem("2", sheet.getCell(4,i).getContents()));
	    			examQuestionCdo.addQuestionItem(new DCExamQuestionItem("3", sheet.getCell(5,i).getContents()));
	    			examQuestionCdo.addQuestionItem(new DCExamQuestionItem("4", sheet.getCell(6,i).getContents()));
	    		}
	    		examQuestionCdo.setAnswer(sheet.getCell(7,i).getContents());
	    		if(examQuestionCdo.getQuestionType() != null && examQuestionCdo.getDifficultyLevel() != null) {
	    			examQuestionCdos.add(examQuestionCdo);
	    		}
	    	}
		} catch (Exception e) {
			throw new NamooExamException("There is an invalid excel file.");
		}
		
		return examQuestionCdos;
	}
}
*/