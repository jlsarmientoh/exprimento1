package co.edu.uniandes.exprimentos.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class NodoConfig {
	
	public static String PRIVATE_KEY;
	
	static{
		FileInputStream fis;
		try {
			fis = new FileInputStream( new File( "./config/nodo.properties" ) );
			Properties properties = new Properties( );
	        properties.load(fis);
	        PRIVATE_KEY = properties.getProperty("security.privatekey");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
