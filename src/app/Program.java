package app;

import app.game.Game;
import app.utility.Utility;

public class Program {
	
	private Game game = Game.getInstance();
	private HighScores hs = HighScores.getInstance();
	
	public Program() {
		System.out.println("________           __________                 __          \r\n"
			  + "\\______ \\ _____    \\______   \\ ____   _______/  |_  ____  \r\n"
			  + " |    |  \\\\__  \\    |       _// __ \\ /  ___/\\   __\\/  _ \\ \r\n"
			  + " |    `   \\/ __ \\_  |    |   \\  ___/ \\___ \\  |  | (  <_> )\r\n"
			  + "/_______  (____  /  |____|_  /\\___  >____  > |__|  \\____/ \r\n"
			  + "        \\/     \\/          \\/     \\/     \\/               ");
		System.out.println("\n\n\n\n");
		System.out.println("Press ENTER to continue...");
		Utility.nextLine();
		
		boolean stay = true;
		do {
			Utility.cls();
			System.out.println("Main Menu");
			System.out.println("");
			System.out.println("1. Play new restaurant");
			System.out.println("2. High Score");
			System.out.println("3. Exit");
			int input = Utility.getInt(">> ", 1, 3);
			switch (input) {
			case 1:
				playGame();
				break;
			case 2:
				highScore();
				break;
			case 3: default:
				stay = false;
				break;
			}
		} while (stay);
	}
	
	private void playGame() {
		Utility.cls();
		game.start();
	}

	private void highScore() {
		hs.clearData();
		hs.readFromFile();
		hs.displayAll();
		System.out.println("Press ENTER to continue...");
		Utility.nextLine();
	}

	public static void main(String[] args) {
		new Program();
	}
}
