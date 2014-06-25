package co.edu.uniandes.exprimentos.pool;

public class ThreadPoolSingleton {
	
	private volatile static ThreadPool pool;
	
	public static synchronized ThreadPool getInstance(){
		if(pool == null)
			pool = new ThreadPool();
		
		return pool;
	}

}
