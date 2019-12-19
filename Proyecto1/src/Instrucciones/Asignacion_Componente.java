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
                        label.setSize(20,20);
                        Principal.ventana_actual.add(label);
                        ts.setValor(id, label);
                        break;
                    case TEXTBOX:
                        JTextField txt = new JTextField();
                        txt.setSize(20,20);
                        ts.setValor(id, txt);
                        Principal.ventana_actual.add(txt);
                        break;
                    case TEXTAREA:
                        JTextArea area = new JTextArea();
                        area.setSize(20,20);
                        ts.setValor(id, area);
                        Principal.ventana_actual.add(area);
                        break;
                    case TEXTPASSWORD:
                        JPasswordField jpass = new JPasswordField();
                        jpass.setSize(20,20);
                        ts.setValor(id, jpass);
                        Principal.ventana_actual.add(jpass);
                        break;
                    case TEXTNUMERO:
                        NumberFormat formato = NumberFormat.getNumberInstance();
                        JFormattedTextField txtn = new JFormattedTextField(formato);
                        txtn.setSize(20,20);
                        ts.setValor(id, txtn);
                         Principal.ventana_actual.add(txtn);
                        break;
                    case BUTTON:
                        JButton boton = new JButton();
                        boton.setSize(20,20);
                        boton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                System.out.println("PRUEBA DE IMPRESION");
                            }
                        });
                        Principal.ventana_actual.add(boton);
                        ts.setValor(id, boton);
                        break;
                }
            }

        }

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
