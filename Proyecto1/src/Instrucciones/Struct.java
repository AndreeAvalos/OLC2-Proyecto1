/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.TablaDeSimbolos;
import java.util.LinkedList;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Struct implements Instruccion {

    String id;
    LinkedList<Instruccion> declaraciones;
    int line, column;

    public Struct(String id, LinkedList<Instruccion> declaraciones, int line, int column) {
        this.id = id;
        this.declaraciones = declaraciones;
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
        //declaramos el tipo 
        if (!Principal.exist_struct(id)) {
            Principal.add_struct(id, new TablaDeSimbolos());
        } else {
            Principal.add_error("La varaible \'" + id + "\' ya esta declarada", "Semantico", line, column);
            return;
        }
        TablaDeSimbolos definiciones = new TablaDeSimbolos();
        declaraciones.forEach((item) -> {
            item.Recolectar(definiciones);
        });
        Principal.replace_struct(id, definiciones);
    }

    @Override
    public Tipo getType() {
        return Tipo.DEF_STRUCT;
    }

}
