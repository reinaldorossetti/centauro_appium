package centauro.properties;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class LoadProperties {

	private static final String PROPERTIES_FILE_NAME = "config.properties";
	
	public static String getConfig(String property) {
		return LoadProperties.get(PROPERTIES_FILE_NAME, property);
	}
	
	private static String get(String file, String property) {
		Properties prop = new Properties();
		InputStream fileProperties = null;

		try {
			fileProperties = LoadProperties.class.getClassLoader().getResourceAsStream(file);
			prop.load(fileProperties);
			fileProperties.close();
			return prop.getProperty(property);
		} catch (Exception e) {
			Logger.getGlobal().severe(e.getMessage() + " - Access file ERROR (property file '" + file + "')");
			e.printStackTrace();
		}
		return "";
	}
}
