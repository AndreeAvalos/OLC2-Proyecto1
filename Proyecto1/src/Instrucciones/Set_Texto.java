/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
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
public class Set_Texto implements Instruccion {

    public enum Tipo_ST {
        VARIABLE,
        CADENA
    }

    String id_a;
    String id_b;
    Operacion valor;
    Tipo_ST tipo;
    int line, column;

    public Set_Texto(String id_a, Operacion valor, int line, int column) {
        this.id_a = id_a;
        this.line = line;
        this.valor = valor;
        this.column = column;
        this.tipo = Tipo_ST.CADENA;
    }

    public Set_Texto(String id_a, String id_b, int line, int column) {
        this.id_a = id_a;
        this.id_b = id_b;
        this.line = line;
        this.column = column;
        this.tipo = Tipo_ST.VARIABLE;
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
        if (tipo == Tipo_ST.CADENA) {
            String resultado = valor.Ejecutar(ts).toString();
            if (ts.existeSimbolo(id_a)) {
                Simbolo sim = ts.getSimbolo(id_a);
                if (sim.getTipo_instruccion() == Tipo.COMPONENTE) {
                    Object val = sim.getValor();
                    SetText(sim.getTipo().getTipo(), val, resultado);
                    return null;
                }
                Principal.add_error("No es un componente", "Semantico", line, column);
                return null;
            }
            Principal.add_error("No existe el componente " + id_a, "Semantico", line, column);
            return null;
        } else {
            if (ts.existeSimbolo(id_b)) {
                Simbolo simbolo = ts.getSimbolo(id_b);
                if (simbolo.getTipo().getTipo() == Tipo.String) {
                    String resultado = ts.getValor(id_b).toString();
                    if (ts.existeSimbolo(id_a)) {
                        Simbolo sim = ts.getSimbolo(id_a);
                        if (sim.getTipo_instruccion() == Tipo.COMPONENTE) {
                            Object val = sim.getValor();
                            SetText(sim.getTipo().getTipo(), val, resultado);
                            return null;
                        }
                        Principal.add_error("No es un componente", "Semantico", line, column);
                        return null;
                    }
                    Principal.add_error("No existe el componente " + id_a, "Semantico", line, column);
                    return null;
                }
                Principal.add_error("La variable " + id_b + " no es un rstring", "Semantico", line, column);
                return null;
            }
            Principal.add_error("No existe la variable " + id_b, "Semantico", line, column);
            return null;
        }
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {

    }

    public void SetText(Tipo tipo_componente, Object valor, String resultado) {
        switch (tipo_componente) {
            case LABEL:
                JLabel lbl = (JLabel) valor;
                lbl.setText(resultado);
                return;
            case TEXTBOX:
                JTextField txt = (JTextField) valor;
                txt.setText(resultado);
                return;
            case TEXTAREA:
                JTextArea txta = (JTextArea) valor;
                txta.setText(resultado);
                return;
            case TEXTPASSWORD:
                JPasswordField txtp = (JPasswordField) valor;
                txtp.setText(resultado);
                return;
            case TEXTNUMERO:
                JFormattedTextField txtn = (JFormattedTextField) valor;
                txtn.setText(resultado);
                return;
            case BUTTON:
                JButton btn = (JButton) valor;
                btn.setText(resultado);
                return;
            default:
                return;
        }
    }

    @Override
    public Tipo getType() {
        return Tipo.SETTEXT;
    }

}
