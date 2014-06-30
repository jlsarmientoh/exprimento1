package co.edu.uniandes.exprimentos.service;

import co.edu.uniandes.exprimentos.event.SensorEvent;
import co.edu.uniandes.exprimentos.output.OutputThread;
import co.edu.uniandes.exprimentos.pool.ThreadPoolSingleton;
import co.edu.uniandes.exprimentos.thread.EventThread;

public class ProcesarSensorService implements SensorService {
	
	public ProcesarSensorService(){
		ThreadPoolSingleton.getInstance();
	}

	/**
	 * 
	 */
	@Override
	public boolean procesar(byte[] trama) {
		if(trama != null && trama.length > 0){
			long timeStamp = 0;
			int idCasa = 0;
			try{
				timeStamp = (((long) trama[0]) & 0xFF)
						+ ((((long) trama[1]) & 0xFF) << 8)
						+ ((((long) trama[2]) & 0xFF) << 16)
						+ ((((long) trama[3]) & 0xFF) << 24)
						+ ((((long) trama[4]) & 0xFF) << 32)
						+ ((((long) trama[5]) & 0xFF) << 40)
						+ ((((long) trama[6]) & 0xFF) << 48)
						+ ((((long) trama[7]) & 0xFF) << 56);
				
				idCasa = (((int) trama[8]) & 0xFF)
							+ ((((int) trama[9]) & 0xFF) << 8);
			
				for (int i = 10; i <= 20 ; i++) {
					
						for(int j = 1, k = 1; j <= 4; j++, k*=2){
							
							//if((trama[i] & k) == k){
								int cambioEstado = 0;
								if((trama[i] & k) == k){
									cambioEstado = 1;
								}
								int estado = ((trama[i] >> (j+3)) & 0x01);
								int sensor = (((i-10) * 4 ) +j );
								
								//Setear la data en el objeto SensorEvent
								SensorEvent evento = new SensorEvent();
								evento.setStartTime(timeStamp);
								evento.setHouse(idCasa);
								evento.setSensor(i);
								evento.setChanged(cambioEstado);
								evento.setState(estado);
								
								EventThread hilo = ThreadPoolSingleton.getInstance().getThread();
								hilo.setSensorEvent(evento);
								hilo.start();
							//}
						}
					
						// Lee la informaci—n de cada sensor
						/*int cambioEstado = trama[j];
						int estado = trama[j + 1];
						
						//Setear la data en el objeto SensorEvent
						SensorEvent evento = new SensorEvent();
						evento.setStartTime(timeStamp);
						evento.setHouse(idCasa);
						evento.setSensor(i);
						evento.setChanged(cambioEstado);
						evento.setState(estado);
						
						EventThread hilo = ThreadPoolSingleton.getInstance().getThread();
						hilo.setSensorEvent(evento);
						hilo.start();*/
						// El sensor cambio de estado. Hay que validar
						/*System.out.println("Consumed: timeStamp("
								+ timeStamp + ") idCasa(" + idCasa
								+ ") idSensor(" + i + ") Estado("
								+ trama[j + 1] + ")");*/
					
				}
			}catch(Exception e){
				System.out.println("Trama no procesada{casa:" + idCasa + "-timesstamp:" + timeStamp + "}{" + trama + "}{"+ e.getMessage() +"}");
			}
			
			return true;
		}
		return false;
	}

}
