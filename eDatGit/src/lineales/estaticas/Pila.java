/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.estaticas;

/**
 *
 * @author alanizgustavo
 */

// PILA ESTATICA
public class Pila {
    private Object [] arreglo;
    private int tope;
    private static final int TAMANIO=10;
    
    //CONSTRUCTORES
    
    public Pila(){
        this.arreglo=new Object[TAMANIO];
        this.tope=-1;
    }
    
    //MODIFICADORAS
    
    //APILA UN NUEVO ELEMENTO EN LA PILA
    public boolean apilar(Object nuevoElemento){
        boolean exito;
        if(this.tope+1>=this.TAMANIO){
            //ERROR pila llena
            exito=false;
        }
        else{
            this.tope++;
            arreglo[tope]=nuevoElemento;
            exito=true;
        }
        return exito;
    }
    //DESAPILA UN ELEMENTO DE LA PILA
    public boolean desapilar(){
        boolean exito;
        if(this.tope>-1){
            this.arreglo[this.tope]=null;
            this.tope--;
            exito=true;
        }
        else{
            //ERROR pila vacia
            exito=false;
        }
        return exito;
    }
    
    //OBSERVADORAS
    //DEVUELVE EL ELEMENTO GUARDADO EN EL TOPE
    public Object obtenerTope(){
        Object exito;
        if(!this.esVacia()){
           exito=arreglo[this.tope]; 
        }
        else{
            exito=null;
        }
        return exito;
    }
    //MODULO QUE VERIFICA SI LA PILA ESTA VACIA O NO
    public boolean esVacia(){
        boolean exito;
        if(this.tope==-1){
            //esta vacia
            exito=true;
        }
        else{
            exito=false;
        }
        return exito;
    }
    
    //PROPIAS DEL TIPO
    //MODULO QUE VACIA LA PILA
    public void vaciar(){
        int i;
        for(i=0;i<=this.tope;i++){
            this.arreglo[i]=null;
        }
        this.tope=-1;
    }
    //MODULO QUE CLONA LA PILA
    public Pila clone(){
        Pila copia=new Pila();
        copia.tope=this.tope;
        copia.arreglo=this.arreglo.clone();
        return copia;
    }
    
    public String toString(){
        int i;
        String texto,texto1="";
        for(i=0;i<=this.tope;i++){
            if(texto1==""){
                texto1=texto1+this.arreglo[i].toString();
            }
            else{
                texto1=texto1+" , "+this.arreglo[i].toString();
            }
            
        }    
        return texto="TOPE: "+this.tope+"\nARREGLO: "+"["+texto1+"]";
    }
}
