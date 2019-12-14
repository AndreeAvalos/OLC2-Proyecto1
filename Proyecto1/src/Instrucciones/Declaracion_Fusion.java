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

    String tipo, id, tipo2;
    int line, column;

    public Declaracion_Fusion(String tipo, String id, String tipo2, int line, int column) {
        this.tipo = tipo;
        this.id = id;
        this.tipo2 = tipo2;
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
            if (!tipo.equals(tipo2)) {
                Principal.add_error("El tipo: " + tipo2 + " con la fusion: " + tipo2, "Semantico", line, column);
                return;
            }
            if (ts.getPadre() == null) {
                if (ts.existeSimbolo(tipo)) {
                    if (!ts.existeSimbolo(id)) {
                        TablaDeSimbolos local = ts.get_struct(tipo);

                        TablaDeSimbolos nueva = new TablaDeSimbolos();

                        for (Simbolo item : local) {
                            Simbolo new_simbol = new Simbolo("", ")");
                            nueva.add(new_simbol.copy(item));
                        }
                        nueva.nombre = id;
                        ts.add(new Simbolo(new TipoSimbolo(Tipo.Struct, tipo), id, Tipo.FUSION));
                        ts.setValor(id, nueva);
                    } else {
                        Principal.add_error("La varaible \'" + id + "\' ya esta declarada", "Semantico", line, column);
                    }
                } else {
                    Principal.add_error("No existe el tipo: " + tipo, "Semantico", line, column);
                }

            }
        }

    }

    @Override
    public Tipo getType() {
        return Tipo.DECLARACION_FUSION;
    }

}
