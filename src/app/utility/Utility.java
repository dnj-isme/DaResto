package app.utility;

import java.util.Scanner;

public final class Utility {
	
	private Utility() { }
	
	private static Scanner s = new Scanner(System.in);
	public static String nextLine() {
		return s.nextLine();
	}
	
	public static int getInt(String text, int min, int max) {
		int output = min - 1;
		do {
			System.out.print(text);
			try {
				output = Integer.parseInt(s.nextLine());
			} catch (Exception e) { }			
		} while (output < min || output > max);
		return output;
	}
	
	public static int random(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min);
	}
	
	public static void cls() {
		for(int i = 0; i < 50; i++) {
			System.out.println("");
		}
	}
	
	public static boolean chance(int percent) {
		return (int) (Math.random() * 100) + 1 <= percent;
	}
	
	public static void inProgress(boolean dumpStack) {
		System.out.println("Work in progres...");
		if(dumpStack) {
			Thread.dumpStack();
			System.exit(0);
		} else {
			s.nextLine();
		}
	}
	
	public static String generateName() {
		return new String(new char[] {
			(char) ('A' + random(0, 25)),
			(char) ('A' + random(0, 25))
		});
	}
	
	public static int range(int current, int min, int max) {
		return Math.max(min, Math.min(current, max));
	}
}
