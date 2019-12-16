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
    LinkedList<Sentencia_IF> else_if;
    LinkedList<Instruccion> sentencias_if ;
    
    public Tipo_IF(Operacion expresion, LinkedList<Instruccion> instrucciones_if, LinkedList<Sentencia_IF> instrucciones_else_if) {
        this.expresion = expresion;
        this.sentencias_if = instrucciones_if;
        this.else_if = instrucciones_else_if;
    }

    public Operacion getExpresion() {
        return expresion;
    }

    public void setExpresion(Operacion expresion) {
        this.expresion = expresion;
    }

    public LinkedList<Sentencia_IF> getElse_if() {
        return else_if;
    }

    public void setElse_if(LinkedList<Sentencia_IF> else_if) {
        this.else_if = else_if;
    }

    public LinkedList<Instruccion> getSentencias_if() {
        return sentencias_if;
    }

    public void setSentencias_if(LinkedList<Instruccion> sentencias_if) {
        this.sentencias_if = sentencias_if;
    }
    
    
    


}
