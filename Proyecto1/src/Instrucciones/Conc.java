/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Arbol.Arbol;
import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Conc implements Instruccion {

    String id_a;
    String id_b;
    int line, column;

    public Conc(String id_a, String id_b, int line, int column) {
        this.id_a = id_a;
        this.id_b = id_b;
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
        if (ts.existeSimbolo(id_a)) {
            if (ts.existeSimbolo(id_b)) {
                Simbolo sim = ts.getSimbolo(id_a);
                Simbolo simbolo = ts.getSimbolo(id_b);
                if (sim.getTipo().getAsignado().equals("arreglo") && simbolo.getTipo().getAsignado().equals("arreglo")) {
                    Arbol arbol = (Arbol) sim.getValor();
                    Arbol arbol2 = (Arbol) simbolo.getValor();
                    arbol.print();
                    arbol2.print();
                    Arbol resultado = new Arbol();
                    resultado.convertirString(arbol.getSalida() + arbol2.getSalida());
                    resultado.print();
                    ts.setValor(id_a, resultado);
                    return null;

                }
                Principal.add_error("Los valores no son una cadena", "Semantico", line, column);
                return null;
            }
            Principal.add_error("No existe variable" + id_b, "Semantico", line, column);
            return null;
        }
        Principal.add_error("No existe variable" + id_a, "Semantico", line, column);
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {

    }

    @Override
    public Tipo getType() {
        return Tipo.CONCATENACION;
    }

}
