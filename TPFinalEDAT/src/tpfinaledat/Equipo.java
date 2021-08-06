/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpfinaledat;

import java.util.Objects;

/**
 *
 * @author alanizgustavo
 */
public class Equipo {
    private String nombre;
    private int puntajeSalida;
    private int puntajeAcumuladoTotal;
    private int habitacionActual;
    private int puntajeEnHabitacion;
    

    public Equipo(String nombre, int puntajeSalida, int puntajeAcumuladoTotal, int habitacionActual, int puntajeEnHabitacion) {
        this.nombre = nombre;
        this.puntajeSalida = puntajeSalida;
        this.puntajeAcumuladoTotal = puntajeAcumuladoTotal;
        this.habitacionActual = habitacionActual;
        this.puntajeEnHabitacion = puntajeEnHabitacion;
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntajeSalida() {
        return puntajeSalida;
    }

    public void setPuntajeSalida(int puntajeSalida) {
        this.puntajeSalida = puntajeSalida;
    }

    public int getPuntajeAcumuladoTotal() {
        return puntajeAcumuladoTotal;
    }

    public void setPuntajeAcumuladoTotal(int puntajeAcumuladoTotal) {
        this.puntajeAcumuladoTotal = puntajeAcumuladoTotal;
    }

    public int getHabitacionActual() {
        return habitacionActual;
    }

    public void setHabitacionActual(int habitacionActual) {
        this.habitacionActual = habitacionActual;
    }

    public int getPuntajeEnHabitacion() {
        return puntajeEnHabitacion;
    }

    public void setPuntajeEnHabitacion(int puntajeEnHabitacion) {
        this.puntajeEnHabitacion = puntajeEnHabitacion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipo other = (Equipo) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Equipo{" + "nombre=" + nombre + ", puntajeSalida=" + puntajeSalida + ", puntajeAcumuladoTotal=" + puntajeAcumuladoTotal + ", habitacionActual=" + habitacionActual + ", puntajeEnHabitacion=" + puntajeEnHabitacion + '}';
    }
    
}
