package co.edu.uniandes.exprimentos.main;

import co.edu.uniandes.exprimentos.connection.SmartHomeServer;
import co.edu.uniandes.exprimentos.output.SmartHomeLogger;

public class Main {

	public static void main(String[] args) {
		
		int port = 2222;
		
		if(args != null && args.length > 0){
			try{
				port = Integer.parseInt(args[0]);
			}catch(NumberFormatException pe){
				SmartHomeLogger.putMessage("No se ha especificado puerto, usando puerto por defecto: 2222");
			}
		}
		
		SmartHomeServer server = new SmartHomeServer(port);
		server.initSocket();
	}

}
