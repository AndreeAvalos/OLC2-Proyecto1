/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import Tabla_Simbolos.TipoSimbolo;
import Tabla_Simbolos.Tipo_Retorno;
import java.awt.Frame;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Iniciar_Ventana implements Instruccion {

    LinkedList<Instruccion> contenido;
    String id;
    JFrame frame = new JFrame("Ventana OLC");
    int line, column;

    public Iniciar_Ventana(LinkedList<Instruccion> contenido, int line, int column) {
        this.contenido = contenido;
        this.line = line;
        this.column = column;
        this.id = Principal.clase_actual + "_VENTANA";
    }

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public int getColumn() {
        return this.column;
    }

    @Override
    public Object Ejecutar(TablaDeSimbolos ts) {
        JPanel panel = new JPanel();
        Principal.ventana_actual = panel;
        frame.setSize(500, 500);
        Principal.frame = frame;
        TablaDeSimbolos local = new TablaDeSimbolos();
        local.setPadre(ts);
        for (Instruccion item : contenido) {
            if (item.getType() == Tipo.RETURN) {
                //aqui ponemos el valor en la funcion
                Principal.add_error("No puede retornar un valor a un metodo.", "Semantico", line, column);
                return null;
            } else {
                Object result = item.Ejecutar(local);
                if (result != null) {
                    try {
                        Tipo_Retorno etiqueta = (Tipo_Retorno) result;
                        if (etiqueta.getEtiqueta() == Tipo.ETIQUETA_RETURN) {
                            //error porque no se puede hacer el casteo explicito
                            Principal.add_error("El valor" + etiqueta.getResultado() + " no puede ser retornado en un metodo", "Semantico", line, column);
                            return null;
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        Principal.frame.add(Principal.ventana_actual);
        Principal.frame.setVisible(true);
        Principal.frame = null;
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
        if (ts.getPadre() == null) {
            if (!ts.existeSimbolo(id)) {
                ts.add(new Simbolo(new TipoSimbolo(Tipo.Frame, ""), id, this, Tipo.VENTANA));
            } else {
                System.out.println("La funcion \'" + id + "\' ya esta declarada");
                return;
                //aqui va el mensaje de error que ya esta declarada la variable en el ambito
            }
        }
    }

    @Override
    public Tipo getType() {
        return Tipo.INICIO_VENTANA;
    }

}
