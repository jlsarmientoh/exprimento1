package co.edu.uniandes.exprimentos.rulesengine;

import java.sql.Date;
public class FuenteReglas {
    private int idRegla; 
    private int estadoRegla;
    private String nombreSensor, accion;
    private int fechaI;
    private int fechaF;
 
    public int getCod() {
        return idRegla;
    }
 
    public void setCod(int cod) {
        this.idRegla = cod;
    }
 
    public int getEstado() {
        return estadoRegla;
    }
 
    public void setEstado(int estado) {
        this.estadoRegla = estado;
    }
    
    public int getFechaInicial() {
        return fechaI;
    }
    public void setFechaInicial(int fecha) {
        this.fechaI = fecha;
    }
    public int getFechaFinal() {
        return fechaF;
    }
    public void setFechaFinal(int fecha) {
        this.fechaF = fechaF;
    } 
    
    
    public String getNombre() {
        return nombreSensor;
    }
    
    public void setNombre(String nombre) {
        this.nombreSensor = nombre;
    }
    
    public String getAccion() {
        return accion;
    }
    
    public void setAccion(String accion) {
        this.accion = accion;
    }
  
}