/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamicas;

/**
 *
 * @author Alaniz Gustavo     FAI-822
 * @author Bustamante Leonel  FAI-2355
 * @author Recalde Franco     FAI-2757
 */
public class Cola {
    private Nodo frente;
    private Nodo fin;
    
    //CONSTRUCTOR
    
    public void Cola(){
        this.frente=null;
        this.fin=null;
    }
    
    //MODIFICADORAS
    
    public boolean poner(Object elemento){
        //MODULO QUE AGREGA UN ELEMENTO A LA COLA
        Nodo nuevo=new Nodo(elemento,null);
        if(this.esVacia()){                                                     //SI ESTA VACIA.... AGREGA EL NODO Y
            this.frente=nuevo;                                                  //SETEA EL FRENTE Y EL FIN AL NUEVO NODO
            this.fin=nuevo;
        }
        else{
            this.fin.setEnlace(nuevo);                                          //EN CASO DE TENER ELEMENTOS SETEA ENLACE AL 
            this.fin=nuevo;                                                     //NUEVO NODO
        }
        return true;
    }
    
    public boolean sacar(){
        boolean exito=true;
        if(this.frente==null){
            //la cola esta vacia, reporta error.
            exito=false;
        }
        else{
            //al menos hay un elemento:
            //quita el primer elemento y actualiza
            this.frente=this.frente.getEnlace();
            if(this.frente==null){
                this.fin=null;
            }
        }
        return exito;
    }
    
    //OBSERVADORAS
    
    public Object obtenerFrente(){
        //MODULO QUE DEVUELVE EL ELEMENTO QUE SE ENCUENTRA EN EL NODO FRENTE
        Object elemento;
        if(!this.esVacia()){
            elemento=this.frente.getElem();                                     //SI TIENE ELEMENTOS LA COLA, DEVUELVE EL ELEMENTO
        }
        else{
            elemento=null;                                                      //SI ESTA VACIA DEVUELVE NULL
        }
        return elemento;
    }
    
    public boolean esVacia(){
        //NODO QUE VERIFICA SI LA COLA ESTA VACIA
        boolean exito=false;
        if(this.frente==null){
            exito=true;                                                         //SI LA COLA TIENE ELEMENTOS DEVUELVE TRUE
        }
        return exito;
    }
    
    public void vaciar(){
        //MODULO QUE VACIA LA COLA
        this.frente=null;
        this.fin=null;
    }
    
    
    public Cola clone(){
        /*MODULO QUE CLONA UNA COLA*/
        Cola copia=new Cola();                                                  //CREO UNA COLA NUEVA
        
        Nodo aux2=new Nodo(this.frente.getElem(),null);
        Nodo aux1=this.frente.getEnlace();                                      //CREO UN PUNTERO AL FRENTE DE LA COLA A CLONAR
        copia.frente=aux2;                                                      //CREO UN NUEVO NODO CON EL ELEMENTO DEL PRIMER NODO DE LA COLA A CLONAR Y SE LO ASIGNO AL FRENTE DE LA COLA CREADA.
                                                                                //CREO UN PUNTERO AL FRENTE DEL NODO CREADO
        while(aux1!=null){                                                      
            aux2.setEnlace(new Nodo(aux1.getElem(),null));                      //ENLACE DEL NODO CREADO A UN NUEVO NODO QUE SE CREA EN ESTA LINEA
            
            aux2=aux2.getEnlace();                                              //MUEVO EL PUNTERO AL SIGUIENTE NODO DE LA COLA CREADA
            aux1=aux1.getEnlace();                                              //MUEVO EL PUNTERO DE LA COLA ORIGINAL
            
        }
        copia.fin=aux2;
        return copia;
    }
    
    public String toString(){
        String texto="",salida;
        Nodo aux=this.frente;
        if(!this.esVacia()){
            while(aux!=null){
                texto+=aux.getElem().toString();
                aux=aux.getEnlace();
                if(aux!=null){
                    texto+=" , ";
                }
            }
        }
        else{
            texto="COLA VACIA";
        }
        if(this.frente!=null){
            salida= "FRENTE: "+this.frente.getElem()+"\nFIN: "+this.fin.getElem()+"\nCOLA: "+"["+texto+"]";
        }
        else{
            salida= "FRENTE: "+this.frente+"\nFIN: "+this.fin+"\nCOLA: "+"["+texto+"]";
        }
        return salida;
        
    }    
}
