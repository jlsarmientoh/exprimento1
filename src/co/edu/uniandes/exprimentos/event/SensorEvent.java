package co.edu.uniandes.exprimentos.event;

import java.util.Date;

public class SensorEvent {

	private long startTime;
	private long finishTime;
	private int house;
	private int sensor;
	private int changed;
	private int state;
	
	
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(long finishTime) {
		this.finishTime = finishTime;
	}
	public int getHouse() {
		return house;
	}
	public void setHouse(int house) {
		this.house = house;
	}
	public int getSensor() {
		return sensor;
	}
	public void setSensor(int sensor) {
		this.sensor = sensor;
	}
	public int getChanged() {
		return changed;
	}
	public void setChanged(int changed) {
		this.changed = changed;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public String toString(){
		/*return String.format("idCasa: %s-sensor: %s-Tiempo inicio: %s-Tiempo fin: %s-diferrencia: %s", 
				this.house, this.sensor, new Date(this.startTime), new Date(this.finishTime), (this.finishTime - this.startTime));*/
		return String.format("%s-%s-%s-%s-%s", 
				this.house, this.sensor, new Date(this.startTime), new Date(this.finishTime), (this.finishTime - this.startTime));
	}
	
}
