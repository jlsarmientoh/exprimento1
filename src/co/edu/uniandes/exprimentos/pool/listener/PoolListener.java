package co.edu.uniandes.exprimentos.pool.listener;

public interface PoolListener {
	
	void onThreadStart(int index);
	
	void onThreadFinish(int index);
	
	void onThreadCancel(int index);

}
