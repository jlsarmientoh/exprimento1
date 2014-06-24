package co.edu.uniandes.exprimentos.thread;

import co.edu.uniandes.exprimentos.pool.listener.PoolListener;

public abstract class BaseThread extends Thread {

	private PoolListener listener;
	private boolean available;
	
	@Override
	public synchronized void start() {
		super.start();
		
	}
	
	public void attachListener(PoolListener listener){
		this.listener = listener;
	}
}
