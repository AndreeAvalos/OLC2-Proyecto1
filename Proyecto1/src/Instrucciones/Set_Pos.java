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
public class Set_Pos implements Instruccion {

    String id;
    Operacion valor, valor2;

    int line, column;

    public Set_Pos(String id, Operacion valor, Operacion valor2, int line, int column) {
        this.id = id;
        this.valor = valor;
        this.valor2 = valor2;
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
                try {
                    Object result = this.valor.Ejecutar(ts);
                    int x = (int) (int) Double.parseDouble(result.toString());
                    int y = (int) (int) Double.parseDouble(this.valor2.Ejecutar(ts).toString());
                    SetPos(simbolo.getTipo().getTipo(), simbolo.getValor(), x, y);
                    return null;

                } catch (Exception e) {
                    Principal.add_error("No es un numero entero", "Semantico", line, column);
                    return null;
                }
            }
            Principal.add_error("No es un componente", "Semantico", line, column);
            return null;
        }
        Principal.add_error("No existe variable " + id, "Semantico", line, column);
        return null;
    }

    public void SetPos(Tipo tipo_componente, Object valor, int x, int y) {
        switch (tipo_componente) {
            case LABEL:
                JLabel lbl = (JLabel) valor;
                lbl.setLocation(x, y);
                return;
            case TEXTBOX:
                JTextField txt = (JTextField) valor;
                txt.setLocation(x, y);
                return;
            case TEXTAREA:
                JTextArea txta = (JTextArea) valor;
                txta.setLocation(x, y);
                return;
            case TEXTPASSWORD:
                JPasswordField txtp = (JPasswordField) valor;
                txtp.setLocation(x, y);
                return;
            case TEXTNUMERO:
                JFormattedTextField txtn = (JFormattedTextField) valor;
                txtn.setLocation(x, y);
                return;
            case BUTTON:
                JButton btn = (JButton) valor;
                btn.setLocation(x, y);
                return;
            default:
                return;
        }
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {

    }

    @Override
    public Tipo getType() {
        return Tipo.SET_POS;
    }

}
