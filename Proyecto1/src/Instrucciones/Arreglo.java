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
import java.util.LinkedList;

/**
 *
 * @author Andree
 */
public class Arreglo implements Instruccion {

    public enum TipoArreglo {
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
            if (!ts.existeSimbolo(id)) {
                ts.add(new Simbolo(new TipoSimbolo(tipo, tipo), id, Tipo.ARREGLO));
            } else {
                System.out.println("YA SE DECLARO LA VARIABLE");
                return null;
                //aqui va el mensaje de error que ya esta declarada la variable en el ambito
            }

            ArrayList<ArrayList<Celda>> celdas = new ArrayList<>();
            ArrayList<Celda> celda = new ArrayList<>();
            String resultado = "";
            int num_indice = 0;
            Arbol new_arreglo = new Arbol();
            int contador_indices = 0;
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
                        ts.setValor(id, new_arreglo);

                    } catch (Exception ex) {
                        System.out.println("El valor no es un entero");
                        return null;
                    }
                case SIMPLE_SININDICE_VALORES:
                    if (tipo == Tipo.Char) {
                        for (Operacion item : valores) {
                            resultado = item.Ejecutar(ts).toString();
                            if (ts.asignValor(id, resultado)) {
                                celda.add(new Celda(contador_indices, resultado));
                            } else {
                                System.out.println("El valor: " + resultado + " no es del mismo tipo del arreglo");
                                return null;
                            }
                            contador_indices++;
                        }
                        celdas.add(celda);
                        new_arreglo.crearArbol(celdas);
                        new_arreglo.print();
                        ts.setValor(id, new_arreglo);
                    } else {
                        System.out.println("NO ES UNA CADENA");
                    }
            }
        }

        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
        boolean declarada = false;
        if (ts.getPadre() == null) {
            if (!ts.existeSimbolo(id)) {
                ts.add(new Simbolo(new TipoSimbolo(tipo, tipo), id, Tipo.ARREGLO));
                declarada = true;
            } else {
                System.out.println("La varaible \'" + id + "\' ya esta declarada");
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
                        System.out.println("");
                        ts.setValor(id, new_arreglo);

                    } catch (Exception ex) {
                        ts.eliminarSimbolo(id);
                        System.out.println("El valor del indice no es un entero");
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
                            System.out.println("El valor: " + resultado + " no es del mismo tipo del arreglo");

                        }
                        contador_indices++;
                    }
                    celdas.add(celda);
                    new_arreglo.crearArbol(celdas);
                    new_arreglo.print();
                    System.out.println("");
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
                                    System.out.println("El valor: " + resultado + " no es del mismo tipo del arreglo");

                                }
                                contador_indices++;
                            }
                            celdas.add(celda);
                            new_arreglo.crearArbol(celdas);
                            new_arreglo.print();
                            System.out.println("");
                            ts.setValor(id, new_arreglo);

                        } else {
                            ts.eliminarSimbolo(id);
                            System.out.println("Indice fuera de limite");
                        }
                    } catch (Exception e) {
                        ts.eliminarSimbolo(id);
                        System.out.println("El valor del indice no es un entero");
                    }

                    break;

                case SIMPLE_INDICE_CADENA:
                    if (tipo == Tipo.Char) {
                        resultado = indice.Ejecutar(ts).toString();
                        num_indice = (int) Double.parseDouble(resultado);

                        caracteres = cadena.Ejecutar(ts).toString().replace("\"", "").toCharArray();
                        if (caracteres.length == num_indice) {
                            for (char item : caracteres) {
                                resultado = String.valueOf(item);
                                celda.add(new Celda(contador_indices, resultado));

                                contador_indices++;
                            }
                            celdas.add(celda);
                            new_arreglo.crearArbol(celdas);
                            new_arreglo.print();
                            System.out.println("");
                            ts.setValor(id, new_arreglo);

                        } else {
                            ts.eliminarSimbolo(id);
                            System.out.println("Indice fuera de limite");
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
                        System.out.println("");
                        ts.setValor(id, new_arreglo);
                    }
                    break;
            }
        }

    }

    @Override
    public Tipo getType() {
        return Tipo.ARREGLO;
    }

}