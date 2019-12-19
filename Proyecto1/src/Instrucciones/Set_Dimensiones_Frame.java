/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.TablaDeSimbolos;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Set_Dimensiones_Frame implements Instruccion {

    Operacion alto;
    Operacion ancho;
    int line, column;

    public Set_Dimensiones_Frame(Operacion ancho, Operacion alto, int line, int column) {
        this.alto = alto;
        this.ancho = ancho;
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
        try {
            int x = (int) Double.parseDouble(ancho.Ejecutar(ts).toString());
            int y = (int) Double.parseDouble(alto.Ejecutar(ts).toString());
            Principal.frame.setBounds(0, 0, x, y);
            return null;
        } catch (Exception e) {
            Principal.add_error("Los parametros no son un numero entero", "Semantico", line, column);
            return null;
        }

    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {

    }

    @Override
    public Tipo getType() {
        return Tipo.ANCHO_ALTO;
    }

}
