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
public class Tipo_Retorno {
    
    Tipo Etiqueta;
    Object resultado;

    public Tipo_Retorno(Tipo Etiqueta, Object resultado) {
        this.Etiqueta = Etiqueta;
        this.resultado = resultado;
    }

    public Tipo getEtiqueta() {
        return Etiqueta;
    }

    public void setEtiqueta(Tipo Etiqueta) {
        this.Etiqueta = Etiqueta;
    }

    public Object getResultado() {
        return resultado;
    }

    public void setResultado(Object resultado) {
        this.resultado = resultado;
    }
    
    
    
}
