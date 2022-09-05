package app.model;

public class ScoreData {
	private String username;
	private int score;
	public ScoreData(String username, int score) {
		super();
		this.username = username;
		this.score = score;
	}
	public String getUsername() {
		return username;
	}
	public int getScore() {
		return score;
	}
	public String getSaveData() {
		return String.format("%s#%d", username, score);
	}
	public static ScoreData fromString(String input) throws Exception{
		String[] buff = input.split("#");
		String username = buff[0];
		int score = Integer.parseInt(buff[1]);
		return new ScoreData(username, score);
	}
}
