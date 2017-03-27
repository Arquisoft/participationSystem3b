package es.uniovi.asw.reportwriter;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;

public class Log {
	public static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private Log (){
		PropertyConfigurator.configure("log4j.properties");
	};
	
	public static void info(String...errors) {
		for (String error: errors)
			logger.warning(error);
	}
	
	public static void config() {
		try {
			FileHandler file = new FileHandler("src/test/resources/Log.log", true);
			file.setFormatter(new LogFormatter());
			logger.addHandler(file);
			logger.setUseParentHandlers(false);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
