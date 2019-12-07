/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_Simbolos;

/**
 *
 * @author Andree
 */
public class Simbolo {
    private TipoSimbolo tipo;//Aqui puede venir (numero,int),(numero,double) etc
    private String id;//el id de la variable
    private Object valor;//el valor de la variable
    private boolean declarado;//si tiene valor o no.

    /**
     * Constructor para declarar una variable
     * @param tipo
     * @param id
     */
    public Simbolo(TipoSimbolo tipo, String id) {
        this.tipo = tipo;
        this.id = id;
        this.valor = null;
        this.declarado = false;
    } 

    public TipoSimbolo getTipo() {
        return tipo;
    }

    public void setTipo(TipoSimbolo tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public boolean isDeclarado() {
        return declarado;
    }

    public void setDeclarado(boolean declarado) {
        this.declarado = declarado;
    }
    
    
    
}
