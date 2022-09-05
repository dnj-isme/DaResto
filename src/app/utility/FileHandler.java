package app.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public final class FileHandler {

	private static FileHandler singleton;
	
	public static FileHandler getInstance() {
		if(singleton == null) singleton = new FileHandler();
		return singleton;
	}
	
	private FileHandler() {	}
	
	// the code starts here
	public boolean saveFile(String fileName, String data) {
		PrintWriter writer = null;
		try {
			String path = "files/" + fileName;
			File file = new File(path);
			if(!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(fw);
			writer = new PrintWriter(bw);
			
			writer.print(data);
			
			writer.close();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occured when attepting to write " + fileName);
			writer.close();
			return false;
		}
	}
	
	public String readFile(String fileName) {
		Scanner scanner = null;
		String output = "";
		try {
			String path = "files/" + fileName;
			File file = new File(path);
			
			scanner = new Scanner(file);
			
			while(scanner.hasNextLine()) {
				output += scanner.nextLine() + "\n";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occured when attempting to read " + fileName);
		}
		scanner.close();
		return output;
	}
}
