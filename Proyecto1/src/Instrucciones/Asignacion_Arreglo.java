/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Arbol.Arbol;
import Arbol.NodoArbol;
import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Asignacion_Arreglo implements Instruccion {

    String id;
    LinkedList<Object> valores;
    int line, column;

    public Asignacion_Arreglo(String id, LinkedList<Object> valores, int line, int column) {
        this.id = id;
        this.valores = valores;
        this.line = line;
        this.column = column;
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

//        NodoArbol raiz = recorrerArbol(valores, ts);
        Arbol arbol_aux = new Arbol();
        arbol_aux.recorrerArbol(id, valores, ts);

        if (!arbol_aux.valido) {
            Principal.add_error("No coinciden los tipos de datos", "Semantico", line, column);
            return null;
        }
        if (ts.existeSimbolo(id)) {
            arbol_aux.print();
            Simbolo sim = ts.getSimbolo(id);
            if (sim.getTipo_instruccion() == Tipo.ARREGLO) {
                Arbol destino = (Arbol) sim.getValor();
                if (destino.mapa.size() == arbol_aux.mapa.size()) {
                    for (int i = 0; i < arbol_aux.mapa.size(); i++) {
                        if (Objects.equals(destino.mapa.get(i), arbol_aux.mapa.get(i))) {

                        } else {
                            Principal.add_error("las dimensiones no coinciden", "Semantico", line, column);
                            return null;
                        }
                    }
                    ts.setValor(id, arbol_aux);
                } else {
                    //los valores no son iguales;
                    Principal.add_error("El tamano de los arreglos no coinciden", "Semantico", line, column);
                }

            } else {

            }

        } else {
            Principal.add_error("La varaible \'" + id + "\' no esta declarada", "Semantico", line, column);
        }
////        ArrayList<Integer> indices = new ArrayList<>();
////        indices.add(2);
////        indices.add(2);
////        if (arbol_aux.existeIndice(indices)) {
////            System.out.println(arbol_aux.get(indices));
////        } else {
////            System.out.println("No existe la ruta");
////        }
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {

    }

    @Override
    public Tipo getType() {
        return Tipo.ASIGNACION_ARREGLO;
    }

}
