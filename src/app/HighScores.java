package app;

import java.util.Vector;

import app.model.ScoreData;
import app.utility.FileHandler;
import app.utility.Utility;
import app.utility.parser.HighScoreParser;

public class HighScores {
	private static final String fileName = "highscore.txt";
	
	private static HighScores singleton;
	public static HighScores getInstance() {
		if(singleton == null) singleton = new HighScores();
		return singleton;
	}

	private Vector<ScoreData> list = new Vector<ScoreData>();
	
	private FileHandler handler = FileHandler.getInstance();
	private HighScoreParser parser = HighScoreParser.getInstance();
	
	private HighScores() {
		readFromFile();
	}
	
	public void saveToFile() {
		String text = parser.parseToString(list);
		handler.saveFile(fileName, text);
	}
	
	public void clearData() {
		list.clear();
	}
	
	public void readFromFile() {
		list.clear();
		String text = handler.readFile(fileName);
		list.addAll(parser.extract(text));
		sort();
	}
	
	public void sort() {
		list.sort((a, b) -> b.getScore() - a.getScore());
	}
	
	public void add(ScoreData ins) {
		list.add(ins);
	}
	
	public void displayAll() {
		Utility.cls();
		for(int i = 0; i < list.size(); i++) {
			ScoreData data = list.elementAt(i);
			System.out.printf("%d. %s\t%d\n", i + 1, data.getUsername(), data.getScore());
		}
	}
}
