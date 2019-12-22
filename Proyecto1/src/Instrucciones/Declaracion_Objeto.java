/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import Tabla_Simbolos.TipoSimbolo;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Declaracion_Objeto implements Instruccion {

    String tipo, id;
    int line, column;

    public Declaracion_Objeto(String tipo, String id, int line, int column) {
        this.tipo = tipo;
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
        if (ts.getPadre() != null) {
            if (!ts.existeSimbolo(id)) {
                ts.add(new Simbolo(new TipoSimbolo(Tipo.Struct, tipo), id, Tipo.FUSION));
                ts.setValor(id, null);
            } else {
                Principal.add_error("La variable \'" + id + "\' ya esta declarada", "Semantico", line, column);
                //aqui va el mensaje de error que ya esta declarada la variable en el ambito
            }
        }
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
        if (ts.getPadre() == null) {
            if (!ts.existeSimbolo(id)) {
                ts.add(new Simbolo(new TipoSimbolo(Tipo.Struct, tipo), id, Tipo.FUSION));
                ts.setValor(id, null);
            } else {
                Principal.add_error("La variable \'" + id + "\' ya esta declarada", "Semantico", line, column);
                //aqui va el mensaje de error que ya esta declarada la variable en el ambito
            }
        }
    }

    @Override
    public Tipo getType() {
        return Tipo.OBJETO;
    }

}
