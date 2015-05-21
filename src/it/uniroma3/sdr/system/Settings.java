package it.uniroma3.sdr.system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Legge le impostazioni di sistema dal file di configurazione
 * e inizializza le proprieta' di sistema.
 * 
 * @author emiliano
 *
 */
public class Settings {

	private static String settingsFileName = "settings.conf";

	/**
	 * Inizializza le proprieta' di sistema leggendo da settings.conf
	 */
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
	
	/**
	 * Data una chiave, restituisce se esiste la proprieta' di sistema
	 * corrispondente.
	 * 
	 * @param key	Chiave della proprieta'
	 * @return	Proprieta' di sistema
	 */
	public static String getProperty(String key) {
		String property = System.getProperty(key);
		
		if (property == null) {
			throw new SystemSettingsException("No property for key '" + key + "' found");
		} else {
			return property.trim();
		}
	}
	
	/**
	 * @param key	Chiave della proprieta' di sistema
	 * @return	Proprieta' convertita in intero
	 */
	public static int getIntegerProperty(String key) {
		return Integer.parseInt(getProperty(key));
	}
	
	/**
	 * @param key	Chiave della proprieta' di sistema
	 * @return	Proprieta' convertita in numero reale
	 */
	public static double getDoubleProperty(String key) {
		return Double.parseDouble(getProperty(key));
	}
	
	/**
	 * Restituisce una proprieta' di sistema  espressa come una sequenza di
	 * valori separati da virgola come un array di stringhe
	 * 
	 * @param key	Nome della proprieta' di sistema
	 * @return	Array di stringhe
	 */
	public static String[] getArrayProperty(String key) {
		String[] split = getProperty(key).split(",");
		for (int i = 0; i < split.length; i++) {
			split[i] = split[i].trim();
		}
		return split;
	}
	
	private static class SystemSettingsException extends RuntimeException {
		
		private static final long serialVersionUID = 3412284103045389241L;

		SystemSettingsException(String msg, Throwable cause) {
			super(msg, cause);
		}
		
		SystemSettingsException(String msg) {
			super(msg);
		}
	}
}
