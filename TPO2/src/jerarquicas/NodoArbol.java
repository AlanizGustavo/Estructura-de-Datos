/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;

/**
 *
 * @author Alaniz Gustavo     FAI-822
 * @author Bustamante Leonel  FAI-2355
 * @author Recalde Franco     FAI-2757
 */
public class NodoArbol {
    private Object elemento;
    private NodoArbol izquierdo;
    private NodoArbol derecho;
    
    //CONSTRUCTOR
    public NodoArbol(Object elem, NodoArbol izq, NodoArbol der){
        this.elemento=elem;
        this.izquierdo=izq;
        this.derecho=der;
    }
    
    public NodoArbol(Object elem){
        this.elemento=elem;
        this.izquierdo=null;
        this.derecho=null;
    }
    
    //MODIFICADORES
    
    public void setElemento(Object elem){
        this.elemento=elem;
    }
    
    public void setIzquierdo(NodoArbol izq){
        this.izquierdo=izq;
    }
    
    public void setDerecho(NodoArbol der){
        this.derecho=der;
    }
    
    //OBSERVADORES
    
    public Object getElemento(){
        return elemento;
    }
    
    public NodoArbol getIzquierdo(){
        return izquierdo;
    }
    
    public NodoArbol getDerecho(){
        return derecho;
    }
}
