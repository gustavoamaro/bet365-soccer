package br.com.gustavoamaro.bet365;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Parameters {
	private static Properties prop;
	private static InputStream in;
	
	static {
		try {
			prop = new Properties();
			in = ClassLoader.getSystemResourceAsStream("application.properties");
			prop.load(in);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public static float getBellowOdd() {
		try {
			return Float.parseFloat(prop.getProperty("odd.bellow"));
		} catch (Exception e) {
			return Float.MAX_VALUE;
		}
	}

	public static float getAboveOdd() {
		try {
			return Float.parseFloat(prop.getProperty("odd.above"));
		} catch (Exception e) {
			return Float.MIN_VALUE;
		}
	}

	public static String getDate() {
		return prop.getProperty("match.date");
	}
}