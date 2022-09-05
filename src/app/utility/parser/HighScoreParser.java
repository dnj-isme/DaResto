package app.utility.parser;

import java.util.Vector;

import app.model.ScoreData;

public class HighScoreParser implements Parser<ScoreData> {
	
	private static HighScoreParser singleton;
	
	public static HighScoreParser getInstance() {
		if(singleton == null) singleton = new HighScoreParser();
		return singleton;
	}
	
	private HighScoreParser() { }

	@Override
	public Vector<ScoreData> extract(String string) {
		Vector<ScoreData> list = new Vector<ScoreData>();

		try {
		String[] lines = string.split("\n");
			for (String data : lines) {
				list.add(ScoreData.fromString(data));
			}
		}
		catch (Exception e) { }
		return list;
	}

	@Override
	public String parseToString(Vector<ScoreData> list) {
		String output = "";
		
		for (ScoreData highScore : list) {
			output += highScore.getSaveData() + "\n";
		}
		
		return output;
	}
}
