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
public class Imprimir implements Instruccion {

    Instruccion contenido;
    int line, column;

    public Imprimir(Instruccion contenido) {
        this.contenido = contenido;
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
        String salida = contenido.Ejecutar(ts).toString();
        System.out.println(salida);
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
        //Se dejo vacio con intencion
    }
    @Override
    public Tipo getType() {
        return Tipo.IMPRIMIR;
    }
}
