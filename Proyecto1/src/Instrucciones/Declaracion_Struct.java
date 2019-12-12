/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import Tabla_Simbolos.TipoSimbolo;
import java.util.LinkedList;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Declaracion_Struct implements Instruccion {

    String id;
    TablaDeSimbolos definiciones;
    LinkedList<Instruccion> declaraciones;
    int line, column;

    public Declaracion_Struct(String id, LinkedList<Instruccion> declaraciones, int line, int column) {
        this.id = id;
        this.declaraciones = declaraciones;
        this.line = line;
        this.column = column;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object Ejecutar(TablaDeSimbolos ts) {
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
        definiciones = new TablaDeSimbolos();
        if (ts.getPadre() == null) {
            if (!Principal.exist_struct(id)) {
                declaraciones.forEach((item) -> {
                    item.Recolectar(definiciones);
                });
                Principal.add_struct(id, definiciones);
            } else {
                Principal.add_error("La varaible \'" + id + "\' ya esta declarada", "Semantico", line, column);
            }
        }
    }

    @Override
    public Tipo getType() {
        return Tipo.FUSION;
    }

}
