package co.edu.uniandes.exprimentos.output;

public class OutputThread extends Thread {
	
	public void run(){
		while(true){
			SmartHomeLogger.doLog();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
