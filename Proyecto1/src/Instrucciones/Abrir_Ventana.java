/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Abrir_Ventana implements Instruccion {

    String id;
    int line, column;

    public Abrir_Ventana(String id, int line, int column) {
        this.id = id;
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
        String id_ventana = id + "_VENTANA";
        if (ts.existeSimbolo(id_ventana)) {
            Simbolo simbolo = ts.getSimbolo(id_ventana);
            if (simbolo.getTipo().getTipo() == Tipo.Frame) {
                Instruccion llamada = simbolo.getContenido();

                llamada.Ejecutar(ts);
            } else {
                Principal.add_error("No es un metodo de llamada de ventana", "Semantico", line, column);
            }
        } else {
            Principal.add_error("No existe metodo de ventana en "+ id , "Semantico", line, column);
        }

        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {

    }

    @Override
    public Tipo getType() {
        return Tipo.ABRIR_VENTANA;
    }

}
