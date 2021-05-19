/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;

/**
 *
 * @author alanizgustavo
 */
public class NodoGen {
    private Object elemento;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho;
    
    //CONSTRUCTOR
    
    public NodoGen(Object elem, NodoGen hI,NodoGen herD){
        this.elemento=elem;
        this.hijoIzquierdo=hI;
        this.hermanoDerecho=herD;        
    }
    
    //MODIFICADORES
    
    public Object getElem(){
        return this.elemento;
    }
    
    public NodoGen getHijoIzquierdo(){
        return this.hijoIzquierdo;
    }
    
    public NodoGen getHermanoDerecho(){
        return this.hermanoDerecho;
    }
    
    //VISUALIZADORES
    
    public void setElem(Object elem){
        this.elemento=elem;
    }
    
    public void setHijoIzquierdo(NodoGen nodo){
        this.hijoIzquierdo=nodo;
    }
    
    public void setHermanoDerecho(NodoGen nodo){
        this.hermanoDerecho=nodo;
    }
}
