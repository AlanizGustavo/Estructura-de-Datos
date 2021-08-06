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
public class Habitacion {
    
    private int codigo;
    private String nombre;
    private int planta;
    private int dimension;
    private boolean salida;
    private int cantEquipos;

    public Habitacion(int codigo, String nombre, int planta, int dimension, boolean salida) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.planta = planta;
        this.dimension = dimension;
        this.salida = salida;
        this.cantEquipos=0;
    }

    public int getCodigo() {
        return codigo;
    }

    /*public void setCodigo(int codigo) {
        this.codigo = codigo;
    }*/

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPlanta() {
        return planta;
    }

    public void setPlanta(int planta) {
        this.planta = planta;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public boolean isSalida() {
        return salida;
    }

    public void setSalida(boolean salida) {
        this.salida = salida;
    }

    public int getCantEquipos() {
        return cantEquipos;
    }

    public void setCantEquipos(int cantEquipos) {
        this.cantEquipos = cantEquipos;
    }
    
    

    @Override
    public String toString() {
        return "Habitacion{" + "codigo=" + codigo + ", nombre=" + nombre + ", planta=" + planta + ", dimension=" + dimension + ", salida=" + salida + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Habitacion other = (Habitacion) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    
    
    
    
    
}
