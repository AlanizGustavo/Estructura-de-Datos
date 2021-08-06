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
class NodoAVLDicc {
    private Comparable clave;
    private Object dato;
    private int altura;
    private NodoAVLDicc izquierdo;
    private NodoAVLDicc derecho;

    //CONSTRUCTOR
    public NodoAVLDicc(Comparable key,Object elem, NodoAVLDicc izq, NodoAVLDicc der) {
        this.clave = key;
        this.dato = elem;
        this.izquierdo = izq;
        this.derecho = der;
        this.altura = 0;
    }

    public NodoAVLDicc(Comparable key,Object elem) {
        this.clave = key;
        this.dato=elem;
        this.izquierdo = null;
        this.derecho = null;
    }

    //MODIFICADORES
    
    public void setDato(Object dato) {
        this.dato = dato;
    }
    
    public void setIzquierdo(NodoAVLDicc izquierdo) {
        this.izquierdo = izquierdo;
    }
    
    public void setDerecho(NodoAVLDicc derecho) {
        this.derecho = derecho;
    }
    
    public void setClave(Comparable key){
        this.clave=key;
    }
    
    //OBSERVADORES

    public Comparable getClave() {
        return clave;
    }

    public Object getDato() {
        return dato;
    }

    public int getAltura() {
        return altura;
    }

    public NodoAVLDicc getIzquierdo() {
        return izquierdo;
    }

    public NodoAVLDicc getDerecho() {
        return derecho;
    }


    public void recalcularAltura() {
        if (this.getIzquierdo() != null && this.getDerecho() != null) {
            this.altura = (Math.max(this.getIzquierdo().getAltura(), this.getDerecho().getAltura()) + 1);
        } else {
            if (this.getIzquierdo() != null && this.getDerecho() == null) {
                this.altura = (Math.max(this.getIzquierdo().getAltura(), -1) + 1);
            } else {
                if (this.getIzquierdo() == null && this.getDerecho() != null) {
                    this.altura = (Math.max(-1, this.getDerecho().getAltura()) + 1);
                } else {
                    if (this.getIzquierdo() == null && this.getDerecho() == null) {
                        this.altura = 0;
                    }
                }
            }
        }

    }
}
