/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_Simbolos;

import Instrucciones.Instruccion;
import Instrucciones.Tipo;
import java.util.ArrayList;

/**
 *
 * @author Andree
 */
public class Simbolo {

    private TipoSimbolo tipo;//Aqui puede venir (numero,int),(numero,double) etc
    private String id;//el id de la variable
    private Object valor;//el valor de la variable
    private Instruccion contenido;
    private boolean declarado;//si tiene valor o no.
    private Tipo tipo_instruccion;
    private ArrayList<String> referencias = new ArrayList<>(); // id a los cual esta referida
    private ArrayList<String> referidos; // id a los cual esta referida
    private boolean PorReferencia; // si esta referenciada

    public Simbolo copy(Simbolo sim) {
        this.tipo = sim.getTipo();
        this.id = sim.getId();
        this.valor = sim.getValor();
        this.contenido = sim.getContenido();
        this.declarado = false;
        this.tipo_instruccion = sim.getTipo_instruccion();
        this.referencias = sim.getReferencias();
        this.PorReferencia = sim.PorReferencia;
        return this;
    }

    /**
     * Constructor para declarar una variable
     *
     * @param tipo
     * @param id
     * @param tipoinstru
     */
    public Simbolo(TipoSimbolo tipo, String id, Tipo tipoinstru) {
        this.tipo = tipo;
        this.id = id;
        this.valor = null;
        this.declarado = false;
        this.tipo_instruccion = tipoinstru;
    }

    public Simbolo(TipoSimbolo tipo, String id, Instruccion contenido, Tipo tipo_instruccion) {
        this.tipo = tipo;
        this.id = id;
        this.contenido = contenido;
        this.tipo_instruccion = tipo_instruccion;
    }
//Simbolo para los arreglos 

    public Simbolo(TipoSimbolo tipo, String id, Object valor, Instruccion contenido, Tipo tipo_instruccion) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
        this.contenido = contenido;
        this.tipo_instruccion = tipo_instruccion;
    }

    public Simbolo(TipoSimbolo tipo, String id, Object valor, Tipo tipo_instruccion) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
        this.tipo_instruccion = tipo_instruccion;
    }

    /**
     * Constructor para tipos de estructuras
     *
     * @param id
     * @param valor
     */
    public Simbolo(String id, Object valor) {
        this.id = id;
        this.valor = valor;
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

    public Instruccion getContenido() {
        return contenido;
    }

    public void setContenido(Instruccion contenido) {
        this.contenido = contenido;
    }

    public Tipo getTipo_instruccion() {
        return tipo_instruccion;
    }

    public void setTipo_instruccion(Tipo tipo_instruccion) {
        this.tipo_instruccion = tipo_instruccion;
    }

    public ArrayList<String> getReferencias() {
        return referencias;
    }

    public boolean setReferencia(String id) {
        return referencias.add(id);
    }

    public void setReferencias(ArrayList<String> referencias) {
        this.referencias = referencias;
    }

    public boolean isPorReferencia() {
        return PorReferencia;
    }

    public void setPorReferencia(boolean PorReferencia) {
        this.PorReferencia = PorReferencia;
    }

    public ArrayList<String> getReferidos() {
        return referidos;
    }

    public void setReferidos(ArrayList<String> referidos) {
        this.referidos = referidos;
    }

    public void removeReferencia(String id) {
        referencias.remove(id);
    }

}
