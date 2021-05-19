/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamicas;

/**
 *
 * @author alanizgustavo
 */

//PILA DINAMICA
public class Pila {
    
    private Nodo tope;
    
    //CONSTRUCTOR
    public Pila(){
        this.tope=null;
    }
    
    //MODIFICADORAS
    //APILA UN NUEVO ELEMENTO
    public boolean apilar(Object elemNuevo){
        Nodo nuevo= new Nodo(elemNuevo, this.tope);
        this.tope=nuevo;
        return true;
    }
    //DESAPILA UN ELEMENTO
    public boolean desapilar(){
        boolean exito;
        if(this.esVacia()==false){  //VERIFICA QUE LA PILA NO SEA VACIA
            this.tope=this.tope.getEnlace();
            exito=true;
        }
        else{
            exito=false;
        }
        return exito;
    }
    
    //OBSERVADORAS
    //DEVUELVE EL ELEMENTO EN EL TOPE
    public Object obtenerTope(){
        Object exito;
        if(this.esVacia()==false){
            exito=this.tope.getElem();
        }
        else{
            exito=null;
        }
        return exito;
    }
    //MODULO QUE VERIFICA SI LA PILA ESTA VACIA O NO
    public boolean esVacia(){
        boolean exito;
        if(this.tope==null){
            exito=true;
        }
        else{
            exito=false;
        }
        return exito;
    }
    //MODULO QUE VACIA LA PILA
    public void vaciar(){
        this.tope=null;
    }
    //METODO QUE CLONA LA PILA DE FORMA ITERATIVA
    public Pila clone2(){
        Pila clone=new Pila();                                  //CREO UNA NUEVA PILA DONDE CLONAREMOS LOS NODOS
        if(!this.esVacia()){
            Nodo copia = new Nodo(this.tope.getElem(),null); // CREO NODO NUEVO CON EL ELEMENTO DEL TOPE
            clone.tope=copia;                                //ASIGO AL NODO COPIA COMO TOPE DE LA PILA NUEVA CREADA
            Nodo aux1=this.tope.getEnlace();
            Nodo aux2=copia;
            while(aux1!=null){
                Nodo nuevo=new Nodo(aux1.getElem(),null);  //CREO NODO NUEVO PARA LUEGO ENLAZARLO AL NODO ANTERIOR
                aux2.setEnlace(nuevo);
                aux2=aux2.getEnlace();                      //PASO AL SIGUIENTE NODO DE LA PILA COPIADA
                aux1=aux1.getEnlace();                      //PASO AL SIGUIENTE NODO DE LA PILA ORIGINAL
            }
        }
        return clone;
    }
    //METODO QUE CLONA DE FORMA RECURSIVA
    public Pila clone(){
        Pila clone=new Pila();  
        clone.tope=nodo(this.tope);
        return clone;
    }
    
    private Nodo nodo(Nodo aux){
        Nodo nuevo=null;
        if(aux!=null){
            nuevo=new Nodo(aux.getElem(),nodo(aux.getEnlace()));
        }
        return nuevo;
    }
    
    public String toString(){
        String s="";
        
        if(this.tope==null){
            s="Pila vacia";
        }
        else{
            Nodo aux=this.tope;
            s="[";
            while(aux!=null){
                s+=aux.getElem().toString();
                aux=aux.getEnlace();
                if(aux!=null){
                    s+=",";
                }
            }
            s+="]";
        }
        return s;
    }
    
}
