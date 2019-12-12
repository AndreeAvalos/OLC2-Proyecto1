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
public class Asignacion_Arreglo implements Instruccion {

    String id;
    LinkedList<Operacion> indices;
    Operacion valor;
    int line, column;

    public Asignacion_Arreglo(String id, LinkedList<Operacion> indices, Operacion valor, int line, int column) {
        this.id = id;
        this.indices = indices;
        this.valor = valor;
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
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {

    }

    @Override
    public Tipo getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
