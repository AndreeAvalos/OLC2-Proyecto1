/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_Simbolos;

import Instrucciones.Tipo;

/**
 *
 * @author Andree
 */
public class TipoSimbolo {
    
    Tipo tipo;
    Tipo asignado;

    public TipoSimbolo(Tipo tipo, Tipo asignado) {
        this.tipo = tipo;
        this.asignado = asignado;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Tipo getAsignado() {
        return asignado;
    }

    public void setAsignado(Tipo asignado) {
        this.asignado = asignado;
    }
    
    
}
