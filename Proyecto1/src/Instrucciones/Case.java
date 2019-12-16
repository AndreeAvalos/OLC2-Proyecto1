/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import java.util.LinkedList;

/**
 *
 * @author Andree
 */
public class Case{
    public enum TipoCase{
        CASE,
        DEFAULT;
    }
    Operacion expresion;
    LinkedList<Instruccion> contenido;
    TipoCase tipo_case;    
    int line,column;

    public Case(Operacion expresion, LinkedList<Instruccion> contenido, int line, int column) {
        this.expresion = expresion;
        this.contenido = contenido;
        this.line = line;
        this.column = column;
        this.tipo_case = TipoCase.CASE;
    }

    public Case(LinkedList<Instruccion> contenido, int line, int column) {
        this.contenido = contenido;
        this.line = line;
        this.column = column;
        this.tipo_case = TipoCase.DEFAULT;
    }

    public Operacion getExpresion() {
        return expresion;
    }

    public LinkedList<Instruccion> getContenido() {
        return contenido;
    }

    public TipoCase getTipo_case() {
        return tipo_case;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    
    
    
    

}
