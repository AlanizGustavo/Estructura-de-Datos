/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import java.util.Arrays;

/**
 *
 * @author alanizgustavo
 */
public class MapeoAMuchos {

    private int TAMANIO;
    private NodoHashMapeoM[] tabla;
    private int cant;

    public MapeoAMuchos(int longitud) {
        this.TAMANIO = longitud;
        this.tabla = new NodoHashMapeoM[this.TAMANIO];
        this.cant = 0;
    }

    public boolean asociar(Object dom, Object rango) {

        boolean exito = true;
        int pos;
        NodoHashMapeoM nodo;
        pos = Math.abs(dom.hashCode() % this.TAMANIO);
        nodo = this.tabla[pos];
        if (exito && this.tabla[pos] == null) {
            this.tabla[pos] = new NodoHashMapeoM(dom, rango);
            this.cant++;
        } else {
            if (nodo.getDominio().equals(dom)) {
                if (nodo.getRango().localizar(rango) < 0) {
                    nodo.agregarElemento(rango);
                }

            } else {
                while (exito && nodo.getEnlace() != null) {
                    if (nodo.getEnlace().getDominio().equals(dom)) {
                        if (nodo.getEnlace().getRango().localizar(rango) < 0) {
                            nodo.agregarElemento(rango);

                        }
                        exito = false;
                    }
                    nodo = nodo.getEnlace();
                }
                if (nodo.getEnlace() == null) {
                    nodo.setEnlace(new NodoHashMapeoM(dom, rango));
                    this.cant++;
                }

            }

        }

        return exito;

    }

    public boolean desasociar(Object dom, Object rango) {

        boolean exito = false;
        int pos;
        NodoHashMapeoM nodo;
        pos = Math.abs(dom.hashCode() % this.TAMANIO);
        nodo = this.tabla[pos];
        if (!exito && nodo != null) {
            if (nodo.getDominio().equals(dom)) {
                Lista aux = nodo.getRango();
                if (aux.longitud() > 1) {
                    int posLista = aux.localizar(rango);
                    if (posLista >= 0) {
                        aux.eliminar(posLista);
                        exito = true;
                    }
                } else {
                    if (aux.recuperar(aux.longitud()).equals(rango)) {
                        this.tabla[pos] = this.tabla[pos].getEnlace();
                        exito = true;
                        this.cant--;
                    }

                }

            } else {
                while (!exito && nodo.getEnlace() != null) {
                    if (nodo.getEnlace().getDominio().equals(dom)) {
                        Lista aux = nodo.getEnlace().getRango();
                        if (aux.longitud() > 1) {
                            int posLista = aux.localizar(rango);
                            if (posLista >= 0) {
                                aux.eliminar(posLista);
                                exito = true;
                            }
                        } else {
                            if (aux.recuperar(aux.longitud()).equals(rango)) {
                                nodo.setEnlace(nodo.getEnlace().getEnlace());
                                exito = true;
                                this.cant--;
                            }
                        }
                    }
                    nodo = nodo.getEnlace();
                }
            }
        }

        return exito;
    }

    public boolean esVacio() {
        return (this.cant == 0);
    }

    public Lista obtenerValores(Object dom) {
        Lista resultado=null;
        int pos;
        boolean exito = false;
        NodoHashMapeoM nodo;
        pos = Math.abs(dom.hashCode() % this.TAMANIO);
        nodo = this.tabla[pos];
        if (nodo != null) {
            if (nodo.getDominio().equals(dom)) {
                resultado = nodo.getRango();
            } else {
                while (!exito && nodo.getEnlace() != null) {
                    if (nodo.getEnlace().getDominio().equals(dom)) {
                        resultado = nodo.getEnlace().getRango();
                    }
                    nodo = nodo.getEnlace();
                }
            }
        }
        return resultado;
    }

    public Lista obtenerConjuntoDominio() {
        Lista lis = new Lista();
        int pos = 0;
        if (!this.esVacio()) {
            while (lis.longitud() < this.cant) {
                NodoHashMapeoM aux = this.tabla[pos];
                while (aux != null) {
                    lis.insertar(aux.getDominio(), lis.longitud() + 1);
                    aux = aux.getEnlace();
                }
                pos++;
            }
        }
        return lis;
    }

    public Lista obtenerConjuntoRango() {
        Lista lis = new Lista();
        int pos = 0;
        int pos2 = 1;
        if (!this.esVacio()) {
            while (lis.longitud() < this.cant) {
                NodoHashMapeoM aux = this.tabla[pos];
                while (aux != null) {

                    while (pos2 <= aux.getRango().longitud()) {
                        if (lis.localizar(aux.getRango().recuperar(pos2)) < 0) {
                            lis.insertar(aux.getRango().recuperar(pos2), lis.longitud() + 1);
                        }

                        pos2++;
                    }
                    pos2 = 1;
                    aux = aux.getEnlace();
                }
                pos++;
            }
        }
        return lis;
    }

    public void vaciar() {
        int i;
        for (i = 0; i < this.TAMANIO; i++) {
            this.tabla[i] = null;
        }
        this.cant = 0;
    }

    public MapeoAMuchos clone() {
        MapeoAMuchos copia = new MapeoAMuchos(this.TAMANIO);
        int pos = 0;
        int pos2 = 1;
        if (!this.esVacio()) {
            while (copia.cant < this.cant) {
                NodoHashMapeoM aux = this.tabla[pos];

                NodoHashMapeoM aux2 = copia.tabla[pos];
                if (aux2 == null) {

                    copia.tabla[pos] = new NodoHashMapeoM(aux.getDominio(), aux.getRango().recuperar(pos2));
                    while (pos2 < aux.getRango().longitud()) {
                        copia.tabla[pos].agregarElemento(aux.getRango().recuperar(pos2 + 1));
                        pos2++;
                    }
                    pos2 = 1;
                    aux = aux.getEnlace();
                    aux2 = copia.tabla[pos];
                    copia.cant++;
                }

                while (aux != null) {
                    aux2.setEnlace(new NodoHashMapeoM(aux.getDominio(), aux.getRango().recuperar(pos2)));
                    while (pos2 < aux.getRango().longitud()) {
                        aux2.agregarElemento(aux.getRango().recuperar(pos2));
                        pos2++;
                    }
                    pos2 = 1;
                    copia.cant++;
                    aux2 = aux2.getEnlace();
                    aux = aux.getEnlace();

                }
                pos++;
            }
        }
        return copia;
    }

    public String toString() {
        String texto = "", salida;

        if (!this.esVacio()) {//En caso de nodo null se retorna una cadena vacia
            for (int i = 0; i < this.TAMANIO; i++) {
                texto += "\nPOS " + i + ": ";
                NodoHashMapeoM aux = this.tabla[i];
                if (aux == null) {
                    texto += " NULL \n";
                }
                while (aux != null) {

                    texto += "Dominio: " + aux.getDominio().toString() + " " + "Rango: " + aux.getRango().toString() + "\n";
                    aux = aux.getEnlace();
                    if (aux != null) {
                        texto += " , ";
                    }
                }
            }
        } else {
            texto = "TABLA VACIA";
        }

        salida = "TABLA: " + texto;

        return salida;

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.TAMANIO;
        hash = 83 * hash + Arrays.deepHashCode(this.tabla);
        hash = 83 * hash + this.cant;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MapeoAMuchos other = (MapeoAMuchos) obj;
        if (this.TAMANIO != other.TAMANIO) {
            return false;
        }
        if (this.cant != other.cant) {
            return false;
        }
        if (!Arrays.deepEquals(this.tabla, other.tabla)) {
            return false;
        }
        return true;
    }

}
