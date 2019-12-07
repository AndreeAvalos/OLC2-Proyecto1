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
public class Return implements Instruccion {

    Instruccion contenido;
    int line, column;

    public Return(Instruccion contenido, int line, int column) {
        this.contenido = contenido;
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
        return contenido.Ejecutar(ts);
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
    }

    @Override
    public Tipo getType() {
        return Tipo.RETURN;
    }

}
