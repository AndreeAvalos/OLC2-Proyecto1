/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.TablaDeSimbolos;

/**
 *
 * @author Andree
 */
public class Asignacion implements Instruccion {

    String id;
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
        String resultado = valor.Ejecutar(ts).toString();
        if (ts.asignValor(id, resultado)) {
            ts.setValor(id, resultado);
        } else {
            //error porque no se puede hacer el casteo explicito
        }
        return null;
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
