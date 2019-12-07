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
public class For implements Instruccion {

    Instruccion declaracion;//variable al principio
    Instruccion asignacion;//asigna el valor a la variable
    Operacion expresion;// por ejemplo i<8, i>3, i<=2, etc.
    Operador operador;// incremento o decremento de varaible
    LinkedList<Instruccion> contenido;
    int line, column;

    /**
     * Constructor para una sentencia for
     *
     * @param declaracion int i,int j,int k, etc
     * @param asignacion i=0, i=100, etc
     * @param expresion i<7,i>10
     * @param operador i++, i--
     * @param contenido instrucciones que puedan venir dentro
     * @param line
     * @param column
     */
    public For(Instruccion declaracion, Instruccion asignacion, Operacion expresion, Operador operador, LinkedList<Instruccion> contenido, int line, int column) {
        this.declaracion = declaracion;
        this.asignacion = asignacion;
        this.expresion = expresion;
        this.operador = operador;
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
        TablaDeSimbolos tabla_local = new TablaDeSimbolos();
        tabla_local.setPadre(ts);

        declaracion.Ejecutar(tabla_local);
        asignacion.Ejecutar(tabla_local);

        while ((boolean) expresion.Ejecutar(ts)) {

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
            operador.Ejecutar(tabla_local);
        }

        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
    }

    @Override
    public Tipo getType() {
        return Tipo.FOR;
    }

}
