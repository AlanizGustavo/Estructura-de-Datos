/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;

/**
 *
 * @author Alaniz Gustavo     FAI-822
 * @author Bustamante Leonel  FAI-2355
 * @author Recalde Franco     FAI-2757
 */
public class ArbolBin {
    
    private NodoArbol raiz;
    
    //CONSTRUCTOR
    
    public ArbolBin(){
        this.raiz=null;
    }
    
    //OBSERVADORAS
    
    public boolean esVacio(){
        //METODO QUE DEVUELVE TRUE SI EL ARBOL ESTA VACIO Y FALSE DE CASO CONTRARIO
        boolean exito=false;
        if(this.raiz==null){
            exito=true;
        }
        return exito;
    }
    
    public Object padre(Object elem){
        //METODO QUE DEVUELVE EL ELEMENTO DEL PADRE DE UN ELEMENTO ENVIADO POR PARAMETRO
        Object elementoP=null;
        
        if(obtenerNodo(this.raiz,elem)!=null&&!this.esVacio()&& !this.raiz.getElemento().equals(elem)){
            elementoP=padreAux(this.raiz,elem).getElemento();
        }
        
        return elementoP;
    }
    
    private NodoArbol padreAux(NodoArbol n, Object buscado){
        NodoArbol resultado=null;
        if(n!=null){
            if(n.getIzquierdo()!=null && (n.getIzquierdo().getElemento().equals(buscado))){
                //SI EL BUSCADO ES EL HI DE n, LO DEVUELVE
                resultado=n;
            }
            else if(n.getDerecho()!=null && (n.getDerecho().getElemento().equals(buscado))){
                //SI EL BUSCADO ES EL HD DE n, LO DEVUELVE
                resultado=n;
            }
            else{
                //NO ES EL BUSCADO: BUSCA PRIMERO EL HI
                resultado=padreAux(n.getIzquierdo(),buscado);
                //SI NO LO ENCUENTRA EN EL HI, BUSCA EN HD
                if(resultado==null){
                    resultado=padreAux(n.getDerecho(),buscado);
                }
            }
        }
        return resultado;
    }
    
    public int altura(){
        int altura;
        
        altura=alturaArbol(this.raiz);
        
        return altura;
    }
    
    private int alturaArbol(NodoArbol nodo){
        int alturaIzquierda=-1,alturaDerecha=-1;
        if(nodo!=null){
            alturaIzquierda=alturaArbol(nodo.getIzquierdo())+1;
            alturaDerecha=alturaArbol(nodo.getDerecho())+1;
        }
        return Math.max(alturaIzquierda, alturaDerecha);
    }
    
    public int nivel(Object elem){
        int nivel=-1;
        nivel=buscarNivel(this.raiz,elem,-1);
        return nivel;
    }
    
    private int buscarNivel(NodoArbol nodo,Object elem,int nivel){
        int encontrado=-1;
        
        if(nodo!=null){
            if(nodo.getElemento().equals(elem)){
                encontrado=nivel+1;
                
            }
            else{
                if(nodo.getIzquierdo()==null && nodo.getDerecho()==null){
                    encontrado=-1;
                }
                else{
                    if(nodo.getIzquierdo()!=null){
                        encontrado=buscarNivel(nodo.getIzquierdo(),elem,nivel+1);
                    }
                    if(nodo.getDerecho()!=null && encontrado==-1){
                        encontrado=buscarNivel(nodo.getDerecho(),elem,nivel+1);
                    }
                }
            
            }
        }
        return encontrado;
    }
    public String toString(){
        String texto="";
        if(this.esVacio()){
            texto="ARBOL VACIO";
        }
        else{
            texto=toStringAux(this.raiz,texto);
        }
        
        return texto;
    }
    
    private String toStringAux(NodoArbol nodo,String texto){
        
        if(nodo!=null){
            if(nodo.getIzquierdo()!=null&&nodo.getDerecho()!=null){
                texto=texto+"NODO: "+nodo.getElemento().toString()+" HI: "+nodo.getIzquierdo().getElemento().toString()+" "+ " HD:  "+nodo.getDerecho().getElemento().toString()+"\n";
            }
            else{
                if(nodo.getIzquierdo()==null&&nodo.getDerecho()!=null){
                    texto=texto+"NODO: "+nodo.getElemento().toString()+" HI: --"+ " HD: "+nodo.getDerecho().getElemento().toString()+"\n";
                }else if(nodo.getDerecho()==null&&nodo.getIzquierdo()!=null){
                    texto=texto+"NODO: "+nodo.getElemento().toString()+" HI: "+nodo.getIzquierdo().getElemento().toString()+" "+ " HD: --"+"\n";
                }
                else{
                    texto=texto+"NODO: "+nodo.getElemento().toString()+" HI: --"+ " HD: --"+"\n";
                }
            }
            texto=toStringAux(nodo.getIzquierdo(),texto);
            texto=toStringAux(nodo.getDerecho(),texto);
            
        }
        return texto;
    }
    
    public Lista listarPreorden(){
        //RETORNA UNA LISTA CON LOS ELEMENTOS DEL ARBOL EN PREORDEN
        Lista lis=new Lista();
        listarPreordenAux(this.raiz,lis);
        return lis;
    }
    
    private void listarPreordenAux(NodoArbol nodo, Lista lis){
        if(nodo!=null){
            //VISITA EL ELEMENTO EN EL NODO
            lis.insertar(nodo.getElemento(),lis.longitud()+1);
            
            //RECORRE A SUS HIJOS EN PREORDEN
            listarPreordenAux(nodo.getIzquierdo(),lis);
            listarPreordenAux(nodo.getDerecho(),lis);
            
        }
    }
    
    public Lista listarPostorden(){
         //RETORNA UNA LISTA CO LOS ELEMENTOS DEL ARBOL EN POSTORDEN
        Lista lis=new Lista();
        listarPostordenAux(this.raiz,lis);
        return lis;
    }
    
    private void listarPostordenAux(NodoArbol nodo, Lista lis){
        if(nodo!=null){ 
            //RECORRE A SUS HIJOS EN POSTORDEN
            listarPostordenAux(nodo.getIzquierdo(),lis);
            listarPostordenAux(nodo.getDerecho(),lis);
            
             //VISITA EL ELEMENTO EN EL NODO
            lis.insertar(nodo.getElemento(),lis.longitud()+1);
            
        }
    }
    
    public Lista listarInorden(){
         //RETORNA UNA LISTA CO LOS ELEMENTOS DEL ARBOL EN INORDEN
        Lista lis=new Lista();
        listarInordenAux(this.raiz,lis);
        return lis;
    }
    
    private void listarInordenAux(NodoArbol nodo, Lista lis){
        if(nodo!=null){ 
            //RECORRE A SUS HIJOS EN INORDEN
            listarInordenAux(nodo.getIzquierdo(),lis);
            
             //VISITA EL ELEMENTO EN EL NODO
            lis.insertar(nodo.getElemento(),lis.longitud()+1);
            
            listarInordenAux(nodo.getDerecho(),lis); 
        }
    }
    
    public Lista listarPorNiveles(){
        Lista l=new Lista();
        Cola q=new Cola();
        q.poner(this.raiz);
        if(!this.esVacio()){
            while(!q.esVacia()){
                NodoArbol nodoActual=(NodoArbol)q.obtenerFrente();
                q.sacar();
                l.insertar(nodoActual.getElemento(), l.longitud()+1);
                if(nodoActual.getIzquierdo()!=null){
                    q.poner(nodoActual.getIzquierdo());
                }
                if(nodoActual.getDerecho()!=null){
                    q.poner(nodoActual.getDerecho());
                }
            }
        }    
        return l;
    }
    
    public Lista frontera(){
        Lista lis=new Lista();
        fronteraAux(this.raiz,lis);
        return lis;
    }
    
    public void fronteraAux(NodoArbol nodo, Lista lis){
        if(nodo!=null){
            if(nodo.getDerecho()==null && nodo.getIzquierdo()==null){
                lis.insertar(nodo.getElemento(),lis.longitud()+1 );
            }
            else{
                fronteraAux(nodo.getIzquierdo(),lis);
                fronteraAux(nodo.getDerecho(),lis);
            }
        }
    }
    
    
    //MODIFICADORAS
    
    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar){
        /*INSERTA elemNuevo COMO HIJO DEL PRIMER NODO ENCONTRADO EN PREORDEN
        IGUAL A elemPadre, COMO HIJO IQUIERDO (I) O DERECHO (D), SEGURN 
        LO INDIQUE EL PARAMETRO LUGAR
        */
        
        boolean exito=true;
        
        if(this.raiz==null){
            //SI EL ARBOL ESTA VACIO, PONEMOS EL elemNuevo 
            this.raiz=new NodoArbol(elemNuevo);
        }
        else{
            //SI NO ESTA VACIO, SE BUSCA AL PADRE
            NodoArbol nodoPadre=obtenerNodo(this.raiz, elemPadre);
            if(nodoPadre!=null){
                if(lugar=='I' && nodoPadre.getIzquierdo()==null){
                    //SI EL PADRE EXISTE Y NO TIENE HI SE LO AGREGA
                    nodoPadre.setIzquierdo(new NodoArbol(elemNuevo));
                }
                else{
                    if(lugar=='D'&& nodoPadre.getDerecho()==null){
                        //SI EL PADRE EXISTE Y NO TIENE HD SE LO AGREGA
                        nodoPadre.setDerecho(new NodoArbol(elemNuevo));
                    }
                    else{
                        //SI EL PADRE NO EXISTE O YA TIENE ESE HIJO, DA ERROR
                        exito=false;
                    }  
                }
            }
            else{
                exito=false;
            }            
        }
        return exito;
    }
    
    private NodoArbol obtenerNodo(NodoArbol n, Object buscado){
        /*METODO QUE BUSCA UN ELEMENTO Y DEVUELVE EL NODO QUE
        LO CONTIENE. SI NO SE ENCUENTRA buscado DEVUELVE NULL
        */
        
        NodoArbol resultado=null;
        if(n!=null){
            if(n.getElemento().equals(buscado)){
                //SI EL BUSCADO ES n, LO DEVUELVE
                resultado=n;
            }
            else{
                //NO ES EL BUSCADO: BUSCA PRIMERO EL HI
                resultado=obtenerNodo(n.getIzquierdo(),buscado);
                //SI NO LO ENCUENTRA EN EL HI, BUSCA EN HD
                if(resultado==null){
                    resultado=obtenerNodo(n.getDerecho(),buscado);
                }
            }
        }
        return resultado;
    }
    
    public void vaciar(){
        this.raiz=null;
    }
    
    public ArbolBin clone(){
        ArbolBin copia=new ArbolBin();
        if(!this.esVacio()){
            copia.raiz=clonAux(this.raiz);    
        }
        return copia;
    }
    
    private NodoArbol clonAux(NodoArbol nodo){
        NodoArbol nuevo=new NodoArbol(nodo.getElemento());
        if(nodo.getIzquierdo()!=null){
            nuevo.setIzquierdo(clonAux(nodo.getIzquierdo()));
        }
        if(nodo.getDerecho()!=null){
            nuevo.setDerecho(clonAux(nodo.getDerecho()));
        }
        return nuevo;
    }
}
