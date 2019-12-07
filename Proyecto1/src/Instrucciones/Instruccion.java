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
public interface Instruccion {
    public int getLine();
    public int getColumn();
    public Object Ejecutar(TablaDeSimbolos ts);
    public void Recolectar(TablaDeSimbolos ts); 
    public Tipo getType();
}
