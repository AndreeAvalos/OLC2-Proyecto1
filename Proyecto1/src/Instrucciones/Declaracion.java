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
public class Declaracion implements Instruccion {

    Tipo tipo_simbolo;//numero,cadena,identificador etc
    Tipo tipo_declarado;//entero,decimal,caracter etc
    String id;//nombre de variable;
    int line, column;// linea y columna de token 
    Instruccion asignacion;

    /**
     *
     * @param tipo_simbolo
     * @param tipo_declarado
     * @param id
     * @param linea
     * @param columna
     */
    public Declaracion(Tipo tipo_simbolo, Tipo tipo_declarado, String id, int linea, int columna) {
        this.tipo_simbolo = tipo_simbolo;
        this.tipo_declarado = tipo_declarado;
        this.id = id;
        this.line = linea;
        this.column = columna;
        this.asignacion = null;
    }

    public Declaracion(Tipo tipo_simbolo, Tipo tipo_declarado, String id, Instruccion asignacion, int line, int column) {
        this.tipo_simbolo = tipo_simbolo;
        this.tipo_declarado = tipo_declarado;
        this.id = id;
        this.line = line;
        this.column = column;
        this.asignacion = asignacion;
    }

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public int getColumn() {
        return this.column;
    }

    /**
     * Metodo implementado de instruccion para declarar variables del ambito
     * local
     *
     * @param ts tabla de simbolos local
     * @return no retornamos nada, ya que no genera valor
     */
    @Override
    public Object Ejecutar(TablaDeSimbolos ts) {

        if (ts.getPadre() != null) {
            
            if (!ts.existeSimboloAmbienteActual(id)) {
                ts.add(new Simbolo(new TipoSimbolo(tipo_simbolo, ""), id, Tipo.VARIABLE));

            } else {
                Principal.add_error("La varaible \'" + id + "\' ya esta declarada","Semantico",line,column);
                //aqui va el mensaje de error que ya esta declarada la variable en el ambito
                return null;
            }
        }

        if (asignacion != null) {
            boolean pass = (boolean) asignacion.Ejecutar(ts);
            if (pass) {
                // si se asigno el valor;
            } else {
                ts.eliminarSimbolo(id);
            }
        }

        return null;
    }

    /**
     * Metodo implementado de instruccion que sirve para recolectar variables
     * del ambito global
     *
     * @param ts tabla de simbolos global
     */
    @Override
    public void Recolectar(TablaDeSimbolos ts) {

        if (ts.getPadre() == null) {
            if (!ts.existeSimbolo(id)) {
                ts.add(new Simbolo(new TipoSimbolo(tipo_simbolo, ""), id, Tipo.VARIABLE));

            } else {
                Principal.add_error("La varaible \'" + id + "\' ya esta declarada","Semantico",line,column);
                //aqui va el mensaje de error que ya esta declarada la variable en el ambito
                return;
            }
        }

        if (asignacion != null) {
            boolean pass = (boolean) asignacion.Ejecutar(ts);
            if (pass) {
                // si se asigno el valor;
            } else {
                ts.eliminarSimbolo(id);
            }
        }

    }

    @Override
    public Tipo getType() {
        return Tipo.DECLARACION;
    }
}
