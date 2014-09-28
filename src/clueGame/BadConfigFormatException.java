package clueGame;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BadConfigFormatException extends RuntimeException {

	public BadConfigFormatException(String message) {
		super(message + " Clue cannot run.");
		
		//Print to log file
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("logfile.txt"));
			writer.write(message + " Clue cannot run.");
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	}
}
