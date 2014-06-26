package co.edu.uniandes.exprimentos.pool;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.exprimentos.pool.listener.PoolListener;
import co.edu.uniandes.exprimentos.thread.EventThread;

public class ThreadPool implements PoolListener{
	
	private final int multiplicador = 2;
	private final double cotaMinima = 0.2;
	private int poolSize = 200;
	private List<EventThread> threads;
	private List<Integer> availables;
	
	public ThreadPool(){
		threads = new ArrayList<EventThread>();
		availables = new ArrayList<Integer>();
		
		for(int i = 0; i < this.poolSize; i++){
			EventThread hilo = new EventThread(i);
			hilo.attachListener(this);
			threads.add(hilo);
			availables.add(i);
		}
		
		System.out.println("Hilos creados:" + threads.size());
	}
	
	private void increasePool(){
		
		int newSize = this.poolSize * multiplicador;
		
		//for(int i = previousSize; i < newSize; i++){
		int i = 0;
		while(threads.size() < newSize){
			EventThread hilo = new EventThread(i);
			hilo.attachListener(this);
			threads.add(hilo);
			availables.add(i);
			i++;
		}
		
		this.poolSize = newSize;
		
		System.out.println("Hilos creados:" + newSize);
	}
	
	
	public synchronized EventThread getThread(){
		
		if(threads.size() < 40){
			increasePool();
		}
		
		/*double hilosRestantes = availables.size() / threads.size();
		
		if(hilosRestantes < cotaMinima){
			increasePool();
		}*/
		
		EventThread t = threads.get(0);
		threads.remove(0);
		return  t;
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
