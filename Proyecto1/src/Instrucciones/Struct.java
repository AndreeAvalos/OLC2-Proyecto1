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
        if (ts.getPadre() == null) {
            if (!ts.existeSimbolo(id)) {
                ts.add(new Simbolo(new TipoSimbolo(Tipo.Struct, id), id, Tipo.OBJETO));

            } else {
                Principal.add_error("La varaible \'" + id + "\' ya esta declarada", "Semantico", line, column);
                //aqui va el mensaje de error que ya esta declarada la variable en el ambito
                return;
            }

            TablaDeSimbolos definiciones = new TablaDeSimbolos();
            declaraciones.forEach((item) -> {
                item.Recolectar(definiciones);
            });
            ts.replace(id, definiciones);
        }
    }

    @Override
    public Tipo getType() {
        return Tipo.FUSION;
    }

}
