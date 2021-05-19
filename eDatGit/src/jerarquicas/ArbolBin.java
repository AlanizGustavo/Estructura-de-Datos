package jerarquicas;

import jerarquicas.NodoArbol;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;

/**
 * @author Alaniz Gustavo FAI-822
 * @author Bustamante Leonel FAI-2355
 * @author Recalde Franco FAI-2757
 */
public class ArbolBin {

    private NodoArbol raiz;

//CONSTRUCTOR ------------------------------------------------------------------
    public ArbolBin() {
        this.raiz = null;
    }

//ES VACIA ---------------------------------------------------------------------
    public boolean esVacio() {
        //Metodo sin parametros que retorna true en caso de Cola vacia
        return (this.raiz == null); 
    }

//PADRE ------------------------------------------------------------------------
    public Object padre(Object elem) {
        /*Metodo que recibe elemento buscado por parametro y llama a un metodo 
        privado "padreAux" para encontrar el padre en Arbol Binario*/
        Object elementoPadre = null;

        if (!this.esVacio() && obtenerNodo(this.raiz, elem) != null && !this.raiz.getElemento().equals(elem)) {
            //Si el arbol no esta vacio y el elemento no es null y la raiz no es el elemento buscado 
            elementoPadre = padreAux(this.raiz, elem).getElemento(); //Llamamos al metodo privado
        }

        return elementoPadre;//En caso de que la raiz sea el elemento buscado este no tiene padre
    }

    private NodoArbol padreAux(NodoArbol nodo, Object buscado) {
        /*Elemento PRIVADO que recibe por parametro un nodo que sera la raiz de 
        los subarboles verificados en cada paso recursivo y un elemento el cual
        estamos buscado*/

        NodoArbol resultado = null;

        if (nodo != null) {//En caso de nodo null se retorna null
            if (nodo.getIzquierdo() != null && (nodo.getIzquierdo().getElemento().equals(buscado))) {
                //SI EL BUSCADO ES EL HI DE n, LO DEVUELVE
                resultado = nodo;
            } else if (nodo.getDerecho() != null && (nodo.getDerecho().getElemento().equals(buscado))) {
                //SI EL BUSCADO ES EL HD DE n, LO DEVUELVE
                resultado = nodo;
            } else {
                //NO ES EL BUSCADO: BUSCA PRIMERO EN EL SUB ARBOL IZQUIERDO
                resultado = padreAux(nodo.getIzquierdo(), buscado);
                //NO ES EL BUSCADO: BUSCA PRIMERO EN EL SUB ARBOL DERECHO
                if (resultado == null) {
                    resultado = padreAux(nodo.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

//ALTURA -----------------------------------------------------------------------
    public int altura() {
        /*Metodo que bsuca la altura de un arbol partiendo desde su raiz, para
        esto llama a metodo privado "alturaArbol" con el nodo raiz del arbol*/
        int altura;

        altura = alturaArbol(this.raiz);

        return altura;
    }

    private int alturaArbol(NodoArbol nodo) {
        /*Metodo privado que recibe por parametro un nodo que en primera instancia
        es raiz del arbol. El metodo retorna la longitud del camino mas largo de
        la raiz a la hoja mas lejana, en caso de arbol binario vacio retorna -1*/
        int alturaIzquierda = -1, alturaDerecha = -1;

        if (nodo != null) {//En caso de arbol vacio se envian alturaIzquierda=-1 y alturaDerecha = -1
            alturaIzquierda = alturaArbol(nodo.getIzquierdo()) + 1;
            alturaDerecha = alturaArbol(nodo.getDerecho()) + 1;
        }

        return Math.max(alturaIzquierda, alturaDerecha);
    }

//NIVEL ------------------------------------------------------------------------
    public int nivel(Object elem) {
        /*Metodo que retorna el nivel en cual se encuentra un nodo recibido por 
        parametro. Este retorna lo que devuelva el metodo privado "buscarNivel"*/
        int nivel;
        nivel = buscarNivel(this.raiz, elem, -1);
        return nivel;
    }

    private int buscarNivel(NodoArbol nodo, Object elem, int nivel) {
        /*Metodo privado que recibe un nodo en primera instancia raiz del arbol, 
        un elemento buscado y el Nivel que sera la forma de indicar en que nivel
        esta el nodo buscado. En caso de no estar el nodo se retorna -1*/
        int encontrado = -1;

        if (nodo != null) { //En caso de nodo null se retorna -1
            if (nodo.getElemento().equals(elem)) {
                //Una vez encontrado asignamos a encontrado el nivel +1
                encontrado = nivel + 1;
            } else {
                if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                    encontrado = -1;
                    //Si llegamos a una hoja encontrado se asigna con -1 y se retorna
                } else {
                    //En caso de no ser hoja se busca en el subarbol izquierdo y aumentamos el nivel
                    if (nodo.getIzquierdo() != null) {
                        encontrado = buscarNivel(nodo.getIzquierdo(), elem, nivel + 1);
                    }
                    //Por ultimo en el subarbol derecho y aumentamos el nivel
                    if (nodo.getDerecho() != null && encontrado == -1) {
                        encontrado = buscarNivel(nodo.getDerecho(), elem, nivel + 1);
                    }
                }
            }
        }
        //Ej raiz = -1 => encontrado=nivel+1 => encontrado = -1 +1 => encontrado = 0
        return encontrado;
    }

//TO STRING ------------------------------------------------------------------------
    public String toString() {
        /*Metodo sin parametros para testeo de programa se recomienda comentar 
        cuando no se esta testeando. En caso de ser vacio se retorna un mensaje
        "ARBOL VACIO" sino se llama a un metodo privado "toStringAux"*/
        String texto = "";

        if (this.esVacio()) {
            texto = "ARBOL VACIO";
        } else {
            texto = toStringAux(this.raiz, texto);
        }

        return texto;
    }

    private String toStringAux(NodoArbol nodo, String texto) {
        /*Metodo privado que recibe un nodo siendo este la raiz en una primera 
        instancia junto con una cadena vacia que sera la retornada*/

        if (nodo != null) {//En caso de nodo null se retorna una cadena vacia
            if (nodo.getIzquierdo() != null && nodo.getDerecho() != null) {
                //En caso de tener ambos hijos se imprimen
                texto = texto + "NODO: " + nodo.getElemento().toString()
                        + " HI: " + nodo.getIzquierdo().getElemento().toString() + " "
                        + " HD:  " + nodo.getDerecho().getElemento().toString() + "\n";
            } else {
                //3 posibles casos donde  no se encuentra a ambos hijos se puede tener un hijo null y el otro no o no tener ninguno
                if (nodo.getIzquierdo() == null && nodo.getDerecho() != null) {
                    texto = texto + "NODO: " + nodo.getElemento().toString() + " HI: --" + " HD: " + nodo.getDerecho().getElemento().toString() + "\n";
                } else if (nodo.getDerecho() == null && nodo.getIzquierdo() != null) {
                    texto = texto + "NODO: " + nodo.getElemento().toString() + " HI: " + nodo.getIzquierdo().getElemento().toString() + " " + " HD: --" + "\n";
                } else {
                    texto = texto + "NODO: " + nodo.getElemento().toString() + " HI: --" + " HD: --" + "\n";
                }
            }
            //Si existieran nodos visitariamos estos hasta llegar al null
            texto = toStringAux(nodo.getIzquierdo(), texto);
            texto = toStringAux(nodo.getDerecho(), texto);
        }

        return texto;
    }

//LISTAR PREORDEN --------------------------------------------------------------
    public Lista listarPreorden() {
        /*Llama a un metodo privado "ListarPreordenAux" donde se envia la raiz
        del arbol mas una lista creada en este modulo*/
        Lista lis = new Lista();

        listarPreordenAux(this.raiz, lis);

        return lis;
    }

    private void listarPreordenAux(NodoArbol nodo, Lista lis) {
        /*Metodo que recibe por parametro un nodo que es la raiz del subarbol 
        recorrido y una lista donde se listaran los elementos del arbol en pre orden*/

        if (nodo != null) {
            //VISITA EL ELEMENTO EN EL NODO
            lis.insertar(nodo.getElemento(), lis.longitud() + 1);

            //RECORRE A SUS HIJOS EN PREORDEN
            listarPreordenAux(nodo.getIzquierdo(), lis);
            listarPreordenAux(nodo.getDerecho(), lis);

        }
    }

//LISTAR POSORDEN ---------------------------------------------------------------
    public Lista listarPosorden() {
        /*Llama a un metodo privado "ListarPostrdenAux" donde se envia la raiz
        del arbol mas una lista creada en este modulo*/
        Lista lis = new Lista();
        listarPosordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPosordenAux(NodoArbol nodo, Lista lis) {
        /*Metodo que recibe por parametro un nodo que es la raiz del subarbol 
        recorrido y una lista donde se listaran los elementos del arbol en pos orden*/

        if (nodo != null) {
            //RECORRE A SUS HIJOS EN POSTORDEN
            listarPosordenAux(nodo.getIzquierdo(), lis);
            listarPosordenAux(nodo.getDerecho(), lis);

            //VISITA EL ELEMENTO EN EL NODO
            lis.insertar(nodo.getElemento(), lis.longitud() + 1);

        }
    }

//LISTAR INORDEN ---------------------------------------------------------------
    public Lista listarInorden() {
        /*Llama a un metodo privado "ListarInordenAux" donde se envia la raiz
        del arbol mas una lista creada en este modulo*/
        Lista lis = new Lista();
        listarInordenAux(this.raiz, lis);
        return lis;
    }

    private void listarInordenAux(NodoArbol nodo, Lista lis) {
        /*Metodo que recibe por parametro un nodo que es la raiz del subarbol 
        recorrido y una lista donde se listaran los elementos del arbol en in orden*/

        if (nodo != null) {
            //RECORRE A SUS HIJOS EN INORDEN
            listarInordenAux(nodo.getIzquierdo(), lis);

            //VISITA EL ELEMENTO EN EL NODO
            lis.insertar(nodo.getElemento(), lis.longitud() + 1);

            listarInordenAux(nodo.getDerecho(), lis);
        }
    }

//LISTAR POR NIVELES -----------------------------------------------------------
    public Lista listarPorNiveles() {
        /*Metodo que iterativamente recorre el arbol binario imprimiendo hasta*/
        Lista lista = new Lista();
        Cola cola = new Cola();
        NodoArbol nodoActual;

        cola.poner(this.raiz); //Asigno a la cola la raiz del arbol
        if (!this.esVacio()) {
            while (!cola.esVacia()) {
                nodoActual = (NodoArbol) cola.obtenerFrente();
                cola.sacar();
                lista.insertar(nodoActual.getElemento(), lista.longitud() + 1);
                if (nodoActual.getIzquierdo() != null) {
                    cola.poner(nodoActual.getIzquierdo());
                }
                if (nodoActual.getDerecho() != null) {
                    cola.poner(nodoActual.getDerecho());
                }
            }
        }
        return lista;
    }

//FRONTERA ---------------------------------------------------------------------
    public Lista frontera() {
        /*Metodo sin parametros que llama a meotodo privado "fronteraAux" el cual
        retornara una lista con las hojas del arbol*/
        Lista lis = new Lista();
        fronteraAux(this.raiz, lis);
        return lis;
    }

    private void fronteraAux(NodoArbol nodo, Lista lis) {
        /*Metodo privado que recibe un nodo que sera la raiz del arbol en primera
        instancia y una lista donde si el elemento no tiene hijos sera una hoja y
        este se listara*/
        if (nodo != null) {
            if (nodo.getDerecho() == null && nodo.getIzquierdo() == null) {
                lis.insertar(nodo.getElemento(), lis.longitud() + 1);
            } else {
                fronteraAux(nodo.getIzquierdo(), lis);
                fronteraAux(nodo.getDerecho(), lis);
            }
        }
    }

//INSERTAR ---------------------------------------------------------------------
    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {
        /*INSERTA elemNuevo COMO HIJO DEL PRIMER NODO ENCONTRADO EN PREORDEN
        IGUAL A elemPadre, COMO HIJO IQUIERDO (I) O DERECHO (D), SEGURN 
        LO INDIQUE EL PARAMETRO LUGAR
         */

        boolean exito = true;

        if (this.raiz == null) {
            //SI EL ARBOL ESTA VACIO, PONEMOS EL elemNuevo 
            this.raiz = new NodoArbol(elemNuevo);
        } else {
            //SI NO ESTA VACIO, SE BUSCA AL PADRE
            NodoArbol nodoPadre = obtenerNodo(this.raiz, elemPadre);
            if (nodoPadre != null) {
                if (lugar == 'I' && nodoPadre.getIzquierdo() == null) {
                    //SI EL PADRE EXISTE Y NO TIENE HI SE LO AGREGA
                    nodoPadre.setIzquierdo(new NodoArbol(elemNuevo));
                } else {
                    if (lugar == 'D' && nodoPadre.getDerecho() == null) {
                        //SI EL PADRE EXISTE Y NO TIENE HD SE LO AGREGA
                        nodoPadre.setDerecho(new NodoArbol(elemNuevo));
                    } else {
                        //SI EL PADRE NO EXISTE O YA TIENE ESE HIJO, DA ERROR
                        exito = false;
                    }
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        /*METODO QUE BUSCA UN ELEMENTO Y DEVUELVE EL NODO QUE
        LO CONTIENE. SI NO SE ENCUENTRA buscado DEVUELVE NULL
         */

        NodoArbol resultado = null;
        if (n != null) {
            if (n.getElemento().equals(buscado)) {
                //SI EL BUSCADO ES n, LO DEVUELVE
                resultado = n;
            } else {
                //NO ES EL BUSCADO: BUSCA PRIMERO EL HI
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                //SI NO LO ENCUENTRA EN EL HI, BUSCA EN HD
                if (resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

//VACIAR ---------------------------------------------------------------
    public void vaciar() {
        //Metodo que vacia el arbol binario mediante garbaje collector
        this.raiz = null;
    }

//CLONAR ---------------------------------------------------------------
    public ArbolBin clone() {
        /*Metodo que llama a metodo privado si el arbol no es vacio con elemento
        raiz como parametro. Si el arbol esta vacio se retorna un arbol vacio*/
        ArbolBin copia = new ArbolBin();

        if (!this.esVacio()) {
            copia.raiz = clonAux(this.raiz);
        }

        return copia;
    }

    private NodoArbol clonAux(NodoArbol nodo) {
        /*Metodo privado que recibe nodo con raiz del arbol en primera instancia
        clona un arbol recursivamente donde se va creando un nuevo nodo que sera
        raiz del arbol clonado y  se repite hasta las hojas del arbol original*/
        NodoArbol nuevoNodo = new NodoArbol(nodo.getElemento());

        if (nodo.getIzquierdo() != null) {
            nuevoNodo.setIzquierdo(clonAux(nodo.getIzquierdo()));
        }
        if (nodo.getDerecho() != null) {
            nuevoNodo.setDerecho(clonAux(nodo.getDerecho()));
        }
        return nuevoNodo;
    }
    
    public boolean verificarPatron(Lista patron){
        boolean exito;
        int pos=1;
        exito=verificarAux(this.raiz,patron,pos);
        return exito;
    }
    private boolean verificarAux(NodoArbol nodo, Lista patron, int pos){
        boolean exito=false;
        if(pos>patron.longitud()){
            exito=true;
        }
        else{
            if(nodo!=null && nodo.getElemento().equals(patron.recuperar(pos))){
                exito=verificarAux(nodo.getIzquierdo(),patron,pos+1);
                if(!exito){
                    exito=verificarAux(nodo.getDerecho(),patron,pos+1);
                }
            }
            
        }
        return exito;    
    }
    
    public ArbolBin clonarInvertido(){
        ArbolBin arbolInvertido=new ArbolBin();
        if(!this.esVacio()){
            
            arbolInvertido.raiz=new NodoArbol(this.raiz.getElemento());
            invertidoAux(this.raiz,arbolInvertido.raiz);
        }
        return arbolInvertido;
    }
    
    private void invertidoAux(NodoArbol nodo, NodoArbol aux){
        if(nodo!=null){
            if(nodo.getIzquierdo()!=null){
                aux.setDerecho((new NodoArbol(nodo.getIzquierdo().getElemento())));
            }
            if(nodo.getDerecho()!=null){
                aux.setIzquierdo(new NodoArbol(nodo.getDerecho().getElemento()));
            }
            invertidoAux(nodo.getIzquierdo(),aux.getDerecho());
            invertidoAux(nodo.getDerecho(),aux.getIzquierdo());
            
        }
    }
    
    public void cambiarHijos(Object izq, Object p, Object der){
        cambiarAux(this.raiz,p,izq,der);
    }
    
    private void cambiarAux(NodoArbol nodo, Object p, Object izq,Object der){
        boolean encontrado=false;
        if (nodo != null&&!encontrado) {
            if (nodo.getElemento().equals(p)) {
                
                if(nodo.getIzquierdo()!=null){
                    nodo.getIzquierdo().setElemento(izq);
                }
                else{
                    NodoArbol nuevo=new NodoArbol(izq);
                    nodo.setIzquierdo(nuevo);
                }
                if(nodo.getDerecho()!=null){
                    nodo.getDerecho().setElemento(der);
                }
                else{
                    NodoArbol nuevo=new NodoArbol(der);
                    nodo.setDerecho(nuevo);
                }
                encontrado=true;
            } 
            else {
                //NO ES EL BUSCADO: BUSCA PRIMERO EL HI
                cambiarAux(nodo.getIzquierdo(), p,izq,der);
                //SI NO LO ENCUENTRA EN EL HI, BUSCA EN HD
                if(encontrado=true){
                    cambiarAux(nodo.getDerecho(), p,izq,der);
                }
                
                
            }
        }
        
    }
}
