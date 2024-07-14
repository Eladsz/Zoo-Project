package interfaces;

import UI.Logger.LogLevel;

public interface LoggerObserver {
	void update(String message, LogLevel loglevel);
}
