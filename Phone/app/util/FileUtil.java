package util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import exception.PhoneException;
import play.Play;

public class FileUtil {
	//
	public static File saveFile(File fromFile) {
		//
		//String path = Play.application().configuration().getString("fileUploadPath");
		String path = Play.application().path().getPath();
		File toFile = new File(path + DateFormatUtils.format(new Date(),"yyyyMMdd"), String.valueOf(System.currentTimeMillis()));
		
		/*try {
			FileUtils.moveFile(fromFile, toFile);
		} catch (IOException e) {
			throw new PhoneException("Failed to save file.");
		}*/
		
		return toFile;
	}
}
