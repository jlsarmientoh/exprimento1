package co.edu.uniandes.exprimentos.service;

import co.edu.uniandes.exprimentos.event.SensorEvent;
import co.edu.uniandes.exprimentos.pool.ThreadPoolSingleton;
import co.edu.uniandes.exprimentos.thread.EventThread;

public class ProcesarSensorService implements SensorService {

	/**
	 * 
	 */
	@Override
	public boolean procesar(byte[] trama) {
		if(trama != null && trama.length > 0){
			//Esta parte puede cambiar
			for(byte data : trama){
				SensorEvent evento = new SensorEvent();
				//Setear la data en el objeto SensorEvent
				EventThread hilo = ThreadPoolSingleton.getInstance().getThread();
				hilo.setSensorEvent(evento);
				hilo.start();
			}
			return true;
		}
		return false;
	}

}
