package UI.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import interfaces.LoggerObserver;

public class Logger{
	
	private static List<LoggerObserver> observers = new ArrayList<LoggerObserver>();
	
	public static void  initLogger() {
		observers.add(new ConsoleLogger());
		observers.add(new FileLogger(
				LocalDateTime.now().getYear()+
        		"_"+LocalDateTime.now().getMonth()+
        		"_"+LocalDateTime.now().getDayOfMonth()+
        		"_"+LocalDateTime.now().getHour()+
        		"_"+LocalDateTime.now().getMinute()+ 
				"_Log.txt"));
	}

	public static void addObserver(LoggerObserver observer) {
		observers.add(observer);
		
	}

	public static void removeObserver(LoggerObserver observer) {
		observers.remove(observer);
		
	}

	public static void notifyObservers(String message, LogLevel loglevel) {
		for (LoggerObserver obs : observers) {
			obs.update(message, loglevel);
		}
		
	}
	
	public static void log(String message, LogLevel loglevel) {
		notifyObservers(message, loglevel);
	}
	
	public static void log(String message) {
		notifyObservers(message, LogLevel.INFO);
	}
	
	public static void log() {
		notifyObservers("", LogLevel.INFO);
	}

}
