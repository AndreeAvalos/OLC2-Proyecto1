/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Arbol.Arbol;
import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import Tabla_Simbolos.TipoSimbolo;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Arreglo implements Instruccion {

    public enum TipoArreglo {
        DEFINICION,
        DECLARACION,
        SININDICE_VALORES,
        INDICE_VALORES,
        SININDICE_OPERACION,
        INDICE_OPERACION

    }

    Tipo tipo_dato;
    String id;
    ArrayList<Integer> num_dimensiones;
    LinkedList<Operacion> dimensiones;
    LinkedList<Object> valores;
    Operacion valor;
    TipoArreglo tipo_arreglo;
    int line, column;

    /**
     * Constructor para solo declaracion
     *
     * @param tipo_dato
     * @param id
     * @param dimensiones
     * @param line
     * @param column
     */
    public Arreglo(Tipo tipo_dato, String id, LinkedList<Operacion> dimensiones, int line, int column) {
        this.tipo_dato = tipo_dato;
        this.id = id;
        this.dimensiones = dimensiones;
        tipo_arreglo = TipoArreglo.DECLARACION;
        this.line = line;
        this.column = column;
    }

    public Arreglo(Tipo tipo_dato, String id, ArrayList<Integer> num_dimensiones, LinkedList<Object> valores, int line, int column) {
        this.tipo_dato = tipo_dato;
        this.id = id;
        this.num_dimensiones = num_dimensiones;
        this.valores = valores;
        this.line = line;
        this.column = column;
        tipo_arreglo = TipoArreglo.SININDICE_VALORES;
    }

    public Arreglo(Tipo tipo_dato, String id, LinkedList<Operacion> dimensiones, LinkedList<Object> valores, int line, int column) {
        this.tipo_dato = tipo_dato;
        this.id = id;
        this.dimensiones = dimensiones;
        this.valores = valores;
        this.line = line;
        this.column = column;
        tipo_arreglo = TipoArreglo.INDICE_VALORES;
    }

    public Arreglo(String id, LinkedList<Object> valores, int line, int column) {
        this.id = id;
        this.valores = valores;
        this.line = line;
        this.column = column;
        tipo_arreglo = TipoArreglo.DEFINICION;
    }

    public Arreglo(Tipo tipo_dato, String id, ArrayList<Integer> num_dimensiones, Operacion valor, int line, int column) {
        this.tipo_dato = tipo_dato;
        this.id = id;
        this.num_dimensiones = num_dimensiones;
        this.valor = valor;
        this.line = line;
        this.column = column;
        tipo_arreglo = TipoArreglo.SININDICE_OPERACION;
    }

    public Arreglo(Tipo tipo_dato, String id, LinkedList<Operacion> dimensiones, Operacion valor, int line, int column) {
        this.tipo_dato = tipo_dato;
        this.id = id;
        this.dimensiones = dimensiones;
        this.valor = valor;
        this.line = line;
        this.column = column;
        tipo_arreglo = TipoArreglo.INDICE_OPERACION;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public Object Ejecutar(TablaDeSimbolos ts) {
        if (ts.getPadre() != null) {
            if (tipo_arreglo == TipoArreglo.DEFINICION) {
                Arbol arbol_aux = new Arbol();
                arbol_aux.recorrerArbol2(valores, ts);
                this.tipo_dato = arbol_aux.tipo_dato;
            }
            if (!ts.existeSimbolo(id)) {
                ts.add(new Simbolo(new TipoSimbolo(tipo_dato, "arreglo"), id, Tipo.ARREGLO));
            } else {
                Principal.add_error("La varaible \'" + id + "\' ya esta declarada", "Semantico", line, column);
                //aqui va el mensaje de error que ya esta declarada la variable en el ambito
                return null;
            }

            if (null != tipo_arreglo) {
                switch (tipo_arreglo) {
                    case DECLARACION:
                        Declaracion(ts);
                        break;
                    case SININDICE_VALORES:
                        No_indice_Valores(ts);
                        break;
                    case INDICE_VALORES:
                        Indice_valores(ts);
                        break;
                    case DEFINICION:
                        Deficion(ts);
                        break;
                    case SININDICE_OPERACION:
                        No_indice_Operacion(ts);
                        break;
                    case INDICE_OPERACION:
                        Indice_Operacion(ts);
                    default:
                        break;
                }
            }
        }
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
        if (ts.getPadre() == null) {
            if (tipo_arreglo == TipoArreglo.DEFINICION) {
                Arbol arbol_aux = new Arbol();
                arbol_aux.recorrerArbol2(valores, ts);
                this.tipo_dato = arbol_aux.tipo_dato;
            }
            if (!ts.existeSimbolo(id)) {
                ts.add(new Simbolo(new TipoSimbolo(tipo_dato, "arreglo"), id, Tipo.ARREGLO));
            } else {
                Principal.add_error("La variable \'" + id + "\' ya esta declarada", "Semantico", line, column);
                //aqui va el mensaje de error que ya esta declarada la variable en el ambito
                return;
            }

            if (null != tipo_arreglo) {
                switch (tipo_arreglo) {
                    case DECLARACION:
                        Declaracion(ts);
                        break;
                    case SININDICE_VALORES:
                        No_indice_Valores(ts);
                        break;
                    case INDICE_VALORES:
                        Indice_valores(ts);
                        break;
                    case DEFINICION:
                        Deficion(ts);
                        break;
                    case SININDICE_OPERACION:
                        No_indice_Operacion(ts);
                        break;
                    case INDICE_OPERACION:
                        Indice_Operacion(ts);
                    default:
                        break;
                }
            }
        }
    }

    private void Deficion(TablaDeSimbolos ts) {
        Arbol arbol_aux = new Arbol();
        arbol_aux.recorrerArbol(id, valores, ts);
        arbol_aux.print();
        ts.setValor(id, arbol_aux);

    }

    private void Declaracion(TablaDeSimbolos ts) {
        Arbol arbol_declaracion = new Arbol();
        ArrayList<Integer> numero_dimensiones = new ArrayList<>();
        try {
            for (Operacion item : dimensiones) {
                String result = item.Ejecutar(ts).toString();
                int indice = (int) Double.parseDouble(result);
                numero_dimensiones.add(indice);
            }
            arbol_declaracion.crearArbol(numero_dimensiones);
            arbol_declaracion.print();
            ts.setValor(id, arbol_declaracion);
        } catch (Exception e) {
            Principal.add_error("El indice no es un numero entero", "Semantico", line, column);

        }
    }

    private void No_indice_Valores(TablaDeSimbolos ts) {
        Arbol arbol_declaracion = new Arbol();
        arbol_declaracion.crearArbol(num_dimensiones);

        Arbol arbol_aux = new Arbol();
        arbol_aux.recorrerArbol(id, valores, ts);
        arbol_aux.print();

        if (arbol_aux.mapa.size() == arbol_declaracion.mapa.size()) {
            ts.setValor(id, arbol_aux);
        } else {
            Principal.add_error("El tamano de los arreglos no coinciden", "Semantico", line, column);
        }
    }

    private void Indice_valores(TablaDeSimbolos ts) {
        Arbol arbol_declaracion = new Arbol();
        ArrayList<Integer> numero_dimensiones = new ArrayList<>();
        try {
            for (Operacion item : dimensiones) {
                String result = item.Ejecutar(ts).toString();
                int indice = (int) Double.parseDouble(result);
                numero_dimensiones.add(indice);
            }
            Arbol arbol_aux = new Arbol();
            arbol_aux.recorrerArbol(id, valores, ts);
            arbol_declaracion.crearArbol(numero_dimensiones);

            if (arbol_declaracion.mapa.size() == arbol_aux.mapa.size()) {
                for (int i = 0; i < arbol_aux.mapa.size(); i++) {
                    if (Objects.equals(arbol_declaracion.mapa.get(i), arbol_aux.mapa.get(i))) {

                    } else {
                        Principal.add_error("las dimensiones no coinciden", "Semantico", line, column);
                        return;
                    }
                }
                arbol_aux.print();
                ts.setValor(id, arbol_aux);
            } else {
                //los valores no son iguales;
                Principal.add_error("El tamano de los arreglos no coinciden", "Semantico", line, column);
            }

        } catch (Exception e) {
            Principal.add_error("El indice no es un numero entero", "Semantico", line, column);

        }
    }

    private void No_indice_Operacion(TablaDeSimbolos ts) {
        try {
            Arbol resultado = (Arbol) valor.Ejecutar(ts);
            resultado.print();
            if (resultado.mapa.size() == num_dimensiones.size()) {
                ts.setValor(id, resultado);
            } else {
                Principal.add_error("No es posible pasar el valor", "Semantico", line, column);

            }

        } catch (Exception e) {
            Principal.add_error("No es un tipo valido para un arreglo", "Semantico", line, column);
        }
    }

    private void Indice_Operacion(TablaDeSimbolos ts) {
        Arbol arbol_declaracion = new Arbol();
        ArrayList<Integer> numero_dimensiones = new ArrayList<>();
        try {
            for (Operacion item : dimensiones) {
                String result = item.Ejecutar(ts).toString();
                int indice = (int) Double.parseDouble(result);
                numero_dimensiones.add(indice);
            }
            arbol_declaracion.crearArbol(numero_dimensiones);

            try {
                Arbol resultado = (Arbol) valor.Ejecutar(ts);
                resultado.print();
                if (resultado.mapa.size() == 1 && arbol_declaracion.mapa.size() == 1) {
                    if (resultado.mapa.get(0) <= arbol_declaracion.mapa.get(0)) {
                        ts.setValor(id, resultado);
                        return;
                    }
                } else {
                    if (resultado.mapa.size() == arbol_declaracion.mapa.size()) {
                        for (int i = 0; i < resultado.mapa.size(); i++) {
                            if (arbol_declaracion.mapa.get(i) >= resultado.mapa.get(i)) {

                            } else {
                                Principal.add_error("las dimensiones no coinciden", "Semantico", line, column);
                                return;
                            }
                        }
                        ts.setValor(id, resultado);
                    }
                }

            } catch (Exception e) {
                Principal.add_error("No es un tipo valido para un arreglo", "Semantico", line, column);
            }

        } catch (Exception e) {
            Principal.add_error("El indice no es un numero entero", "Semantico", line, column);

        }
    }

    @Override
    public Tipo getType() {
        return Tipo.ARREGLO_SIMPLE_DECLARACION;
    }

}
