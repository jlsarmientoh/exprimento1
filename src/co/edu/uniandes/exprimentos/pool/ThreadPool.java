package co.edu.uniandes.exprimentos.pool;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.exprimentos.pool.listener.PoolListener;
import co.edu.uniandes.exprimentos.thread.EventThread;

public class ThreadPool implements PoolListener{
	
	private final int multiplicador = 2;
	private final double cotaMinima = 0.2;
	private List<EventThread> threads;
	private List<Integer> availables;
	
	public ThreadPool(){
		threads = new ArrayList<EventThread>();
		availables = new ArrayList<Integer>();
		
		for(int i = 0; i < 200; i++){
			EventThread hilo = new EventThread(i);
			hilo.attachListener(this);
			threads.add(hilo);
			availables.add(i);
		}
	}
	
	private void increasePool(){
		int previousSize = threads.size();
		int newSize = previousSize * multiplicador;
		
		for(int i = previousSize; i < newSize; i++){
			EventThread hilo = new EventThread(i);
			hilo.attachListener(this);
			threads.add(hilo);
			availables.add(i);
		}
	}
	
	
	public synchronized EventThread getThread(){
		
		double hilosRestantes = availables.size() / threads.size();
		
		if(hilosRestantes < cotaMinima){
			increasePool();
		}
		
		return threads.get(availables.get(0));
	}

	@Override
	public synchronized void onThreadStart(int index) {
		this.availables.remove(index);
		this.threads.remove(index);
	}

	@Override
	public synchronized void onThreadFinish(int index) {
		//this.availables.add(index);
		
	}

	@Override
	public synchronized void onThreadCancel(int index) {
		//this.availables.add(index);
	}

}
