/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.TablaDeSimbolos;
import java.util.LinkedList;

/**
 *
 * @author Andree
 */
public class Sentencia_IF implements Instruccion {

    public enum TipoIf {
        TIPO_ELSE_IF,
        TIPO_ELSE;
    }

    Operacion expresion;
    LinkedList<Instruccion> sentencias;
    TipoIf tipo_sentencia;
    int line, column;

    /**
     * Constructor para else if
     *
     * @param expresion
     * @param sentencias
     * @param line
     * @param column
     */
    public Sentencia_IF(Operacion expresion, LinkedList<Instruccion> sentencias, int line, int column) {
        this.expresion = expresion;
        this.sentencias = sentencias;
        this.line = line;
        this.column = column;
        this.tipo_sentencia = TipoIf.TIPO_ELSE_IF;
    }

    public Sentencia_IF(LinkedList<Instruccion> sentencias, int line, int column) {
        this.sentencias = sentencias;
        this.line = line;
        this.column = column;
        this.tipo_sentencia = TipoIf.TIPO_ELSE;
    }

    public Sentencia_IF() {
        this.sentencias = null;
        this.tipo_sentencia = TipoIf.TIPO_ELSE;
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
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {

    }

    @Override
    public Tipo getType() {

        return Tipo.ELSE_IF;
    }
}
