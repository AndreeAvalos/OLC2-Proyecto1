/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuracion;

import java.util.ArrayList;

/**
 *
 * @author Andree
 */
public class Proyecto {

    ArrayList<Contenido> hijos;

    public Proyecto(ArrayList<Contenido> hijos) {
        this.hijos = hijos;
    }

    public ArrayList<Contenido> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Contenido> hijos) {
        this.hijos = hijos;
    }
    
    
    
}
