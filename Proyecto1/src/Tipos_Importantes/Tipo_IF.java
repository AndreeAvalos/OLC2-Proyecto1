/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tipos_Importantes;

import Instrucciones.*;
import java.util.LinkedList;

/**
 *
 * @author Andree
 */
public class Tipo_IF {

    Operacion expresion;
    LinkedList<Tipo_IF> else_if = new LinkedList<>();
    LinkedList<Instruccion> instrucciones_if, instrucciones_else;

    public Tipo_IF(Operacion expresion, LinkedList<Instruccion> instrucciones_if, LinkedList<Instruccion> instrucciones_else) {
        this.expresion = expresion;
        this.instrucciones_if = instrucciones_if;
        this.instrucciones_else = instrucciones_else;
    }

    public Operacion getExpresion() {
        return expresion;
    }

    public void setExpresion(Operacion expresion) {
        this.expresion = expresion;
    }

    public LinkedList<Tipo_IF> getElse_if() {
        return else_if;
    }

    public void setElse_if(LinkedList<Tipo_IF> else_if) {
        this.else_if = else_if;
    }

    public LinkedList<Instruccion> getInstrucciones_if() {
        return instrucciones_if;
    }

    public void setInstrucciones_if(LinkedList<Instruccion> instrucciones_if) {
        this.instrucciones_if = instrucciones_if;
    }

    public LinkedList<Instruccion> getInstrucciones_else() {
        return instrucciones_else;
    }

    public void setInstrucciones_else(LinkedList<Instruccion> instrucciones_else) {
        this.instrucciones_else = instrucciones_else;
    }

}
