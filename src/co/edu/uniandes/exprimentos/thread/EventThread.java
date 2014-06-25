package co.edu.uniandes.exprimentos.thread;

import co.edu.uniandes.exprimentos.event.SensorEvent;
import co.edu.uniandes.exprimentos.pool.listener.PoolListener;

public class EventThread extends Thread {

	private int index;
	private PoolListener listener;
	private final Object handler;
	private SensorEvent event;
	private boolean available;
	
	public EventThread(int index){
		this.index = index;
		available = true;
		//Instanciar el handler, no cambia, es inmutable
		handler = new Object(); //Cambiar por la implementaci—n del motor de reglas
	}
	
	@Override
	public synchronized void start() {
		super.start();
		this.available = false;
		this.listener.onThreadStart(index);
		/**
		 * LLamo al motor de reglas y pasarle el evento
		 */
		this.listener.onThreadFinish(index);
	}
	
	public void attachListener(PoolListener listener){
		this.listener = listener;
	}
	
	public void setSensorEvent(SensorEvent e){
		this.event = e;
	}

	public boolean isAvailable() {
		return available;
	}
}
