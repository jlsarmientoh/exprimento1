package co.edu.uniandes.exprimentos.main;

import co.edu.uniandes.exprimentos.connection.SmartHomeServer;

public class Main {

	public static void main(String[] args) {
		
		SmartHomeServer server = new SmartHomeServer(2222);
		server.initSocket();
	}

}
