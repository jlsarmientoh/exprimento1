package co.edu.uniandes.exprimentos.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import co.edu.uniandes.exprimentos.service.ProcesarSensorService;
import co.edu.uniandes.exprimentos.service.SensorService;

public class SmartHomeServer {
	
	private ServerSocket serverSocket;
	private SensorService service;
	
	public SmartHomeServer(int port){
		try {
			serverSocket = new ServerSocket(port);
			service = new ProcesarSensorService();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initSocket(){
		int i = 0;
		while(true){
			try {
				Socket connectionSocket = serverSocket.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				service.procesar(in.readLine().getBytes());
				i++;
				//connectionSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
				i++;
			}
			System.out.println("Tramas Recibidas: " + i);
		}
	}

}
