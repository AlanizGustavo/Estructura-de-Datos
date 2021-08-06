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
class NodoHashDicc {
    private Object clave;
    private Object dato;
    private NodoHashDicc enlace;

    public NodoHashDicc(Object key,Object elem) {
        this.clave = key;
        this.dato=elem;
        this.enlace = null;
    }

    public Object getClave() {
        return clave;
    }
    
    public NodoHashDicc getEnlace() {
        return enlace;
    }
    
    public Object getDato() {
        return dato;
    }
    
    public void setClave(Object key) {
        this.clave = key;
    }

    public void setEnlace(NodoHashDicc enlace) {
        this.enlace = enlace;
    }
    
    public void setDato(Object elem) {
        this.dato = elem;
    }
}
