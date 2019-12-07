/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import Tabla_Simbolos.TipoSimbolo;

/**
 *
 * @author Andree
 */
public class Declaracion implements Instruccion {

    Tipo tipo_simbolo;//numero,cadena,identificador etc
    Tipo tipo_declarado;//entero,decimal,caracter etc
    String id;//nombre de variable;
    int line, column;// linea y columna de token 

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
            if (!ts.existeSimbolo(id)) {
                ts.add(new Simbolo(new TipoSimbolo(tipo_simbolo, tipo_declarado), id));
            } else {
                //aqui va el mensaje de error que ya esta declarada la variable en el ambito
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
                ts.add(new Simbolo(new TipoSimbolo(tipo_simbolo, tipo_declarado), id));
            } else {
                //aqui va el mensaje de error que ya esta declarada la variable en el ambito global
            }
        }
    }

    @Override
    public Tipo getType() {
        return Tipo.DECLARACION;
    }
}
