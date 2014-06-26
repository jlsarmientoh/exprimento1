package co.edu.uniandes.exprimentos.thread;

import co.edu.uniandes.exprimentos.event.SensorEvent;
import co.edu.uniandes.exprimentos.rulesengine.Reglas;

public class EventThread extends Thread {

	private final Reglas handler;
	private SensorEvent event;
	private boolean available;
	
	public EventThread(){
		available = true;
		//Instanciar el handler, no cambia, es inmutable
		handler = new Reglas();
	}
	
	@Override
	public synchronized void start() {
		super.start();
	}
	
	public void run(){
		this.available = false;
		
		/**
		 * LLamo al motor de reglas y pasarle el evento
		 */
		this.handler.procesarReglas(event);
		
	}
	
	public void setSensorEvent(SensorEvent e){
		this.event = e;
	}

	public boolean isAvailable() {
		return available;
	}
}
