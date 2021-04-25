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
public class Lista {
    private Nodo cabecera;
    private int longitud;
    
    //CONSTRUCTOR
    
    public void Lista(){
        cabecera=null;
        longitud=0;
    }
    
    //MODIFICADORES
//INSERTAR ---------------------------------------------------------------    
    public boolean insertar(Object nuevoElemento, int pos){
        //INSERTA EL ELEMENTO NUEVO EN LA POSICION POS
        //DETECTA Y REPORTA ERROR DE POSICION INVALIDA
        boolean exito=true;
        
        if(pos<1 || pos>this.longitud+1){
            exito=false;
        }
        else{
            if(pos==1){//CREA UN NUEVO NODO Y SE ENLAZA EN LA CABECERA
                this.cabecera=new Nodo(nuevoElemento,this.cabecera);
                this.longitud+=1;
            }
            else{
                //AVANZA HASTA EL ELEMENTO EN LA POSICION POS-1
                Nodo aux = this.cabecera;
                int i=1;
                while(i<pos-1){
                    aux=aux.getEnlace();
                    i++;
                }
                //CREA EL NODO Y LO ENLAZA
                Nodo nuevo=new Nodo(nuevoElemento,aux.getEnlace());
                aux.setEnlace(nuevo);
                this.longitud+=1;
            }
        }
        //NUNCA HAY ERROR DE LISTA LLENA, ENTONCES DEVUELVE TRUE
        return exito;
    }

//ELIMINAR ---------------------------------------------------------------
    
    public boolean eliminar(int pos){
        //MODULO QUE ELIMINA UN NODO EN UNA POSICION DADA
        boolean exito=true;
        if(pos<1 || pos>this.longitud){
            exito=false;                //SI LA POSICION A EVALUAR NO SE ENCUENTRA DENTRO DE LOS 
        }                               //LIMITES DE LA LISTA RETORNA FALSE
        else{
            if(pos==1){
                this.cabecera=this.cabecera.getEnlace();        //CASO ESPECIAL: ELIMINAR EN POS 1
                this.longitud--;                                //SE ASIGNA UNA NUEVA CABECERA Y SE DESCUENTA EN UNA UNIDAD LA LONGITUD
            }
            else{
                Nodo aux=this.cabecera;                 //EN LAS DEMAS POSICIONES A ELIMINAR. SE ENCUENTRA LA POSICION ANTERIOR
                int i=1;                                //Y SE SETEA UN ENLACE DE ESE NODO AL SIGUIENTE DEL QUE SE QUIERE ELIMINAR
                while(i<pos-1){
                    aux=aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
                this.longitud--;
            }
        }
        return exito;
    }
    
//VACIAR ---------------------------------------------------------------    
    public void vaciar(){
        //METODO QUE VACIA LA LISTA
        this.cabecera=null;
        this.longitud=0;
    }
    
    //OBSERVADORAS

//RECUPERAR ---------------------------------------------------------------
    
    public Object recuperar(int pos){
        //METODO QUE DEVUELVE EL ELEMENTO QUE SE ENCUENTRA EN UNA POSICION DADA
        Object elemento;
        if(pos<1 || pos>this.longitud){         //SI NO SE DA UNA POSICION CORRECTA DEVUELVE NULL
            elemento=null;
        }
        else{
            int i=1;
            Nodo aux=this.cabecera;
            while(i<pos){
                aux=aux.getEnlace();
                i++;
            }
            elemento=aux.getElem();
        }
        return elemento;
    }

//LOCALIZAR ---------------------------------------------------------------
    
    public int localizar(Object buscado){
        //METODO QUE DEVUELVE LA POSICION DE UN ELEMENTO QUE SE BUSCA
        int pos=-1, contador=1;
        boolean encontrado=false;
        if(!this.esVacia()){                    //SI NO ES VACIA 
            Nodo aux=this.cabecera;
            while(aux!=null && encontrado==false){
                if(aux.getElem().equals(buscado)){
                    encontrado=true;            //SI SE ENCUENTRA CAMBIA LA BANDERA A TRUE
                    pos=contador;
                }
                else{
                    aux=aux.getEnlace();
                    contador+=1;
                }
            }   
        }
        return pos;
    }
//LONGITUD --------------------------------------------------------------- 
    
    public int longitud(){
        //METODO QUE DEVUELVE LA LONGITUD DE LA LISTA
        return this.longitud;
    }
    
//ES VACIA ---------------------------------------------------------------    
    
    public boolean esVacia(){
        //METODO QUE VERIFICA SI LA LISTA ES VACIA O NO
        boolean exito=false;
        if(this.longitud==0){
            exito=true;             //EN CASO DE QUE NO TENGA ALGUN ELEMENTO DEVUEVE TRUE
        }
        return exito;
    }

//CLONAR ---------------------------------------------------------------
    
    public Lista clone(){
        //METODO QUE CLONA LA LISTA
        Lista clon=new Lista();                                                  //CREO UNA LISTA NUEVA
        if(!this.esVacia()){
            Nodo aux=this.cabecera.getEnlace();
            clon.cabecera=new Nodo(this.cabecera.getElem(),null);
        
            Nodo aux2=clon.cabecera;
                                                              //CREO UN NUEVO NODO CON EL ELEMENTO DEL PRIMER NODO DE LA LISTA A CLONAR Y SE LO ASIGNO AL FRENTE DE LA LISTA CREADA.
                                                                                //CREO UN PUNTERO AL FRENTE DEL NODO CREADO
            while(aux!=null){                                                      
                aux2.setEnlace(new Nodo(aux.getElem(),null));                      //ENLACE DEL NODO CREADO A UN NUEVO NODO QUE SE CREA EN ESTA LINEA
            
                aux2=aux2.getEnlace();                                              //MUEVO EL PUNTERO AL SIGUIENTE NODO DE LA LISTA CREADA
                aux=aux.getEnlace();                                              //MUEVO EL PUNTERO DE LA COLA ORIGINAL
            
            }
            clon.longitud=this.longitud;
        }    
            return clon;
    }

//INVERTIR ---------------------------------------------------------------
    
    public void invertir(){
        //METODO QUE INVIERTE LA LISTA
          Nodo ultimo=inviertoLista(this.cabecera);
          ultimo.setEnlace(null);  
    }
    
    private Nodo inviertoLista(Nodo aux){
        if(aux.getEnlace()==null){
            this.cabecera=aux;
        }
        else{
            Nodo aux2=inviertoLista(aux.getEnlace());
            aux2.setEnlace(aux);
        }
        return aux;
    }
    
    
    public void eliminarApariciones(Object x){
        //METODO QUE ELIMINA LAS APARICIONES DE UN ELEMENTO DADO
        while(this.cabecera!=null && this.cabecera.getElem().equals(x)){
            this.cabecera=this.cabecera.getEnlace();
            this.longitud--;
        }
        
        Nodo aux=this.cabecera;
        
        while(aux!=null && aux.getEnlace()!=null ){
            if(aux.getEnlace().getElem().equals(x)){
                aux.setEnlace(aux.getEnlace().getEnlace());
                this.longitud--;
            }
            else{
                aux=aux.getEnlace();
            }
        } 
    } 

//TO STRING ---------------------------------------------------------------
    
    public String toString(){
        String texto="",salida;
        Nodo aux=this.cabecera;
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
            texto="LISTA VACIA";
        }
        if(this.cabecera!=null){
            salida= "CABECERA: "+this.cabecera.getElem()+"\nLISTA: "+"["+texto+"]"+"\nLONGITUD: "+this.longitud;
        }
        else{
            salida= "CABECERA: "+this.cabecera+"\nLISTA: "+"["+texto+"]"+"\nLONGITUD: "+this.longitud;
        }
        return salida;
    }
}
