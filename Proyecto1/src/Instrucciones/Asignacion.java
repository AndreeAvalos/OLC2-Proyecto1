/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.TablaDeSimbolos;
import java.util.ArrayList;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Asignacion implements Instruccion {

    String id;
    Operacion index;
    Operacion valor;
    int line, column;
    TablaDeSimbolos local = new TablaDeSimbolos();
    TablaDeSimbolos local_2 = new TablaDeSimbolos();

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
            Object aux = resultado;
            if (ts.asignValor(id, resultado)) {
                ts.setValor(id, aux);
                local = ts.getEntorno(id);
                if (ts.equals(ts)) {
                    local = ts;
                }
                if (local.existReferencia(id)) {
                    ArrayList<String> lst = local.getListaReferencia(id);
                    if (lst.size() > 1) {
                        lst.forEach((item) -> {
                            local.setValor(item, resultado);
                        });
                    }
                }

                return true;
            } else {
                //error porque no se puede hacer el casteo explicito
                Principal.add_error("No es posible hacer el casteo ", "Semantico", line, column);
                return false;
            }
        } else {
            Principal.add_error("No existe la variable: " + id, "Semantico", line, column);
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
