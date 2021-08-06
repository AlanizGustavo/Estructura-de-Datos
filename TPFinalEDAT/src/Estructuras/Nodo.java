/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author alanizgustavo
 */
class Nodo {
    private Object elemento;
    private Nodo enlace;
    
    //CONSTRUCTOR
    public Nodo(Object elemento, Nodo enlace){
        this.elemento=elemento;
        this.enlace=enlace;
    }
    
    //MODIFICADORAS
    public void setElem(Object elem){
        this.elemento=elem;
    }
    
    public void setEnlace(Nodo enlace){
        this.enlace=enlace;
    }
    
    //OBSERVADORAS
    
    public Object getElem(){
        return elemento;
    }
    
    public Nodo getEnlace(){
        return enlace;
    }
}
