/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import java.awt.Rectangle;
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
public class Set_Alto implements Instruccion {
    
    String id;
    Operacion valor;
    int line, column;
    
    public Set_Alto(String id, Operacion valor, int line, int column) {
        this.id = id;
        this.valor = valor;
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
                    int val = (int) Double.parseDouble(result.toString());
                    SetAlto(simbolo.getTipo().getTipo(), simbolo.getValor(), val*2);
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
    
    @Override
    public void Recolectar(TablaDeSimbolos ts) {
        
    }
    
    public void SetAlto(Tipo tipo_componente, Object valor, int resultado) {
        switch (tipo_componente) {
            case LABEL:
                JLabel lbl = (JLabel) valor;
                lbl.setSize(lbl.getWidth(), resultado);
                return;
            case TEXTBOX:
                JTextField txt = (JTextField) valor;
                txt.setSize(txt.getWidth(), resultado);
                return;
            case TEXTAREA:
                JTextArea txta = (JTextArea) valor;
                txta.setSize(txta.getWidth(), resultado);
                return;
            case TEXTPASSWORD:
                JPasswordField txtp = (JPasswordField) valor;
                txtp.setSize(txtp.getWidth(), resultado);
                return;
            case TEXTNUMERO:
                JFormattedTextField txtn = (JFormattedTextField) valor;
                txtn.setSize(txtn.getWidth(), resultado);
                return;
            case BUTTON:
                JButton btn = (JButton) valor;
                int val = btn.getWidth();
                btn.setSize(btn.getWidth(), resultado);
                int x = btn.getX(); 
                return;
            default:
                return;
        }
    }
    
    @Override
    public Tipo getType() {
        return Tipo.SET_ALTO;
    }
    
}
