package UI.Logger;

import interfaces.LoggerObserver;

public class ConsoleLogger implements LoggerObserver {

	@Override
	public void update(String message, LogLevel loglevel) {
		System.out.println(message);		
	}

}
