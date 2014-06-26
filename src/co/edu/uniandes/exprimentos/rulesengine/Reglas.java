package co.edu.uniandes.exprimentos.rulesengine;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

import co.edu.uniandes.exprimentos.event.SensorEvent;
import co.edu.uniandes.exprimentos.output.SmartHomeLogger;

public class Reglas {

	public static FuenteReglas REGLAS[][] = new FuenteReglas[100][50]; // 100 CASAS Y 50 SENSORES DEFINIDOS 

	static{
		llenarReglas();
	}
/*
 * METODO PARA SIMULAR LAS REGLAS CON VALORES ALEATORIOS Y ALMACENARLOS EN UN ARRAY BIDIMENCIONAL
 * DONDE FILAS SERIAN LAS CASAS Y COLUMNAS LOS SENSORES (CASAS, SENSORES)
 * */
	public static  void llenarReglas(){
		String[] notif = {"LLamar a la policia","LLamar a los bomberos","Llamar a los duenos","Llamar a los vigilantes","Llamar al ejercito"};
		int casa = 100;
	    int sensor = 50;
	    Date fecha = new Date(System.currentTimeMillis());
	    SimpleDateFormat fechaActual = new SimpleDateFormat("hh");
	    
	    int hora;
	    
	    // Llenar matriz de reglas
	    for(int i=0; i<casa;i++){ 
			for(int j=0;j<sensor;j++){
				//GENERAR VALORES ALEATORIOS A LAS VARIABLES DE LAS REGLAS
				Random rn = new Random();
				int idRegla = 0;
			    int estadoRegla = 1;
			    int idx = new Random().nextInt(notif.length);
			    String accion = (notif[idx]); // SIMULAR ADICIONAR UNA NOTIFICACION ALEATORIA 
			    
			    FuenteReglas temp = new FuenteReglas();
			    int idRegla1 =  (int) Math.round((Math.random()*10)); // 10 REGLAS ALEATORIAS
			    temp.setCod(idRegla1); 							// Id Regla
			    temp.setEstado(estadoRegla); 					//Estado sensor 1
			    temp.setAccion(accion); 						// Accion a tomar
			    int horaAlarma =  (int) Math.round((Math.random()*24)); // SIMULAR ADICIONAR UNA HORA MILITAR PARA LA REGLA 
			    temp.setFechaInicial(horaAlarma); // HACER LA PRUEBA SOLO CON UNA HORA PROGRAMADA			
			    
				//datos aleatorios para la regla (idregla, estado, hora, accion)
				REGLAS[i][j] = temp; //LLenando la matriz con: (idRegla, Estado, accion, horaAlarma)
			}
	    } 
		
	}
/*
 * METODO QUE PROCESA EL OBJETO DE LA TRAMA PARA PODER VALIDAR Y NOTIFICAR
 * PARA EL EXPEIMENTO SE VALIDAN REGLAS POR HORAS, SI HORA > A
 */
	public void procesarReglas(SensorEvent tempTrama){
		
		try {
			int casaTrama = 0;
		  	int sensorTrama = 0;
		  	int cambioSensor = 0;
		  	int estadoSensor = 0;
		  	long tiempoInicial = 0;
		  	long tiempoFinal = 0;
		  	int hora;
		  	// VALOR TOMADOS DEL OBJETO PASADO CON LOS DATOS DE LA CASA, SENSORES Y VALORES Y FECHA 
		  	casaTrama = tempTrama.getHouse() - 1; // Fila (la casa)
		  	sensorTrama = tempTrama.getSensor() - 1; // Columna (El sensor)
		  	cambioSensor = tempTrama.getChanged(); // YA VIENE CON VALOR 1, ES DECIR CAMBIÒ EL SENSOR
		  	estadoSensor = tempTrama.getState(); // ESTADO 1 O 0
		  	tiempoInicial = tempTrama.getStartTime(); // OBTENER SOLO LA HORA
		  	String S = new SimpleDateFormat("hh").format(tiempoInicial); // OBTENIENDO SOLO LA HORA COMO EJEMPLO Y VALIDACION
		  	tiempoFinal = tempTrama.getFinishTime();
		  	//Leer la matriz Regla que se llenó desde un principio y en este punto (casaTrama, sensorTrama) par'ametros que vienen del objeto
			//verificar Regla: si cumple y luego almacenarla 
		    // se debe notificar
			if (REGLAS[casaTrama][sensorTrama].getEstado() == 1){
				int horaNueva = REGLAS[casaTrama][sensorTrama].getFechaInicial(); // Hora de matriz regla
				
				if (Integer.parseInt(S) > horaNueva){
					tempTrama.setFinishTime(System.currentTimeMillis());
					SmartHomeLogger.putMessage(tempTrama.toString() + "-" + REGLAS[casaTrama][sensorTrama].getAccion()); // ALMACENANDO LAS NOTICIFICACIONES PARA LUEGO SI SE DESEA IMPRIMIRLAS
				}else{
					tempTrama.setFinishTime(System.currentTimeMillis());
					SmartHomeLogger.putMessage(tempTrama.toString() + "-" + "No es alarma"); // ALMACENANDO LAS NOTICIFICACIONES PARA LUEGO SI SE DESEA IMPRIMIRLAS
				}
			}
		}catch(IndexOutOfBoundsException iob){
			tempTrama.setFinishTime(System.currentTimeMillis());
			SmartHomeLogger.putMessage(tempTrama.toString() + "-" + "Casa no existe:" + iob.getMessage());
		}
	}

}
