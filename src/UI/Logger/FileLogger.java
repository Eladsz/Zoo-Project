package UI.Logger;

import interfaces.LoggerObserver;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FileLogger implements LoggerObserver {
	private String fileName;
	public FileLogger(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public void update(String message, LogLevel loglevel) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(LocalDateTime.now() +
            		" : " + loglevel.toString() + 
            		" : " + message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

}
