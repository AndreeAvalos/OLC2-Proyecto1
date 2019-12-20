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
public class Siguiente implements Instruccion {

    public Siguiente() {
    }

    @Override
    public int getLine() {
        return 0;
    }

    @Override
    public int getColumn() {
        return 0;
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
        return Tipo.SEGUIR;
    }

}
