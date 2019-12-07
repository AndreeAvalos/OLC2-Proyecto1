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
public class While implements Instruccion {

    Operacion expresion;
    LinkedList<Instruccion> contenido;
    int line, column;

    public While(Operacion expresion, LinkedList<Instruccion> contenido, int line, int column) {
        this.expresion = expresion;
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

        while ((boolean) expresion.Ejecutar(ts)) {
            TablaDeSimbolos tabla_local = new TablaDeSimbolos();
            tabla_local.setPadre(ts);

            for (Instruccion item : contenido) {
                switch (item.getType()) {
                    case BREAK:
                        return null;
                    case RETURN:
                        return item.Ejecutar(tabla_local);
                    default:
                        item.Ejecutar(tabla_local);
                }
            }
        }

        return null;

    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
    }

    @Override
    public Tipo getType() {
        return Tipo.WHILE;
    }
}
