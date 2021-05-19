/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;

/**
 *
 * @author alanizgustavo
 */
public class ArbolHeap {

    private final int TAMANIO = 20;
    private Comparable[] heap;
    private int ultimo;

    public ArbolHeap() {
        this.heap = new Comparable[TAMANIO];
        this.ultimo = 0;//la pos 0 no es utilizada
    }

    public boolean insertar(Comparable elem) {
        boolean exito = false; // SI EL ARREGLO ESTA LLENO, RETORNA FALSE
        if (this.TAMANIO > this.ultimo + 1) {
            this.heap[this.ultimo + 1] = elem;

            this.ultimo++;
            hacerSubir(ultimo);
            exito = true; //INSERTA BIEN. RETORNA TRUE
        }
        return exito;
    }

    public void hacerSubir(int posHijo) {
        
        int posPadre;
        Comparable temp = this.heap[posHijo];
        boolean salir = false;

        while (!salir) {
            posPadre = posHijo / 2;
            
            if (this.heap[1].compareTo(this.heap[posHijo])==0) {
                salir = true;
            } else if(this.heap[posHijo].compareTo(this.heap[posPadre]) > 0) {
                salir=true;
            }else{
                    temp = this.heap[posPadre];
                    this.heap[posPadre] = this.heap[posHijo];
                    this.heap[posHijo] = temp;
                    posHijo = posHijo/2;
                
            }

        }

    }

    public boolean eliminarCima() {
        boolean exito;
        if (this.ultimo == 0) {
            //ESTRUCTURA VACIA
            exito = false;
        } else {
            //SACA LA RAIZ Y PONE LA ULTIMA HORA EN SU LUGAR
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;
            //REESTABLECE LAS PROPIEDADES DE HEAP MINIMO
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }

    public void hacerBajar(int posPadre) {
        int posH;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;

        while (!salir) {
            posH = posPadre * 2;
            if (posH <= this.ultimo) {
                //TEMP TIENE AL MENOS UN HIJO(IZQ) Y LO CONSIDERA MENOR
                if (posH < this.ultimo) {
                    //HIJOMENOR TIENE HERMANO DERECHO
                    if (this.heap[posH + 1].compareTo(this.heap[posH]) < 0) {
                        //EL HIJO DERECHO ES EL MENOR DE LOS DOS
                        posH++;
                    }
                }

                //COMPARA AL HIJO MENOR CON EL PADRE
                if (this.heap[posH].compareTo(temp) < 0) {
                    //EL HIJO ES MENOR QUE EL PADRE, LOS INTERCAMBIA
                    this.heap[posPadre] = this.heap[posH];
                    this.heap[posH] = temp;
                    posPadre = posH;
                } else {
                    //EL PADRE ES MENOR QUE SUS HIJOS, ESTA BIEN UBICADO
                    salir = true;
                }
            } else {
                //EL TEMP ES HOJA, ESTA BIEN UBICADO
                salir = true;
            }
        }
    }
    
    public Comparable recuperarCima(){
        Comparable exito;
        if(!this.esVacio()){
           exito=this.heap[1]; 
        }
        else{
            exito=null;
        }
        return exito;
    }
    
    public boolean esVacio(){
        return(this.heap[1]==null);
    }

    public void vaciar(){
        int i;
        for(i=1;i<=this.ultimo;i++){
            this.heap[i]=null;
        }
        this.ultimo=0;
    }

    public ArbolHeap clon() {
        ArbolHeap copia=new ArbolHeap();
        copia.ultimo=this.ultimo;
        copia.heap=this.heap.clone();
        return copia;
    }
    public String toString() {
        int i;
        String texto, texto1 = "";
        for (i = 1; i <= this.ultimo; i++) {
            if (texto1 == "") {
                texto1 = texto1 + this.heap[i].toString()+ " --> HI: "+this.heap[2]+" HD: "+this.heap[3]+"\n" ;
            } else {
                texto1 = texto1 +  this.heap[i].toString()+ " --> HI: "+this.heap[2*i]+" HD: "+this.heap[(2*i)+1]+"\n";
            }

        }
        return texto = "ULTIMO: " + this.ultimo + "\nARBOL: \n" + texto1 ;
    }

}
