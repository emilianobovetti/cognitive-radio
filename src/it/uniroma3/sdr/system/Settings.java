package it.uniroma3.sdr.system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Settings {

	private static String settingsFileName = "settings.conf";
	
	public static void readSettings() {
		try {
			FileInputStream propFile = new FileInputStream(settingsFileName);
			Properties prop = new Properties(System.getProperties());
			prop.load(propFile);
			System.setProperties(prop);
		} catch (FileNotFoundException e) {
			throw new SystemSettingsException("Unable to load configuration file", e);
		} catch (IOException e) {
			throw new SystemSettingsException("Unable to read configuration file", e);
		}
	}
	
	private static class SystemSettingsException extends RuntimeException {
		
		private static final long serialVersionUID = 3412284103045389241L;

		SystemSettingsException(String msg, Throwable cause) {
			super(msg, cause);
		}
	}
}
