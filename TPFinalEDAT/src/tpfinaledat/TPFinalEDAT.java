/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpfinaledat;

import Estructuras.Diccionario;
import Estructuras.Grafo;
import Estructuras.DiccionarioHash;
import Estructuras.Lista;
import Estructuras.MapeoAMuchos;
import java.util.StringTokenizer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author alanizgustavo
 */
public class TPFinalEDAT {

    private static int cantHabitaciones = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Icon icono = new ImageIcon("/Users/alanizgustavo/NetBeansProjects/Estructura-de-Datos/EscapeHouse.png");

        //ESTRUCTURAS UTILIZADAS
        Grafo casa = new Grafo();
        Diccionario habitaciones = new Diccionario();
        Diccionario desafios = new Diccionario();

        DiccionarioHash equipos = new DiccionarioHash(20);
        MapeoAMuchos desafiosEquipos = new MapeoAMuchos(50);
        MapeoAMuchos habitacionesPasadas = new MapeoAMuchos(50);

        //INICIA JUEGO
        iniciarJuego(casa, habitaciones, desafios, equipos, desafiosEquipos, habitacionesPasadas, icono);

    }

    public static void altaDesafio(Diccionario d) {
        String puntaje;
        boolean seguir, exito;

        String nombreDesafio = JOptionPane.showInputDialog(null, "INGRESE EL NOMBRE DEL DESAFIO: ");
        String tipo = JOptionPane.showInputDialog(null, "INGRESE QUE TIPO DE DESAFIO ES: ");

        do {
            puntaje = JOptionPane.showInputDialog(null, "INGRESE EL PUNTAJE QUE OTORGA EL DESAFIO: ");

            seguir = esEntero(puntaje);
        } while (seguir);

        exito = agregarDesafio(puntaje, nombreDesafio, tipo, d);
        if (exito) {
            Archivos.escribirLog("ALTA DE DESAFIO DE PUNTAJE " + puntaje);
        }
    }

    public static void altaEquipo(DiccionarioHash team, MapeoAMuchos habitacionesPasadas, Diccionario h) {
        String puntajeSalida;
        String habitacionActual;
        boolean seguir = true, exito;

        String nombreEquipo = JOptionPane.showInputDialog(null, "INGRESE NOMBRE DEL EQUIPO: ");

        do {
            puntajeSalida = JOptionPane.showInputDialog(null, "INGRESE PUNTAJE PARA GANAR EL JUEGO: ");

            seguir = esEntero(puntajeSalida);
        } while (seguir);

        do {
            habitacionActual = JOptionPane.showInputDialog(null, "INGRESE HABITACION POR LA CUAL EMPIEZA: ");

            seguir = esEntero(habitacionActual);
        } while (seguir);

        exito = agregarEquipo(nombreEquipo, puntajeSalida, "0", habitacionActual, "0", team, habitacionesPasadas, h);
        if (exito) {
            Archivos.escribirLog("ALTA DE EQUIPO " + nombreEquipo);
        }
    }

    public static void altaHabitacion(Grafo casa, Diccionario h,Icon icono) {
        int num;
        boolean seguir = true, exito;
        String planta, dimension, salida = null;

        String nombre = JOptionPane.showInputDialog(null, "INGRESE NOMBRE DE LA HABITACION: ");

        do {
            planta = JOptionPane.showInputDialog(null, "INGRESE NUMERO DE PLANTA A LA QUE PERTENECE: ");

            seguir = esEntero(planta);
        } while (seguir);

        int eleccion = JOptionPane.YES_NO_OPTION;
        JOptionPane.showConfirmDialog(null, "TIENE SALIDA AL EXTERIOR?", "IMPORTANTE", eleccion, 0, icono);
        if (eleccion == JOptionPane.YES_OPTION) {
            salida = "true";
        } else if (eleccion == JOptionPane.NO_OPTION) {
            salida = "false";
        }

        do {
            dimension = JOptionPane.showInputDialog(null, "INGRESE LOS METROS CUADRADOS DE LA HABITACION: ");

            seguir = esEntero(dimension);
        } while (seguir);

        exito = agregarHabitacion(String.valueOf(cantHabitaciones + 1), nombre, planta, dimension, salida, casa, h);
        if (exito) {
            Archivos.escribirLog("ALTA DE HABITACION");
            cantHabitaciones++;
        }
    }

    public static void altaPuerta(Grafo casa, Diccionario h) {
        String codigo, codigo2, etiqueta;
        boolean seguir, exito;

        do {
            codigo = JOptionPane.showInputDialog(null, "INGRESE EL CODIGO DE UNA DE LAS HABITACIONES: ");

            seguir = esEntero(codigo);
        } while (seguir);

        do {
            codigo2 = JOptionPane.showInputDialog(null, "INGRESE EL CODIGO DE LA OTRA HABITACION: ");

            seguir = esEntero(codigo2);
        } while (seguir);

        do {
            etiqueta = JOptionPane.showInputDialog(null, "INGRESE LA CANTIDAD DE PUNTOS NECESARIA PARA ABRIR LA PUERTA: ");

            seguir = esEntero(etiqueta);
        } while (seguir);

        exito = agregarPuerta(codigo, codigo2, etiqueta, casa, h);
        if (exito) {
            Archivos.escribirLog("ALTA DE PUERTA ENTRE: " + codigo + " y " + codigo2);
        }
    }

    public static boolean agregarDesafio(String puntaje, String nombre, String tipo, Diccionario desafios) {
        //AGREGA DESAFIO EN DICCIONARIO Y RETORNA TRUE O FALSE DE ACUERDO AL EXITO

        boolean exito = false;

        Desafio desafio = new Desafio(Integer.parseInt(puntaje), nombre, tipo);

        exito = desafios.insertar(desafio.getPuntaje(), desafio);
        return exito;
    }

    public static boolean agregarEquipo(String nombre, String puntajeSalida, String puntajeAcumuladoTotal,
            String habitacionActual, String puntajeEnHabitacion, DiccionarioHash team, MapeoAMuchos habitacionesPasadas, Diccionario habitaciones) {
        //AGREGA EQUIPO AL DICCIONARIO. EN CASO DE EXITO, SE AGREGA UNA ASOCIACION ENTRE EL EQUIPO Y LA HABITACION EN LA QUE EMPIEZA EN MAPEO A MUCHOS.
        //Y POR ULTIMO SE AUMENTA LA CANTIDAD DE EQUIPOS QUE HAY EN LA HABITACION EN DONDE ES AGREGADO EL EQUIPO EN UNA UNIDAD 

        Habitacion habitacion;
        boolean exito = false;
        Equipo nuevo = new Equipo(nombre, Integer.parseInt(puntajeSalida),
                Integer.parseInt(puntajeAcumuladoTotal), Integer.parseInt(habitacionActual),
                Integer.parseInt(puntajeEnHabitacion));

        exito = team.insertar(nuevo.getNombre(), nuevo);
        if (exito) {
            habitacion = (Habitacion) habitaciones.obtenerDato(Integer.parseInt(habitacionActual));
            habitacionesPasadas.asociar(nuevo, habitacion);
            habitacion.setCantEquipos(habitacion.getCantEquipos() + 1);
        }
        return exito;
    }

    public static boolean agregarHabitacion(String codigo, String nombre, String planta, String dimension, String salida,
            Grafo casa, Diccionario habitaciones) {
        //AGREGA HABITACION AL DICCIONARIO. EN CASO DE EXITO, SE INSERTA EN EL GRAFO. RETORNA TRUE O FALSE 

        boolean exito = false;
        Habitacion habitacion;

        habitacion = new Habitacion(Integer.parseInt(codigo), nombre, Integer.parseInt(planta), Integer.parseInt(dimension),
                Boolean.parseBoolean(salida));

        exito = habitaciones.insertar(habitacion.getCodigo(), habitacion);
        if (exito) {
            casa.insertarVertice(habitacion);
            cantHabitaciones++;
        }
        return exito;
    }

    public static boolean agregarPuerta(String habitacion1, String habitacion2, String puntajeSalida, Grafo casa, Diccionario habitaciones) {
        //AGREGA PUERTA ENTRE HABITACIONES EN EL GRAFO

        boolean exito = false;

        exito = casa.insertarArco(habitaciones.obtenerDato(Integer.parseInt(habitacion1)), habitaciones.obtenerDato(Integer.parseInt(habitacion2)),
                Integer.parseInt(puntajeSalida));
        return exito;
    }

    public static void avanzarHabitacion(Diccionario habitaciones, DiccionarioHash equipos, MapeoAMuchos habitacionesPasadas, Grafo casa) {
        String nombre, codigo;
        boolean seguir;
        Habitacion habitacion;
        Equipo equipo;

        nombre = JOptionPane.showInputDialog(null, "INGRESE EL NOMBRE DEL EQUIPO: ");

        do {
            codigo = JOptionPane.showInputDialog(null, "INGRESE EL CODIGO DE LA HABITACION A PASAR: ");

            seguir = esEntero(codigo);
        } while (seguir);

        habitacion = (Habitacion) habitaciones.obtenerDato(Integer.parseInt(codigo));
        equipo = (Equipo) equipos.obtenerDato(nombre);
        if (habitacion != null && equipo != null) {
            pasarHabitacion(equipo, habitacion, habitacionesPasadas, habitaciones, casa);
        } else {
            JOptionPane.showMessageDialog(null, "EQUIPO O HABITACION NO ENCONTRADOS", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void bajaDesafio(Diccionario d) {
        String puntaje;
        boolean seguir, exito;

        do {
            puntaje = JOptionPane.showInputDialog(null, "INGRESE EL PUNTAJE DEL DESAFIO A DAR DE BAJA: ");

            seguir = esEntero(puntaje);
        } while (seguir);
        exito = d.eliminar(Integer.parseInt(puntaje));
        if (exito) {
            Archivos.escribirLog("BAJA DE DESAFIO CON PUNTAJE: " + puntaje);
        }
    }

    public static void bajaEquipo(DiccionarioHash team, Diccionario h) {
        String nombre;
        Equipo equipo;
        Habitacion aux;
        boolean exito;

        nombre = JOptionPane.showInputDialog(null, "INGRESE EL NOMBRE DEL EQUIPO A DAR DE BAJA: ");
        equipo = (Equipo) team.obtenerDato(nombre);
        aux = (Habitacion) h.obtenerDato(equipo.getHabitacionActual());
        aux.setCantEquipos(aux.getCantEquipos() - 1);
        exito = team.eliminar(nombre);
        if (exito) {
            Archivos.escribirLog("BAJA DE EQUIPO: " + nombre);
        }
    }

    public static void bajaHabitacion(Grafo casa, Diccionario h) {
        String codigo;
        Habitacion aux;
        boolean seguir;

        do {
            codigo = JOptionPane.showInputDialog(null, "INGRESE CODIGO DE LA HABITACION A DAR DE BAJA: ");

            seguir = esEntero(codigo);
        } while (seguir);
        aux = (Habitacion) h.obtenerDato(Integer.parseInt(codigo));
        if (aux.getCantEquipos() == 0) {
            casa.eliminarVertice(aux);
            h.eliminar(Integer.parseInt(codigo));
            Archivos.escribirLog("BAJA DE HABITACION: " + aux.getCodigo());
        } else {
            JOptionPane.showMessageDialog(null, "NO ES POSIBLE. HAY EQUIPOS EN LA HABITACION");
        }
    }

    public static void bajaPuerta(Grafo casa, Diccionario h) {
        String codigo, codigo2;
        boolean seguir, exito;

        do {
            codigo = JOptionPane.showInputDialog(null, "INGRESE EL CODIGO DE UNA DE LAS HABITACIONES: ");

            seguir = esEntero(codigo);
        } while (seguir);

        do {
            codigo2 = JOptionPane.showInputDialog(null, "INGRESE EL CODIGO DE LA OTRA HABITACION: ");

            seguir = esEntero(codigo2);
        } while (seguir);
        exito = casa.eliminarArco(h.obtenerDato(Integer.parseInt(codigo)), h.obtenerDato(Integer.parseInt(codigo2)));
        if (exito) {
            Archivos.escribirLog("BAJA DE PUERTA ENTRE HABITACION: " + codigo + " y " + codigo2);
        }
    }

    public static boolean esEntero(String valor) {
        //METODO UTILIZADO PARA VERIFICAR SI UN VALOR INGRESADO ES ENTERO. EN CASO DE QUE NO LO SEA MUESTRA UN WARNING

        int num;
        boolean seguir = true;
        try {
            num = Integer.parseInt(valor);

            seguir = false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "EL VALOR INGRESADO NO ES UN ENTERO", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
        return seguir;
    }

    public static void esPosibleLlegar(Diccionario h, Grafo casa) {
        String codigo, codigo2, valorLimite;
        boolean seguir;
        Habitacion aux, aux2;

        do {
            codigo = JOptionPane.showInputDialog(null, "INGRESE CODIGO DE LA HABITACION DE ORIGEN: ");

            seguir = esEntero(codigo);
        } while (seguir);

        do {
            codigo2 = JOptionPane.showInputDialog(null, "INGRESE CODIGO DE LA HABITACION DE DESTINO: ");

            seguir = esEntero(codigo);
        } while (seguir);

        do {
            valorLimite = JOptionPane.showInputDialog(null, "INGRESE EL PUNTAJE MAXIMO A OBTENER ENTRE AMBAS HABITACIONES: ");

            seguir = esEntero(codigo);
        } while (seguir);

        aux = (Habitacion) h.obtenerDato(Integer.parseInt(codigo));
        aux2 = (Habitacion) h.obtenerDato(Integer.parseInt(codigo2));
        boolean resultado = casa.esPosibleLlegar(aux, aux2, Integer.parseInt(valorLimite));
        if (resultado) {
            JOptionPane.showMessageDialog(null, "SI ES POSIBLE LLEGAR DE: " + codigo + " A " + codigo2 + " ACUMULANDO " + valorLimite + " PUNTOS");
        } else {
            JOptionPane.showMessageDialog(null, "NO ES POSIBLE LLEGAR DE: " + codigo + " A " + codigo2 + " ACUMULANDO " + valorLimite + " PUNTOS", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void iniciarJuego(Grafo casa, Diccionario habitaciones, Diccionario desafios, DiccionarioHash equipos,
            MapeoAMuchos desafiosEquipos, MapeoAMuchos habitacionesPasadas, Icon icono) {

        //MENU DE OPCIONES
        String[] opciones = {"CARGA INICIAL", "ABM", "CONSULTA SOBRE HABITACIONES", "CONSULTA SOBRE DESAFIOS",
            "CONSULTA SOBRE EQUIPOS", "CONSULTA GENERAL"};

        Boolean seguir = true;
        do {
            String opcion = (String) JOptionPane.showInputDialog(null, "SELECCIONE UNA OPCION", "ESCAPE HOUSE",
                    JOptionPane.DEFAULT_OPTION, icono, opciones, opciones[0]);
            if (opcion == null) {
                System.exit(0);
            }
            switch (opcion) {
                case "CARGA INICIAL":
                    Archivos.leerTxt("/Users/alanizgustavo/NetBeansProjects/Estructura-de-Datos/CargaInicial.txt", casa,
                            habitaciones, desafios, equipos, habitacionesPasadas);

                    break;
                case "ABM":
                    menuABM(icono, casa, habitaciones, desafios, equipos, habitacionesPasadas);

                    break;
                case "CONSULTA SOBRE HABITACIONES":
                    menuHabitaciones(icono, habitaciones, casa);

                    break;
                case "CONSULTA SOBRE DESAFIOS":
                    menuDesafios(icono, desafios, desafiosEquipos, equipos);
                    break;
                case "CONSULTA SOBRE EQUIPOS":
                    menuEquipos(equipos, desafios, desafiosEquipos, habitaciones, habitacionesPasadas, casa, icono);
                    break;
                case "CONSULTA GENERAL":
                    menuGeneral(casa, equipos, desafios, habitaciones, desafiosEquipos, icono);
                    break;
            }
            int resp = JOptionPane.showConfirmDialog(null, "¿DESEA CONTINUAR?", "CONTINUAR!",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icono);

            if (resp == 0) {
                seguir = true;
            } else {
                seguir = false;
            }

        } while (seguir);
    }

    public static void jugar(DiccionarioHash equipos, Diccionario desafios, MapeoAMuchos desafiosEquipos) {
        String nombre, puntaje;
        boolean seguir;
        Equipo equipo;
        Desafio desafio;

        nombre = JOptionPane.showInputDialog(null, "INGRESE EL NOMBRE DEL EQUIPO A REALIZAR EL DESAFIO: ");

        do {
            puntaje = JOptionPane.showInputDialog(null, "INGRESE EL PUNTAJE DEL DESAFIO: ");

            seguir = esEntero(puntaje);
        } while (seguir);

        equipo = (Equipo) equipos.obtenerDato(nombre);
        desafio = (Desafio) desafios.obtenerDato(Integer.parseInt(puntaje));
        if (equipo != null && desafio != null) {
            jugarDesafio(equipo, desafio, desafiosEquipos);
        } else {
            JOptionPane.showMessageDialog(null, "EQUIPO O DESAFIO INEXISTENTE", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void jugarDesafio(Equipo equipo, Desafio desafio, MapeoAMuchos desafiosEquipos) {
        //PERMITE A UN EQUIPO JUGAR UN DESAFIO.EN CASO DE QUE NO LO HAYA REALIZADO PREVIAMENTE, Y GENERA UNA ASOCIACION EQUIPO-DESAFIO EN EL MAPEO
        //AUMENTA EL PUNTAJE QUE TIENE LLEVADO EN LA HABITACION Y EL TOTAL ACUMULADO 

        Lista lis = desafiosEquipos.obtenerValores(equipo);

        if (lis == null || lis.localizar(desafio) < 0) {
            equipo.setPuntajeEnHabitacion(equipo.getPuntajeEnHabitacion() + desafio.getPuntaje());
            equipo.setPuntajeAcumuladoTotal(equipo.getPuntajeAcumuladoTotal() + desafio.getPuntaje());

            desafiosEquipos.asociar(equipo, desafio);

            JOptionPane.showMessageDialog(null, "DESAFIO GANADAO", "IMPORTANTE", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "EL DESAFIO YA FUE REALIZADO POR EL EQUIPO", "ERROR", JOptionPane.WARNING_MESSAGE);
        }

    }

    public static void menuABM(Icon icono, Grafo casa, Diccionario h, Diccionario d, DiccionarioHash team, MapeoAMuchos habitacionesPasadas) {
        //MENU DE OPCIONES DE ALTA, BAJA Y MODIFICACIONES

        String[] opciones = {"ALTA DE HABITACION", "ALTA DE EQUIPO", "ALTA DE DESAFIO", "ALTA DE PUERTA", "MODIFICACION DE HABITACION",
            "MODIFICACION DE EQUIPO", "MODIFICACION DE DESAFIO", "BAJA DE HABITACION", "BAJA DE EQUIPO", "BAJA DE DESAFIO", "BAJA DE PUERTA"};

        String opcion = (String) JOptionPane.showInputDialog(null, "SELECCIONE UNA OPCION", "OPCIONES",
                JOptionPane.DEFAULT_OPTION, icono, opciones, opciones[0]);

        if (opcion == null) {
            System.exit(0);
        }

        switch (opcion) {
            case "ALTA DE HABITACION":
                altaHabitacion(casa, h,icono);
                break;

            case "ALTA DE EQUIPO":
                altaEquipo(team, habitacionesPasadas, h);
                break;
            case "ALTA DE DESAFIO":
                altaDesafio(d);
                break;
            case "ALTA DE PUERTA":
                altaPuerta(casa, h);
                break;
            case "MODIFICACION DE HABITACION":
                String[] modHabitaciones = {"NOMBRE", "PLANTA", "DIMENSION", "SALIDA"};
                String opcionesHabitaciones = (String) JOptionPane.showInputDialog(null, "QUE DESEA MODIFICAR", "OPCIONES",
                        JOptionPane.DEFAULT_OPTION, icono, modHabitaciones, modHabitaciones[0]);
                menuModificacionHabitacion(opcionesHabitaciones, h);

                break;
            case "MODIFICACION DE EQUIPO":

                String[] modEquipos = {"PUNTAJE PARA GANAR", "PUNTAJE ACUMULADO TOTAL", "HABITACION ACTUAL", "PUNTAJE EN HABITACION ACTUAL"};
                String opcionesEquipos = (String) JOptionPane.showInputDialog(null, "QUE DESEA MODIFICAR", "OPCIONES",
                        JOptionPane.DEFAULT_OPTION, icono, modEquipos, modEquipos[0]);
                menuModificacionEquipos(opcionesEquipos, team);
                break;
            case "MODIFICACION DE DESAFIO":
                String[] modDesafios = {"NOMBRE", "TIPO"};
                String opcionesDesafios = (String) JOptionPane.showInputDialog(null, "QUE DESEA MODIFICAR", "OPCIONES",
                        JOptionPane.DEFAULT_OPTION, icono, modDesafios, modDesafios[0]);
                menuModificacionDesafios(opcionesDesafios, d);
                break;

            case "BAJA DE HABITACION":
                bajaHabitacion(casa, h);
                break;
            case "BAJA DE EQUIPO":
                bajaEquipo(team, h);
                break;
            case "BAJA DE DESAFIO":

                bajaDesafio(d);
                break;
            case "BAJA DE PUERTA":

                bajaPuerta(casa, h);
                break;
        }

    }

    public static void menuDesafios(Icon icono, Diccionario desafios, MapeoAMuchos desafiosEquipos, DiccionarioHash equipos) {
        //MENU DE OPCIONES DE DESAFIOS

        String[] opciones = {"MOSTRAR DESAFIOS", "MOSTRAR DESAFIOS RESUELTOS POR UN EQUIPO", "MOSTRAR DESAFIOS ESPECIFICOS"};

        String opcion = (String) JOptionPane.showInputDialog(null, "SELECCIONE UNA OPCION", "MENU DESAFIOS",
                JOptionPane.DEFAULT_OPTION, icono, opciones, opciones[0]);
        if (opcion == null) {
            System.exit(0);
        }
        switch (opcion) {
            case "MOSTRAR DESAFIOS":
                mostrarDesafio(desafios);

                break;
            case "MOSTRAR DESAFIOS RESUELTOS POR UN EQUIPO":

                mostrarDesafiosResueltosEquipo(equipos, desafiosEquipos);

                break;
            case "MOSTRAR DESAFIOS ESPECIFICOS":
                mostrarDesafiosEspecificos(desafios);
                break;

        }
    }

    public static void mostrarDesafio(Diccionario desafios) {
        String puntaje;
        boolean seguir;
        Desafio aux;

        do {
            puntaje = JOptionPane.showInputDialog(null, "INGRESE EL PUNTAJE DEL DESAFIO: ");

            seguir = esEntero(puntaje);
        } while (seguir);
        aux = (Desafio) desafios.obtenerDato(Integer.parseInt(puntaje));
        if (aux != null) {
            JOptionPane.showMessageDialog(null, aux.toString());
        } else {
            JOptionPane.showMessageDialog(null, "DESAFIO INEXISTENTE", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void mostrarDesafiosEspecificos(Diccionario desafios) {
        String puntaje, puntaje2, tipo;
        boolean seguir;
        Lista entrePuntajes;
        int pos = 1;
        Desafio aux;

        do {
            puntaje = JOptionPane.showInputDialog(null, "INGRESE EL PUNTAJE MINIMO DE LOS DESAFIOS A BUSCAR: ");

            seguir = esEntero(puntaje);
        } while (seguir);

        do {
            puntaje2 = JOptionPane.showInputDialog(null, "INGRESE EL PUNTAJE MAXIMO DE LOS DESAFIOS A BUSCAR: ");

            seguir = esEntero(puntaje);
        } while (seguir);

        tipo = JOptionPane.showInputDialog(null, "INGRESE A QUE CATEGORIA PERTENECE EL DESAFIO: ");

        entrePuntajes = desafios.listarRango(Integer.parseInt(puntaje), Integer.parseInt(puntaje2));
        //cambiar
        String filtrada = "";
        while (pos <= entrePuntajes.longitud()) {
            aux = (Desafio) entrePuntajes.recuperar(pos);
            if (aux.getTipo().equalsIgnoreCase(tipo)) {
                filtrada += " " + aux.toString();

            }
            pos++;
        }
        JOptionPane.showMessageDialog(null, filtrada);
    }

    public static void mostrarDesafiosResueltosEquipo(DiccionarioHash equipos, MapeoAMuchos desafiosEquipos) {
        String nombre;
        Equipo aux2;
        Lista desafiosHechos;

        nombre = JOptionPane.showInputDialog(null, "INGRESE EL NOMBRE DEL EQUIPO: ");
        aux2 = (Equipo) equipos.obtenerDato(nombre);
        if (aux2 != null) {
            desafiosHechos = desafiosEquipos.obtenerValores(aux2);
            if (desafiosHechos != null) {
                JOptionPane.showMessageDialog(null, desafiosHechos.toString());
            } else {
                JOptionPane.showMessageDialog(null, "NO SE RESOLVIO NINGUN DESAFIO TODAVIA");
            }
        } else {
            JOptionPane.showMessageDialog(null, "EQUIPO INEXISTENTE");
        }
    }

    public static void menuEquipos(DiccionarioHash equipos, Diccionario desafios, MapeoAMuchos desafiosEquipos, Diccionario habitaciones, MapeoAMuchos habitacionesPasadas, Grafo casa, Icon icono) {
        //MENU DE OPCIONES DE EQUIPOS

        String[] opciones = {"MOSTRAR INFORMACION DE EQUIPOS", "JUGAR DESAFIOS", "PASAR A HABITACION", "PUEDE SALIR"};

        String opcion = (String) JOptionPane.showInputDialog(null, "SELECCIONE UNA OPCION", "MENU EQUIPOS",
                JOptionPane.DEFAULT_OPTION, icono, opciones, opciones[0]);
        if (opcion == null) {
            System.exit(0);
        }
        switch (opcion) {
            case "MOSTRAR INFORMACION DE EQUIPOS":
                mostrarEquipo(equipos);

                break;
            case "JUGAR DESAFIOS":

                jugar(equipos, desafios, desafiosEquipos);

                break;
            case "PASAR A HABITACION":
                avanzarHabitacion(habitaciones, equipos, habitacionesPasadas, casa);
                break;
            case "PUEDE SALIR":

                salir(equipos, habitaciones);
                break;
        }
    }

    public static void menuGeneral(Grafo casa, DiccionarioHash equipos, Diccionario desafios, Diccionario habitaciones, MapeoAMuchos desafiosEquipos, Icon icono) {
        //MENU DE OPCIONES DE IMPRESION DE LAS ESTRUCTURAS

        String[] opciones = {"VER GRAFO", "VER DICCIONARIO DE HABITACIONES", "VER DICCIONARIO DE DESAFIOS", "VER DICCIONARIO DE EQUIPOS",
            "MAPEO DE EQUIPOS Y DESAFIOS"};

        String opcion = (String) JOptionPane.showInputDialog(null, "SELECCIONE UNA OPCION", "MENU GENERAL",
                JOptionPane.DEFAULT_OPTION, icono, opciones, opciones[0]);
        if (opcion == null) {
            System.exit(0);
        }
        switch (opcion) {
            case "VER GRAFO":

                JOptionPane.showMessageDialog(null, casa.toString());

                break;
            case "VER DICCIONARIO DE HABITACIONES":
                JOptionPane.showMessageDialog(null, habitaciones.toString());

                break;
            case "VER DICCIONARIO DE DESAFIOS":
                JOptionPane.showMessageDialog(null, desafios.toString());

                break;
            case "VER DICCIONARIO DE EQUIPOS":
                JOptionPane.showMessageDialog(null, equipos.toString());

                break;
            case "MAPEO DE EQUIPOS Y DESAFIOS":
                JOptionPane.showMessageDialog(null, desafiosEquipos.toString());

                break;

        }
    }

    public static void menuHabitaciones(Icon icono, Diccionario h, Grafo casa) {
        //MENU DE OPCIONES DE HABITACIONES

        String[] opciones = {"MOSTRAR HABITACION", "MOSTRAR HABITACIONES CONTIGUAS",
            "ES POSIBLE LLEGAR (verifica si es posible llegar de una habitacion a otra acumulando cierta cantidad de puntos)",
            "SIN PASAR POR (muestra los caminos entre dos habitaciones, sin pasar por la tercera acumulando menos puntos del ingresado)"};

        String opcion = (String) JOptionPane.showInputDialog(null, "SELECCIONE UNA OPCION", "MENU HABITACIONES",
                JOptionPane.DEFAULT_OPTION, icono, opciones, opciones[0]);
        if (opcion == null) {
            System.exit(0);
        }
        switch (opcion) {
            case "MOSTRAR HABITACION":
                mostrarHabitacion(h);
                break;
            case "MOSTRAR HABITACIONES CONTIGUAS":
                mostrarHabitacionesAdyacentes(h, casa);

                break;
            case "ES POSIBLE LLEGAR (verifica si es posible llegar de una habitacion a otra acumulando cierta cantidad de puntos)":
                esPosibleLlegar(h, casa);
                break;
            case "SIN PASAR POR (muestra los caminos entre dos habitaciones, sin pasar por la tercera acumulando menos puntos del ingresado)":
                sinPasarPor(h,casa);
                break;

        }
    }

    public static void menuModificacionDesafios(String opcion, Diccionario d) {
        //MENU DE MODIFICACIONES DE DESAFIOS

        switch (opcion) {
            case "NOMBRE":
                modificarNombreDesafio(d);

                break;
            case "TIPO":

                modificarTipoDesafio(d);
                break;
        }
    }

    public static void menuModificacionEquipos(String opcion, DiccionarioHash team) {
        //MENU DE MODIFICACIONES DE EQUIPOS
        switch (opcion) {
            case "PUNTAJE PARA GANAR":
                modificarPuntajeParaGanarEquipo(team);
                break;
            case "PUNTAJE ACUMULADO TOTAL":

                modificarPuntajeAcumuladoTotalEquipo(team);
                break;
            case "HABITACION ACTUAL":

                modificarHabitacionActual(team);
                break;
            case "PUNTAJE EN HABITACION ACTUAL":
                modificarPuntajeHabitacionActual(team);
                break;
        }
    }

    public static void menuModificacionHabitacion(String opcion, Diccionario h) {
        //MENU DE MODIFICACIONES DE LAS HABITACIONES

        switch (opcion) {
            case "NOMBRE":

                modificarNombreHabitacion(h);
                break;
            case "PLANTA":

                modificarPlantaHabitacion(h);
                break;
            case "DIMENSION":

                modificarDimensionHabitacion(h);
                break;
            case "SALIDA":

                modificarSalidaHabitacion(h);
                break;
        }
    }

    public static void modificarDimensionHabitacion(Diccionario h) {
        String dimension, codigo;
        boolean seguir;
        Habitacion aux;

        do {
            dimension = JOptionPane.showInputDialog(null, "INGRESE LOS METROS CUADRADOS DE LA HABITACION: ");

            seguir = esEntero(dimension);
        } while (seguir);
        do {
            codigo = JOptionPane.showInputDialog(null, "INGRESE CODIGO DE LA HABITACION A MODIFICAR: ");

            seguir = esEntero(codigo);
        } while (seguir);
        aux = (Habitacion) h.obtenerDato(Integer.parseInt(codigo));
        if (aux != null) {
            aux.setDimension(Integer.parseInt(dimension));
            Archivos.escribirLog("MODIFICACION METROS CUADRADOS DE HABITACION: " + aux.getDimension());
        }
    }

    public static void modificarHabitacionActual(DiccionarioHash team) {
        String habitacionActual, nombre;
        boolean seguir;
        Equipo aux;

        do {
            habitacionActual = JOptionPane.showInputDialog(null, "INGRESE LA HABITACION ACTUAL DEL EQUIPO: ");

            seguir = esEntero(habitacionActual);
        } while (seguir);

        nombre = JOptionPane.showInputDialog(null, "INGRESE EL NOMBRE DEL EQUIPO A MODIFICAR: ");

        aux = (Equipo) team.obtenerDato(nombre);
        if (aux != null) {
            aux.setHabitacionActual(Integer.parseInt(habitacionActual));
        } else {
            JOptionPane.showMessageDialog(null, "EQUIPO INEXISTENTE", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void modificarNombreDesafio(Diccionario d) {
        String puntaje, nombre;
        boolean seguir;
        Desafio aux;

        do {
            puntaje = JOptionPane.showInputDialog(null, "INGRESE EL PUNTAJE DEL DESAFIO A MODIFICAR: ");

            seguir = esEntero(puntaje);
        } while (seguir);
        nombre = JOptionPane.showInputDialog(null, "INGRESE EL NOMBRE DEL DESAFIO A MODIFICAR: ");

        aux = (Desafio) d.obtenerDato(puntaje);
        if (aux != null) {
            aux.setNombre(nombre);
            Archivos.escribirLog("SE MODIFICA NOMBRE DEL DESAFIO CON PUNTAJE: " + aux.getPuntaje());
        } else {
            JOptionPane.showMessageDialog(null, "DESAFIO INEXISTENTE", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void modificarNombreHabitacion(Diccionario h) {
        String codigo;
        boolean seguir;
        Habitacion aux;

        String nombre = JOptionPane.showInputDialog(null, "INGRESE NOMBRE DE LA HABITACION: ");

        do {
            codigo = JOptionPane.showInputDialog(null, "INGRESE CODIGO DE LA HABITACION A MODIFICAR: ");

            seguir = esEntero(codigo);
        } while (seguir);
        aux = (Habitacion) h.obtenerDato(Integer.parseInt(codigo));
        if (aux != null) {
            aux.setNombre(nombre);
            Archivos.escribirLog("MODIFICACION NOMBRE DE HABITACION: " + aux.getCodigo());
        } else {
            JOptionPane.showMessageDialog(null, "HABITACION INEXISTENTE", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void modificarPlantaHabitacion(Diccionario h) {
        String planta, codigo;
        boolean seguir;
        Habitacion aux;

        do {
            planta = JOptionPane.showInputDialog(null, "INGRESE LA PLANTA DE HABITACION: ");

            seguir = esEntero(planta);
        } while (seguir);
        do {
            codigo = JOptionPane.showInputDialog(null, "INGRESE CODIGO DE LA HABITACION A MODIFICAR: ");

            seguir = esEntero(codigo);
        } while (seguir);
        aux = (Habitacion) h.obtenerDato(Integer.parseInt(codigo));
        if (aux != null) {
            aux.setPlanta(Integer.parseInt(planta));
            Archivos.escribirLog("MODIFICACION NUMERO DE PLANTA DE HABITACION: " + aux.getPlanta());
        } else {
            JOptionPane.showMessageDialog(null, "HABITACION INEXISTENTE", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void modificarPuntajeAcumuladoTotalEquipo(DiccionarioHash team) {
        String puntajeAcumulado, nombre;
        boolean seguir;
        Equipo aux;

        do {
            puntajeAcumulado = JOptionPane.showInputDialog(null, "INGRESE LA PLANTA DE HABITACION: ");

            seguir = esEntero(puntajeAcumulado);
        } while (seguir);

        nombre = JOptionPane.showInputDialog(null, "INGRESE EL NOMBRE DEL EQUIPO A MODIFICAR: ");

        aux = (Equipo) team.obtenerDato(nombre);
        if (aux != null) {
            aux.setPuntajeAcumuladoTotal(Integer.parseInt(puntajeAcumulado));
            Archivos.escribirLog("SE MODIFICA PUNTAJE ACUMULADO EN EL JUEGO DE EQUIPO" + aux.getNombre());
        } else {
            JOptionPane.showMessageDialog(null, "EQUIPO INEXISTENTE", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void modificarPuntajeHabitacionActual(DiccionarioHash team) {
        String puntajeHabitacionActual, nombre;
        boolean seguir;
        Equipo aux;

        do {
            puntajeHabitacionActual = JOptionPane.showInputDialog(null, "INGRESE PUNTAJE ACUMULADO EN LA HABITACION ACTUAL: ");

            seguir = esEntero(puntajeHabitacionActual);
        } while (seguir);

        nombre = JOptionPane.showInputDialog(null, "INGRESE EL NOMBRE DEL EQUIPO A MODIFICAR: ");

        aux = (Equipo) team.obtenerDato(nombre);
        if (aux != null) {
            aux.setPuntajeEnHabitacion(Integer.parseInt(puntajeHabitacionActual));
        } else {
            JOptionPane.showMessageDialog(null, "EQUIPO INEXISTENTE", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void modificarPuntajeParaGanarEquipo(DiccionarioHash team) {
        String puntaje, nombre;
        boolean seguir;
        Equipo aux;

        do {
            puntaje = JOptionPane.showInputDialog(null, "INGRESE EL PUNTAJE TOTAL A OBTENER PARA GANAR: ");

            seguir = esEntero(puntaje);
        } while (seguir);

        nombre = JOptionPane.showInputDialog(null, "INGRESE EL NOMBRE DEL EQUIPO A MODIFICAR: ");

        aux = (Equipo) team.obtenerDato(nombre);
        if (aux != null) {
            aux.setPuntajeSalida(Integer.parseInt(puntaje));
            Archivos.escribirLog("SE MODIFICA PUNTAJE PARA GANAR DE EQUIPO" + aux.getNombre());
        } else {
            JOptionPane.showMessageDialog(null, "EQUIPO INEXISTENTE", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void modificarSalidaHabitacion(Diccionario h) {
        String codigo;
        int eleccion;
        boolean seguir,salida=false;
        Habitacion aux;
        
        do {
            codigo = JOptionPane.showInputDialog(null, "INGRESE CODIGO DE LA HABITACION A MODIFICAR: ");

            seguir = esEntero(codigo);
        } while (seguir);

        eleccion = JOptionPane.YES_NO_OPTION;
        JOptionPane.showConfirmDialog(null, "TIENE SALIDA AL EXTERIOR?", "IMPORTANTE", eleccion, 0);
        if (eleccion == JOptionPane.YES_OPTION) {
            salida = true;
        } else if (eleccion == JOptionPane.NO_OPTION) {
            salida = false;
        }
        aux = (Habitacion) h.obtenerDato(Integer.parseInt(codigo));
        if (aux != null) {
            aux.setSalida(salida);
            Archivos.escribirLog("MODIFICACION ESTADO DE PUERTA DE SALIDA DE HABITACION: " + aux.isSalida());
        } else {
            JOptionPane.showMessageDialog(null, "HABITACION INEXISTENTE", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void modificarTipoDesafio(Diccionario d) {
        String puntaje, tipo;
        boolean seguir;
        Desafio aux;

        do {
            puntaje = JOptionPane.showInputDialog(null, "INGRESE EL PUNTAJE DEL DESAFIO A MODIFICAR: ");

            seguir = esEntero(puntaje);
        } while (seguir);

        tipo = JOptionPane.showInputDialog(null, "INGRESE EL TIPO DEL DESAFIO A MODIFICAR: ");

        aux = (Desafio) d.obtenerDato(puntaje);
        if (aux != null) {
            aux.setTipo(tipo);
            Archivos.escribirLog("SE MODIFICA EL TIPO DE DESAFIO CON PUNTAJE: " + aux.getTipo());
        } else {
            JOptionPane.showMessageDialog(null, "DESAFIO INEXISTENTE", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void mostrarEquipo(DiccionarioHash equipos) {
        String nombre;
        Equipo equipo;

        nombre = JOptionPane.showInputDialog(null, "INGRESE EL NOMBRE DEL EQUIPO: ");
        equipo = (Equipo) equipos.obtenerDato(nombre);
        if (equipo != null) {
            JOptionPane.showMessageDialog(null, equipo.toString());
        } else {
            JOptionPane.showMessageDialog(null, "NO EXISTE EL EQUIPO");
        }
    }

    public static void mostrarHabitacion(Diccionario h) {
        String codigo;
        boolean seguir;
        Habitacion aux;

        do {
            codigo = JOptionPane.showInputDialog(null, "INGRESE CODIGO DE LA HABITACION: ");

            seguir = esEntero(codigo);
        } while (seguir);
        aux = (Habitacion) h.obtenerDato(Integer.parseInt(codigo));
        if (aux != null) {
            JOptionPane.showMessageDialog(null, aux.toString());
        } else {
            JOptionPane.showMessageDialog(null, "HABITACION INEXISTENTE", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void mostrarHabitacionesAdyacentes(Diccionario h, Grafo casa) {
        String codigo;
        boolean seguir;
        Habitacion aux;

        do {
            codigo = JOptionPane.showInputDialog(null, "INGRESE CODIGO DE LA HABITACION: ");

            seguir = esEntero(codigo);
        } while (seguir);
        aux = (Habitacion) h.obtenerDato(Integer.parseInt(codigo));
        if (aux != null) {
            JOptionPane.showMessageDialog(null, casa.nodosAdyacentes(aux).toString());
        } else {
            JOptionPane.showMessageDialog(null, "HABITACION INEXISTENTE", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void pasarHabitacion(Equipo equipo, Habitacion habitacion, MapeoAMuchos habitacionesPasadas, Diccionario habitaciones, Grafo casa) {
        //DADO UN EQUIPO Y UNA HABITACION PERMITE EL PASO DE LA HABITACION ACTUAL DEL EQUIPO A LA DADA POR PARAMETRO EN CASO DE QUE AMBAS HABITACIONES SEAN 
        //CONTIGUAS. SI LA HABITACION DE DESTINO YA FUE FISITADA POR EL EQUIPO PUEDE AVANZAR SIN IMPORTAR LA CANTIDAD DE PUNTOS EN LA HABITACION.
        //EN CASO DE QUE NO HAYA ANTERIORMENTE POR LA HABITACION, SE VERIFICA QUE HAYA SUPERADO LA CANTIDAD DE PUNTOS REQUERIDOS.
        //EN AMBOS CASOS SE ACTUALIZA LA CANTIDAD DE EQUIPOS EN AMBAS HABITACIONES

        Habitacion actual = (Habitacion) habitaciones.obtenerDato(equipo.getHabitacionActual());
        if (habitacionesPasadas.obtenerValores(equipo).localizar(habitacion) < 0) {
            if (casa.puedePasar(actual, habitacion, equipo.getPuntajeEnHabitacion())) {
                equipo.setHabitacionActual(habitacion.getCodigo());
                equipo.setPuntajeEnHabitacion(0);
                habitacionesPasadas.asociar(equipo, habitacion);
                actual.setCantEquipos(actual.getCantEquipos() - 1);
                habitacion.setCantEquipos(habitacion.getCantEquipos() + 1);
            } else {
                JOptionPane.showMessageDialog(null, "NO PUEDE PASAR", "ERROR", JOptionPane.WARNING_MESSAGE);
            }

        } else {
            equipo.setHabitacionActual(habitacion.getCodigo());
            equipo.setPuntajeEnHabitacion(0);
            actual.setCantEquipos(actual.getCantEquipos() - 1);
            habitacion.setCantEquipos(habitacion.getCantEquipos() + 1);
        }
    }

    public static void puedeSalir(Diccionario habitaciones, Equipo equipo) {
        //VERIFICA SI LA CANTIDAD DE PUNTAJES ACUMULADOS A LO LARGO DEL JUEGO SON MAYORES A LOS NECESARIOS PARA SALIR DE LA CASA 
        //Y PERMITE LA SALIDA EN CASO DE QUE LA HABITACION EN LA QUE ESTE TENGA PUERTA DE SALIDA

        Habitacion habitacion = (Habitacion) habitaciones.obtenerDato(equipo.getHabitacionActual());
        if (habitacion.isSalida()) {
            if (equipo.getPuntajeAcumuladoTotal() >= equipo.getPuntajeSalida()) {
                JOptionPane.showMessageDialog(null, "PUEDE SALIR DE LA CASA", "IMPORTANTE", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "NO PUEDE SALIR, CONTINUE JUGANDO PARA SUMAR MAS PUNTOS", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "LA HABITACION NO POSEE PUERTA DE SALIDA", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void salir(DiccionarioHash equipos, Diccionario habitaciones) {
        String nombre;
        Equipo equipo;

        nombre = JOptionPane.showInputDialog(null, "INGRESE EL NOMBRE DEL EQUIPO: ");

        equipo = (Equipo) equipos.obtenerDato(nombre);
        if (equipo != null) {
            puedeSalir(habitaciones, equipo);
        } else {
            JOptionPane.showMessageDialog(null, "EQUIPO INEXISTENTE", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void sinPasarPor(Diccionario h,Grafo casa) {
        String codigo, codigo2, codigo3, valorLimite;
        boolean seguir;
        Habitacion aux, aux2, aux3;

        do {
            codigo = JOptionPane.showInputDialog(null, "INGRESE CODIGO DE LA HABITACION DE ORIGEN: ");

            seguir = esEntero(codigo);
        } while (seguir);

        do {
            codigo2 = JOptionPane.showInputDialog(null, "INGRESE CODIGO DE LA HABITACION DE DESTINO: ");

            seguir = esEntero(codigo);
        } while (seguir);
        do {
            codigo3 = JOptionPane.showInputDialog(null, "INGRESE CODIGO DE LA HABITACION QUE DESEA EVITAR: ");

            seguir = esEntero(codigo);
        } while (seguir);
        do {
            valorLimite = JOptionPane.showInputDialog(null, "INGRESE EL PUNTAJE MAXIMO A OBTENER ENTRE AMBAS HABITACIONES: ");

            seguir = esEntero(codigo);
        } while (seguir);

        aux = (Habitacion) h.obtenerDato(Integer.parseInt(codigo));
        aux2 = (Habitacion) h.obtenerDato(Integer.parseInt(codigo2));
        aux3 = (Habitacion) h.obtenerDato(Integer.parseInt(codigo3));
        if (aux != null && aux2 != null && aux3 != null) {
            JOptionPane.showMessageDialog(null, "LOS POSIBLES CAMINOS ENTRE " + codigo + " Y " + codigo2 + " SON: \n" + casa.sinPasarPor(aux, aux2, aux3, Integer.parseInt(valorLimite)).toString());
        } else {
            JOptionPane.showMessageDialog(null, "UNA DE LAS HABITACIONES ES INEXISTENTE", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }
}
