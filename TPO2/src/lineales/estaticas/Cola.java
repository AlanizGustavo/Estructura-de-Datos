
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.estaticas;

/**
 *
 * @author Alaniz Gustavo     FAI-822
 * @author Bustamante Leonel  FAI-2355
 * @author Recalde Franco     FAI-2757
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
    
//PONER ---------------------------------------------------------------    
    public boolean poner(Object elemento){
        //METODO QUE AGREGA UN ELEMENTO A LA COLA
        boolean exito=false;                //SI NO LA AGREGA POR COLA LLENA DEVUELVE FALSE
        if((this.fin+1)%this.TAMANIO!=this.frente){
            arreglo[this.fin]=elemento;
            this.fin=(this.fin+1)%this.TAMANIO;
            exito=true;             //EN CASO DE QUE AGREGUE CON EXITO RETORNA TRUE
        }
        
        return exito;
    }
 
//SACAR ---------------------------------------------------------------    
    
    public boolean sacar(){
        //METODO QUE QUITA UN ELEMENTO DE LA COLA
        boolean exito=true;         //EN CASO DE TENER EXITO DEVUELVE TRUE
        
        if(this.esVacia()){         //EN CASO DE COLA VACIA RETORNA FALSE
            exito=false;
        }
        else{
            this.arreglo[this.frente]=null;
            this.frente=(this.frente+1)%this.TAMANIO;
        }
        return exito;
    }
    
//OBSERVADORAS    
    
//OBTENER FRENTE ---------------------------------------------------------------    
    
    public Object obtenerFrente(){
        //METODO QUE DEVUELVE EL ELEMENTO QUE SE ENCUENTRA EN EL FRENTE DE LA COLA
        Object exito;
        if(!this.esVacia()){
            exito=arreglo[this.frente];     //EN CASO DE TENER ELEMENTOS DEVUELVE EL ELEMENTO EN EL FRENTE
        }
        else{
            exito=null;                 //EN CASO DE COLA VACIA DEVUELVE NULL
        }
        return exito;
    }

//ES VACIA ---------------------------------------------------------------
    
    public boolean esVacia(){
        //METODO QUE VERIFICA SI LA COLA ES VACIA O NO
        boolean exito=false;
        if(this.frente==this.fin){
            exito=true;     //EN CASO DE SER UNA COLA VACIA DEVUELVE TRUE
        }
        return exito;
    }
    
//PROPIAS DEL TIPO
    
//VACIAR ---------------------------------------------------------------    
    
    public void vaciar(){
        //METODO QUE VACIA LA COLA
        //AL SER ESTATICA LA COLA, DEBE PONER EN NULL EN TODAS LAS POSICIONES DEL ARREGLO DONDE POSEE ELEMENTOS
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
    
//CLONAR ---------------------------------------------------------------    
    
    public Cola clone(){
        //METODO QUE CLONA LA COLA
        Cola copia=new Cola();
        copia.frente=this.frente;
        copia.fin=this.fin;
        copia.arreglo=this.arreglo.clone();
        return copia;
    }
    

//TO STRING ---------------------------------------------------------------
    
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
