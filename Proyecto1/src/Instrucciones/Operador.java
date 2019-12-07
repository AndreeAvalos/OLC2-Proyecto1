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
public class Operador implements Instruccion {

    String id;
    Tipo tipo;
    int line, column;

    public Operador(String id, Tipo tipo, int line, int column) {
        this.id = id;
        this.tipo = tipo;
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
        if (tipo == Tipo.Incremento) {
            ts.incrementarValor(id);
        } else if (tipo == Tipo.Decremento) {
            ts.decrementarValor(id);
        }else{
        //error jejeje
        }
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
    }

    @Override
    public Tipo getType() {
        return Tipo.OPERADOR;
    }

}
