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
public class Lista {
    private Nodo cabecera;
    private int longitud;
    
    //CONSTRUCTOR
    
    public void Lista(){
        cabecera=null;
        longitud=0;
    }
    
    //MODIFICADORES
    
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
    
    public boolean eliminar(int pos){
        boolean exito=true;
        if(pos<1 || pos>this.longitud){
            exito=false;
        }
        else{
            if(pos==1){
                this.cabecera=this.cabecera.getEnlace();
                this.longitud--;
            }
            else{
                Nodo aux=this.cabecera;
                int i=1;
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
    public void vaciar(){
        this.cabecera=null;
        this.longitud=0;
    }
    
    //OBSERVADORAS
    
    public Object recuperar(int pos){
        Object elemento;
        if(pos<1 || pos>this.longitud){
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
    
    public int localizar(Object buscado){
        int pos=-1, contador=1;
        boolean encontrado=false;
        if(!this.esVacia()){
            Nodo aux=this.cabecera;
            while(aux!=null && !encontrado){
                if(aux.getElem().equals(buscado)){
                    encontrado=true;
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
    
    public int longitud(){
        return this.longitud;
    }
    
    public boolean esVacia(){
        boolean exito=false;
        if(this.longitud==0){
            exito=true;
        }
        return exito;
    }
    
    public Lista clone(){
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
    
    public void invertir(){
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
    
    public Lista obtenerMultiplos(int num){
        Lista lis=new Lista();
        if(this.longitud!=0){
            int pos=1,cont=0;
            Nodo aux= this.cabecera;
            Nodo aux2=null;
            while(aux!=null){
                if(pos%num==0){
                    Nodo nuevo=new Nodo(aux.getElem(),null);
                    if(lis.cabecera==null){
                        lis.cabecera=nuevo;
                        aux2=lis.cabecera;
                        cont++;
                        pos++;
                        aux=aux.getEnlace();
                    }
                    else{
                        aux2.setEnlace(nuevo);
                        pos++;
                        aux2=aux2.getEnlace();
                        aux=aux.getEnlace();
                        cont++;
                    }
                }
                else{
                    pos++;
                    aux=aux.getEnlace();
                }
            }
            lis.longitud=cont;
        }
        return lis;
    }
    
    
    public void insertarAnterior(Object a, Object b){
        Nodo aux=null;
        if(!this.esVacia()){
           
            if(this.cabecera.equals(a)){
                Nodo nuevo=new Nodo(b,null);
                nuevo.setEnlace(this.cabecera.getEnlace());
                this.cabecera.setEnlace(nuevo);
              
                Nodo nuevo2=new Nodo(b,null);
                nuevo2.setEnlace(this.cabecera);
                this.cabecera=nuevo2;
                aux=nuevo;
                while(aux!=null && aux.getEnlace().getElem().equals(a)){
                    Nodo nuevo3=new Nodo(b,null);
                    nuevo3.setEnlace(aux);
                    aux.setEnlace(nuevo3);
                    
                    aux=aux.getEnlace();
                }
            }    
            else{
                while(aux!=null && aux.getEnlace().getElem().equals(a)){
                    Nodo nuevo4=new Nodo(b,aux.getEnlace());
                    aux.setEnlace(nuevo4);
                    
                    aux=aux.getEnlace().getEnlace();
                }
            }
            
       
        }
    }

    public void insertarAnterior2(Object a, Object b){

        Nodo aux = this.cabecera;
        int pos = 1;
        while(aux != null){
            if(aux.getElem().equals(a) && pos == 1){
                Nodo nuevo = new Nodo(b,aux);
                this.cabecera = nuevo;
                Nodo nodoPosterior = new Nodo(b, aux.getEnlace());
                aux.setEnlace(nodoPosterior);
            }else{
                if(aux.getEnlace()!=null && aux.getEnlace().getElem().equals(a)){
                    Nodo nuevo = new Nodo(b, aux.getEnlace());
                    aux.setEnlace(nuevo);
                    aux = nuevo;
                }
            }
            aux = aux.getEnlace();
            pos++;
        }
    }
    
    
}
