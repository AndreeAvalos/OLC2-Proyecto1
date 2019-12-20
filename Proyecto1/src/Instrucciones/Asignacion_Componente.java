/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Asignacion_Componente implements Instruccion {

    String id;
    int line, column;

    public Asignacion_Componente(String id, int line, int column) {
        this.id = id;
        this.line = line;
        this.column = column;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public Object Ejecutar(TablaDeSimbolos ts) {

        if (ts.existeSimbolo(id)) {
            Simbolo simbolo = ts.getSimbolo(id);
            if (simbolo.getTipo_instruccion() == Tipo.COMPONENTE) {
                switch (simbolo.getTipo().getTipo()) {
                    case LABEL:
                        JLabel label = new JLabel();
                        label.setSize(50, 20);
                        Principal.ventana_actual.add(label);
                        ts.setValor(id, label);
                        return null;
                    case TEXTBOX:
                        JTextField txt = new JTextField();
                        txt.setSize(50, 20);
                        ts.setValor(id, txt);
                        Principal.ventana_actual.add(txt);
                        return null;
                    case TEXTAREA:
                        JTextArea area = new JTextArea();
                        area.setSize(50, 20);
                        ts.setValor(id, area);
                        Principal.ventana_actual.add(area);
                        return null;
                    case TEXTPASSWORD:
                        JPasswordField jpass = new JPasswordField();
                        jpass.setSize(50, 20);
                        ts.setValor(id, jpass);
                        Principal.ventana_actual.add(jpass);
                        return null;
                    case TEXTNUMERO:
                        NumberFormat formato = NumberFormat.getNumberInstance();
                        JFormattedTextField txtn = new JFormattedTextField(formato);
                        txtn.setSize(50, 20);
                        ts.setValor(id, txtn);
                        Principal.ventana_actual.add(txtn);
                        return null;
                    case BUTTON:
                        JButton boton = new JButton();
                        boton.setSize(50, 20);

                        boton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                Instruccion aux = ts.getContenido("EVENTO_" + id + "_CLICK");
                                if (aux != null) {
                                    Crear_Evento aux2 = (Crear_Evento) aux;
                                    aux2.Llamada = true;
                                    aux2.Ejecutar(ts);
                                } else {
                                    //System.out.println("no existe evento al click");
                                }
                            }
                        });
                        Principal.ventana_actual.add(boton);
                        ts.setValor(id, boton);
                        return null;
                }
            }
            Principal.add_error( id+ " no es un componente", "Semantico", line, column);
            return null;
        }
        Principal.add_error("No existe componente " + id, "Semantico", line, column);
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {

    }

    @Override
    public Tipo getType() {
        return Tipo.ASIGNACION_COMPONENTE;
    }
}
