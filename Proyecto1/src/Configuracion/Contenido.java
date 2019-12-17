/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuracion;

/**
 *
 * @author Andree
 */
public class Contenido {

    Etiqueta tipo;
    Object contenido;

    public Contenido(Etiqueta tipo, Object contenido) {
        this.tipo = tipo;
        this.contenido = contenido;
    }

    public Etiqueta getTipo() {
        return tipo;
    }

    public void setTipo(Etiqueta tipo) {
        this.tipo = tipo;
    }

    public Object getContenido() {
        return contenido;
    }

    public void setContenido(Object contenido) {
        this.contenido = contenido;
    }
    
    

}
