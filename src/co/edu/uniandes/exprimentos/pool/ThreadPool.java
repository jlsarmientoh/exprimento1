package co.edu.uniandes.exprimentos.pool;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.exprimentos.output.SmartHomeLogger;
import co.edu.uniandes.exprimentos.thread.EventThread;

public class ThreadPool{
	
	private final int multiplicador = 2;
	private int poolSize = 200;
	private List<EventThread> threads;
	
	public ThreadPool(){
		threads = new ArrayList<EventThread>();
		
		for(int i = 0; i < this.poolSize; i++){
			EventThread hilo = new EventThread();
			threads.add(hilo);
		}
		
		SmartHomeLogger.putMessage("Hilos creados:" + threads.size());
	}
	
	private void increasePool(){
		
		int newSize = this.poolSize * multiplicador;
		
		while(threads.size() < newSize){
			EventThread hilo = new EventThread();
			threads.add(hilo);

		}
		
		this.poolSize = newSize;
		
		SmartHomeLogger.putMessage("Pool de Hilos ha aumentado a:" + threads.size());
	}
	
	
	public synchronized EventThread getThread(){
		
		if(threads.size() < 40){
			increasePool();
		}
		
		EventThread t = threads.get(0);
		threads.remove(0);
		return  t;
	}

}
