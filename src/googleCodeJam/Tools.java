package googleCodeJam;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

public class Tools {
	public static String chooseFile(){
		String s = "";
		String currentDir = System.getProperty("user.dir");
		JFileChooser fileChooser = new JFileChooser(currentDir);
		if (fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
		  File file = fileChooser.getSelectedFile();
		  s = file + "";
		  s = s.replace("\\", "/");
	    }
		return s;
	}
	public static String getOutputName(String inputName){
		File inputFile = new File(inputName);
		String fileName = inputFile.getName();
		String dirName = inputFile.getParent().replace("\\", "/");
		return dirName + "/output_" + fileName;
	}
}
