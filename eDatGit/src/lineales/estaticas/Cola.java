
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.estaticas;

/**
 *
 * @author Alaniz Gustavo
 */

//COLA ESTATICA
public class Cola {
    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO=10;
    
    //CONSTRUCTOR
    public Cola(){
        this.arreglo=new Object[this.TAMANIO];
        this.frente=0;
        this.fin=0;
    }
    
//MODIFICADORAS
    public boolean poner(Object elemento){
        boolean exito=false;
        if((this.fin+1)%this.TAMANIO!=this.frente){
            arreglo[this.fin]=elemento;
            this.fin=(this.fin+1)%this.TAMANIO;
            exito=true;
        }
        
        return exito;
    }
    
    public boolean sacar(){
        boolean exito=true;
        
        if(this.esVacia()){
            exito=false;
        }
        else{
            this.arreglo[this.frente]=null;
            this.frente=(this.frente+1)%this.TAMANIO;
        }
        return exito;
    }
    
//OBSERVADORAS    
    public Object obtenerFrente(){
        Object exito;
        if(!this.esVacia()){
            exito=arreglo[this.frente];
        }
        else{
            exito=null;
        }
        return exito;
    }
    
    public boolean esVacia(){
        return (this.frente==this.fin);
    }
    
//PROPIAS DEL TIPO
    public void vaciar(){
        int i;
        if(this.frente<this.fin){
            for(i=this.frente;i<this.fin;i++){
                this.arreglo[i]=null;   
            }
            
        }
        else{
            for(i=0;i<this.fin;i++){
                this.arreglo[i]=null;
            }
            for(i=this.frente;i<TAMANIO;i++){
                this.arreglo[i]=null;
            }
        }
        this.fin=0;
        this.frente=0;
    }
    
    public Cola clone(){
        Cola copia=new Cola();
        copia.frente=this.frente;
        copia.fin=this.fin;
        copia.arreglo=this.arreglo.clone();
        return copia;
    }
    
    
    public String toString(){
        int contador=this.frente;
        String texto="";
        if(!this.esVacia()){
            while(contador!=this.fin){
                texto=texto+this.arreglo[contador].toString();
                contador=(contador+1)%this.TAMANIO;
                if(contador!=this.fin){
                    texto+=",";
                }
            }
        }
        else{
            texto="COLA VACIA";
        }
        return "FRENTE: "+this.frente+"\nFIN: "+this.fin+"\nARREGLO: "+"["+texto+"]";
    }
}
