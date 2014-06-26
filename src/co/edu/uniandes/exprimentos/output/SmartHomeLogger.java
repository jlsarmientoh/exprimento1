package co.edu.uniandes.exprimentos.output;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmartHomeLogger {

	private static BlockingQueue<String> outputQeue = new ArrayBlockingQueue<String>(1000);
	
	private final static Logger log = Logger.getLogger(SmartHomeLogger.class.getName());
	
	static{
		log.setLevel(Level.INFO);
		try {
			Handler finelHanlder = new FileHandler("./smarthome.log");
			finelHanlder.setFormatter(new java.util.logging.XMLFormatter());
			log.addHandler(finelHanlder);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void putMessage(String msg){
		log.log(Level.INFO, msg);
		/*try {
			outputQeue.put(msg);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public static void doLog(){
		//if(!outputQeue.isEmpty()){
			try {
				//log.log(Level.INFO, "cualqueir pendejada");
				String msg = outputQeue.poll(100, TimeUnit.MILLISECONDS);
				if(msg != null)
					log.log(Level.INFO, msg);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//}
	}
}
