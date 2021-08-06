/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpfinaledat;

/**
 *
 * @author alanizgustavo
 */
public class Desafio {
    private int puntaje;
    private String nombre;
    private String tipo;
    

    public Desafio(int puntaje, String nombre, String tipo) {
        
        this.puntaje = puntaje;
        this.nombre = nombre;
        this.tipo = tipo;
        
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.puntaje;
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
        final Desafio other = (Desafio) obj;
        if (this.puntaje != other.puntaje) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Desafio{" + "puntaje=" + puntaje + ", nombre=" + nombre + ", tipo=" + tipo + '}';
    }
    
    
}
