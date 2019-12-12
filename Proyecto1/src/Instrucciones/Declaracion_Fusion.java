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
public class Declaracion_Fusion implements Instruccion {

    String tipo;
    String id;
    Operacion valor;
    int line, column;

    public Declaracion_Fusion(String tipo, String id, int line, int column) {
        this.tipo = tipo;
        this.id = id;
        this.line = line;
        this.column = column;
    }

    public Declaracion_Fusion(String tipo, String id, Operacion valor, int line, int column) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
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
        if (ts.getPadre() == null) {
            if (Principal.exist_struct(tipo)) {
                TablaDeSimbolos local = Principal.get_struct(tipo);
                if (!ts.existeSimbolo(id)) {
                    ts.add(new Simbolo(new TipoSimbolo(Tipo.Struct, ""), id, Tipo.FUSION));
                    ts.setValor(id, local);
                } else {
                    Principal.add_error("La varaible \'" + id + "\' ya esta declarada", "Semantico",line,column);
                    //aqui va el mensaje de error que ya esta declarada la variable en el ambito
                }

            } else {
                Principal.add_error("No existe el tipo: " + tipo, "Semantico",line,column);
            }
        }
    }

    @Override
    public Tipo getType() {
        return Tipo.DECLARACION_FUSION;
    }

}
