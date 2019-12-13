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
    String tipo2;
    Operacion valor;
    int line, column;
    boolean reservar_memoria = false;

    public Declaracion_Fusion(String tipo, String id, int line, int column) {
        this.tipo = tipo;
        this.id = id;
        this.line = line;
        this.column = column;
    }

    public Declaracion_Fusion(String tipo, String id, String tipo2, int line, int column) {
        this.tipo = tipo;
        this.id = id;
        this.tipo2 = tipo2;
        this.line = line;
        this.column = column;
        this.reservar_memoria = true;
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

        if (reservar_memoria == true) {
            if (!tipo.equals(tipo2)) {
                Principal.add_error("El tipo: " + tipo2 + " con la fusion: " + tipo2, tipo, line, column);
                return;
            }
            Principal.on_struck = true;
        }

        if (ts.getPadre() == null) {
            if (Principal.exist_struct(tipo)) {
                TablaDeSimbolos local = Principal.get_struct(tipo);
                if (!ts.existeSimbolo(id)) {
                    ts.add(new Simbolo(new TipoSimbolo(Tipo.Struct, tipo), id, Tipo.FUSION));
                    if (Principal.on_struck == true) {
                        ts.setValor(id, local);
                    }
                } else {
                    Principal.add_error("La varaible \'" + id + "\' ya esta declarada", "Semantico", line, column);
                    //aqui va el mensaje de error que ya esta declarada la variable en el ambito
                }

            } else {
                Principal.add_error("No existe el tipo: " + tipo, "Semantico", line, column);
            }
        }
    }

    @Override
    public Tipo getType() {
        return Tipo.DECLARACION_FUSION;
    }

}
