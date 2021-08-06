/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpfinaledat;

import Estructuras.Diccionario;
import Estructuras.Grafo;
import Estructuras.DiccionarioHash;
import Estructuras.MapeoAMuchos;
import java.io.*;
import java.util.StringTokenizer;

/**
 *
 * @author alanizgustavo
 */
public class Archivos {
    private static int cantLog=1;
    public static void leerTxt(String direccion,Grafo casa,Diccionario h,Diccionario d,DiccionarioHash team,MapeoAMuchos habitacionesPasadas) {
        String texto = "";

        try {
            BufferedReader bf = new BufferedReader(new FileReader(direccion));

            String bfRead;
            
            while ((bfRead = bf.readLine()) != null) {
                cargaInicial(bfRead,casa,h,d,team,habitacionesPasadas);
                
            }
            escribirLog("CARGA INICIAL DE DATOS");
        } catch (Exception e) {
            System.err.println("NO SE ENCUENTRA EL ARCHIVO");
        }

    }

    private static void cargaInicial(String linea,Grafo casa, Diccionario h,Diccionario d,DiccionarioHash team,MapeoAMuchos habitacionesPasadas) {
        StringTokenizer tokens = new StringTokenizer(linea, ";");
        String elem = tokens.nextToken();
        switch (elem) {
            case "H":
                
                TPFinalEDAT.agregarHabitacion(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(),casa,h);
                break;
            case "D":
                TPFinalEDAT.agregarDesafio(tokens.nextToken(), tokens.nextToken(),tokens.nextToken(),d);
                break;
            case "E":
                TPFinalEDAT.agregarEquipo(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(),tokens.nextToken(),team,habitacionesPasadas,h);
                break;
            case "P":
                TPFinalEDAT.agregarPuerta(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(),casa,h);
                break;
        }
    }

    public static void escribirLog(String elem) {
        File archivo = new File("/Users/alanizgustavo/NetBeansProjects/Estructura-de-Datos/log.txt");

        try {
            if (!archivo.exists()) {
                archivo = new File("/Users/alanizgustavo/NetBeansProjects/Estructura-de-Datos/log.txt");
            }

            //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
            FileWriter escribir = new FileWriter(archivo, true);

            //Escribimos en el archivo con el metodo write 
            escribir.write(cantLog + "- "+elem);
            escribir.write("\n");
            cantLog++;
            //Cerramos la conexion
            escribir.close();
        } //Si existe un problema al escribir cae aqui
        catch (Exception e) {
            System.out.println("Error al escribir");
        }

    }
}
