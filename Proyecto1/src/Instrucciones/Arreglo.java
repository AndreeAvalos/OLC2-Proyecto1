/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Arbol.Arbol;
import Arbol.Celda;
import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import Tabla_Simbolos.TipoSimbolo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Arreglo implements Instruccion {

    public enum TipoArreglo {
        DEFINIR_ARREGLO,
        SIMPLE_DECLARACION,
        SIMPLE_SININDICE_VALORES,
        SIMPLE_INDICE_VALORES,
        SIMPLE_SININDICE_CADENA,
        SIMPLE_INDICE_CADENA

    }
    private Tipo tipo;
    private String id;
    Operacion indice;
    LinkedList<Operacion> valores;
    Operacion cadena;
    int line, column;
    TipoArreglo tipo_arreglo;


    /**
     * Constructor para declaracion de arreglos simples del tipo int a[1];
     *
     * @param tipo
     * @param id
     * @param indice
     * @param line
     * @param column
     */
    public Arreglo(Tipo tipo, String id, Operacion indice, int line, int column) {
        this.tipo = tipo;
        this.id = id;
        this.indice = indice;
        this.line = line;
        this.column = column;
        this.tipo_arreglo = TipoArreglo.SIMPLE_DECLARACION;
    }

    /**
     * Constructor para arreglos sin indices, pero si tienen valores del tipo
     * int a[] = {1,2}
     *
     * @param tipo
     * @param id
     * @param valores
     * @param line
     * @param column
     */
    public Arreglo(Tipo tipo, String id, LinkedList<Operacion> valores, int line, int column) {
        this.tipo = tipo;
        this.id = id;
        this.valores = valores;
        this.line = line;
        this.column = column;
        this.tipo_arreglo = TipoArreglo.SIMPLE_SININDICE_VALORES;
    }

    /**
     * Constructor para arreglo con indices y valores del tipo int a[2] = {1,2}
     *
     * @param tipo
     * @param id
     * @param indice
     * @param valores
     * @param line
     * @param column
     */
    public Arreglo(Tipo tipo, String id, Operacion indice, LinkedList<Operacion> valores, int line, int column) {
        this.id = id;
        this.tipo = tipo;
        this.indice = indice;
        this.valores = valores;
        this.line = line;
        this.column = column;
        this.tipo_arreglo = TipoArreglo.SIMPLE_INDICE_VALORES;
    }

    /**
     * Constructor para arreglos tipo cadena int a[2] = "hola"
     *
     * @param tipo
     * @param id
     * @param indice
     * @param cadena
     * @param line
     * @param column
     */
    public Arreglo(Tipo tipo, String id, Operacion indice, Operacion cadena, int line, int column) {
        this.tipo = tipo;
        this.id = id;
        this.indice = indice;
        this.cadena = cadena;
        this.line = line;
        this.column = column;
        this.tipo_arreglo = TipoArreglo.SIMPLE_INDICE_CADENA;
    }

    /**
     * Constructor para arreglos tipo cadena int a[] = "hola"
     *
     * @param tipo
     * @param id
     * @param cadena
     * @param line
     * @param column
     * @param basura
     */
    public Arreglo(Tipo tipo, String id, Operacion cadena, int line, int column, int basura) {
        this.tipo = tipo;
        this.id = id;
        this.cadena = cadena;
        this.line = line;
        this.column = column;
        this.tipo_arreglo = TipoArreglo.SIMPLE_SININDICE_CADENA;
    }

    /**
     * Constructor para el metodo definir un arreglo simple
     *
     * @param id
     * @param valores
     * @param line
     * @param column
     */
    public Arreglo(String id, LinkedList<Operacion> valores, int line, int column) {
        this.id = id;
        this.valores = valores;
        this.line = line;
        this.column = column;
        this.tipo_arreglo = TipoArreglo.DEFINIR_ARREGLO;
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
        //Simpre se declara la variable
        if (ts.getPadre() != null) {

            if (tipo_arreglo == TipoArreglo.DEFINIR_ARREGLO) {
                this.tipo = ts.getTipo(valores.get(0).Ejecutar(ts).toString());
                tipo_arreglo = TipoArreglo.SIMPLE_SININDICE_VALORES;
            }

            if (!ts.existeSimbolo(id)) {
                ts.add(new Simbolo(new TipoSimbolo(tipo, ""), id, Tipo.ARREGLO));
            } else {
                Principal.add_error("La varaible \'" + id + "\' ya esta declarada", "Semantico", line, column);
                //aqui va el mensaje de error que ya esta declarada la variable en el ambito
                return null;
            }

            ArrayList<ArrayList<Celda>> celdas = new ArrayList<>();
            ArrayList<Celda> celda = new ArrayList<>();
            String resultado = "";
            int num_indice = 0;
            Arbol new_arreglo = new Arbol();
            int contador_indices = 0;
            int num_caracteres = 0;
            char[] caracteres = {1, 2};
            switch (tipo_arreglo) {
                case SIMPLE_DECLARACION:
                    try {
                        resultado = indice.Ejecutar(ts).toString();
                        num_indice = (int) Double.parseDouble(resultado);
                        for (int i = 0; i < num_indice; i++) {
                            celda.add(new Celda(i));
                        }
                        celdas.add(celda);
                        new_arreglo.crearArbol(celdas);
                        new_arreglo.print();
//                        System.out.println("El numero de celdas es: "+new_arreglo.getSize());
//                        System.out.println("El numero de niveles es: "+new_arreglo.getNiveles());
                        ts.setValor(id, new_arreglo);

                    } catch (NumberFormatException ex) {
                        ts.eliminarSimbolo(id);
                        Principal.add_error("El valor del indice no es un entero", "Semantico", line, column);
                        return null;
                    }

                    break;

                case SIMPLE_SININDICE_VALORES:

                    for (Operacion item : valores) {
                        resultado = item.Ejecutar(ts).toString();
                        if (ts.asignValor(id, resultado)) {
                            String val = ts.castearValor(id, resultado).toString();
                            celda.add(new Celda(contador_indices, val));
                        } else {
                            ts.eliminarSimbolo(id);
                            Principal.add_error("El valor: " + resultado + " no es del mismo tipo del arreglo", "Semantico", line, column);
                            return null;

                        }
                        contador_indices++;
                    }
                    celdas.add(celda);
                    new_arreglo.crearArbol(celdas);
                    new_arreglo.print();
                    ts.setValor(id, new_arreglo);

                    break;

                case SIMPLE_INDICE_VALORES:
                    resultado = indice.Ejecutar(ts).toString();
                    try {
                        num_indice = (int) Double.parseDouble(resultado);
                        if (valores.size() == num_indice) {
                            for (Operacion item : valores) {
                                resultado = item.Ejecutar(ts).toString();
                                if (ts.asignValor(id, resultado)) {
                                    String val = ts.castearValor(id, resultado).toString();
                                    celda.add(new Celda(contador_indices, val));
                                } else {
                                    ts.eliminarSimbolo(id);
                                    Principal.add_error("El valor: " + resultado + " no es del mismo tipo del arreglo", "Semantico", line, column);
                                    return null;
                                }
                                contador_indices++;
                            }
                            celdas.add(celda);
                            new_arreglo.crearArbol(celdas);
                            new_arreglo.print();
                            ts.setValor(id, new_arreglo);

                        } else {
                            ts.eliminarSimbolo(id);
                            Principal.add_error("Indice fuera de limite", "Semantico", line, column);
                            return null;
                        }
                    } catch (Exception e) {
                        ts.eliminarSimbolo(id);
                        Principal.add_error("El valor del indice no es un entero", "Semantico", line, column);
                    }

                    break;

                case SIMPLE_INDICE_CADENA:
                    if (tipo == Tipo.Char) {
                        resultado = indice.Ejecutar(ts).toString();
                        num_indice = (int) Double.parseDouble(resultado);

                        caracteres = cadena.Ejecutar(ts).toString().replace("\"", "").toCharArray();
                        if (caracteres.length <= num_indice) {
                            for (char item : caracteres) {
                                resultado = String.valueOf(item);
                                celda.add(new Celda(contador_indices, resultado));

                                contador_indices++;
                            }
                            for (int i = contador_indices; i < num_indice; i++) {
                                celda.add(new Celda(i, null));
                            }
                            celdas.add(celda);
                            new_arreglo.crearArbol(celdas);
                            new_arreglo.print();
                            ts.setValor(id, new_arreglo);

                        } else {
                            ts.eliminarSimbolo(id);
                            Principal.add_error("Indice fuera de limite", "Semantico", line, column);
                            return null;
                        }
                    }

                    break;

                case SIMPLE_SININDICE_CADENA:
                    if (tipo == Tipo.Char) {
                        caracteres = cadena.Ejecutar(ts).toString().replace("\"", "").toCharArray();

                        for (char item : caracteres) {
                            resultado = String.valueOf(item);
                            celda.add(new Celda(contador_indices, resultado));

                            contador_indices++;
                        }
                        celdas.add(celda);
                        new_arreglo.crearArbol(celdas);
                        new_arreglo.print();
                        ts.setValor(id, new_arreglo);
                    }
                    break;
            }
        }
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
        if (ts.getPadre() == null) {

            if (tipo_arreglo == TipoArreglo.DEFINIR_ARREGLO) {
                this.tipo = ts.getTipo(valores.get(0).Ejecutar(ts).toString());
                tipo_arreglo = TipoArreglo.SIMPLE_SININDICE_VALORES;
            }

            if (!ts.existeSimbolo(id)) {
                ts.add(new Simbolo(new TipoSimbolo(tipo, ""), id, Tipo.ARREGLO));
            } else {
                Principal.add_error("La varaible \'" + id + "\' ya esta declarada", "Semantico", line, column);
                //aqui va el mensaje de error que ya esta declarada la variable en el ambito
                return;
            }

            ArrayList<ArrayList<Celda>> celdas = new ArrayList<>();
            ArrayList<Celda> celda = new ArrayList<>();
            String resultado = "";
            int num_indice = 0;
            Arbol new_arreglo = new Arbol();
            int contador_indices = 0;
            int num_caracteres = 0;
            char[] caracteres = {1, 2};
            switch (tipo_arreglo) {
                case SIMPLE_DECLARACION:
                    try {
                        resultado = indice.Ejecutar(ts).toString();
                        num_indice = (int) Double.parseDouble(resultado);
                        for (int i = 0; i < num_indice; i++) {
                            celda.add(new Celda(i));
                        }
                        celdas.add(celda);
                        new_arreglo.crearArbol(celdas);
                        new_arreglo.print();
//                        System.out.println("El numero de celdas es: "+new_arreglo.getSize());
//                        System.out.println("El numero de niveles es: "+new_arreglo.getNiveles());
                        ts.setValor(id, new_arreglo);

                    } catch (NumberFormatException ex) {
                        ts.eliminarSimbolo(id);
                        Principal.add_error("El valor del indice no es un entero", "Semantico", line, column);
                    }

                    break;

                case SIMPLE_SININDICE_VALORES:

                    for (Operacion item : valores) {
                        resultado = item.Ejecutar(ts).toString();
                        if (ts.asignValor(id, resultado)) {
                            String val = ts.castearValor(id, resultado).toString();
                            celda.add(new Celda(contador_indices, val));
                        } else {
                            ts.eliminarSimbolo(id);
                            Principal.add_error("El valor: " + resultado + " no es del mismo tipo del arreglo", "Semantico", line, column);

                        }
                        contador_indices++;
                    }
                    celdas.add(celda);
                    new_arreglo.crearArbol(celdas);
                    new_arreglo.print();
                    ts.setValor(id, new_arreglo);

                    break;

                case SIMPLE_INDICE_VALORES:
                    resultado = indice.Ejecutar(ts).toString();
                    try {
                        num_indice = (int) Double.parseDouble(resultado);
                        if (valores.size() == num_indice) {
                            for (Operacion item : valores) {
                                resultado = item.Ejecutar(ts).toString();
                                if (ts.asignValor(id, resultado)) {
                                    String val = ts.castearValor(id, resultado).toString();
                                    celda.add(new Celda(contador_indices, val));
                                } else {
                                    ts.eliminarSimbolo(id);
                                    Principal.add_error("El valor: " + resultado + " no es del mismo tipo del arreglo", "Semantico", line, column);

                                }
                                contador_indices++;
                            }
                            celdas.add(celda);
                            new_arreglo.crearArbol(celdas);
                            new_arreglo.print();
                            ts.setValor(id, new_arreglo);

                        } else {
                            ts.eliminarSimbolo(id);
                            Principal.add_error("Indice fuera de limite", "Semantico", line, column);
                        }
                    } catch (Exception e) {
                        ts.eliminarSimbolo(id);
                        Principal.add_error("El valor del indice no es un entero", "Semantico", line, column);
                    }

                    break;

                case SIMPLE_INDICE_CADENA:
                    if (tipo == Tipo.Char) {
                        resultado = indice.Ejecutar(ts).toString();
                        num_indice = (int) Double.parseDouble(resultado);

                        caracteres = cadena.Ejecutar(ts).toString().replace("\"", "").toCharArray();
                        if (caracteres.length <= num_indice) {
                            for (char item : caracteres) {
                                resultado = String.valueOf(item);
                                celda.add(new Celda(contador_indices, resultado));

                                contador_indices++;
                            }
                            for (int i = contador_indices; i < num_indice; i++) {
                                celda.add(new Celda(i, null));
                            }
                            celdas.add(celda);
                            new_arreglo.crearArbol(celdas);
                            new_arreglo.print();
                            ts.setValor(id, new_arreglo);

                        } else {
                            ts.eliminarSimbolo(id);
                            Principal.add_error("Indice fuera de limite", "Semantico", line, column);
                        }
                    }

                    break;

                case SIMPLE_SININDICE_CADENA:
                    if (tipo == Tipo.Char) {
                        caracteres = cadena.Ejecutar(ts).toString().replace("\"", "").toCharArray();

                        for (char item : caracteres) {
                            resultado = String.valueOf(item);
                            celda.add(new Celda(contador_indices, resultado));

                            contador_indices++;
                        }
                        celdas.add(celda);
                        new_arreglo.crearArbol(celdas);
                        new_arreglo.print();
                        ts.setValor(id, new_arreglo);
                    }
                    break;
            }
        }

    }

    @Override
    public Tipo getType() {
        return Tipo.ARREGLO_SIMPLE_DECLARACION;
    }

}
