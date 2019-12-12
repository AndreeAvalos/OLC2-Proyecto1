/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.TablaDeSimbolos;
import java.util.ArrayList;

/**
 *
 * @author Andree
 */
public class Asignacion implements Instruccion {

    String id;
    Operacion index;
    Operacion valor;
    int line, column;

    /**
     *
     * @param id
     * @param valor
     * @param line
     * @param column
     */
    public Asignacion(String id, Operacion valor, int line, int column) {
        this.id = id;
        this.valor = valor;
        this.line = line;
        this.column = column;
    }

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public int getColumn() {
        return this.column;
    }

    @Override
    public Object Ejecutar(TablaDeSimbolos ts) {
        Object resultado = valor.Ejecutar(ts);
        if (ts.existeSimbolo(id)) {
            if (ts.asignValor(id, resultado)) {
                ts.setValor(id, resultado);
                if (ts.existReferencia(id)) {
                    ArrayList<String> lst = ts.getListaReferencia(id);
                    if (lst.size() > 1) {
                        lst.forEach((item) -> {
                            ts.setValor(item, resultado);
                        });
                    }
                }
                return true;
            } else {
                //error porque no se puede hacer el casteo explicito
                System.out.println("No es posible hacer el casteo ");
                return false;
            }
        } else {
            System.out.println("No existe la variable: " + id);
            return false;
        }
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
        //Se dejo en blanco con intencion
    }

    @Override
    public Tipo getType() {
        return Tipo.ASIGNACION;
    }

}
