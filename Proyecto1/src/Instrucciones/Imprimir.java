/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Arbol.Arbol;
import Tabla_Simbolos.TablaDeSimbolos;
import java.util.ArrayList;
import java.util.LinkedList;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Imprimir implements Instruccion {

    Instruccion contenido;
    LinkedList<Operacion> parametros;
    int line, column;

    public Imprimir(Instruccion contenido, int line, int column) {
        this.contenido = contenido;
        this.line = line;
        this.column = column;
        this.parametros = new LinkedList<>();
    }

    public Imprimir(Instruccion contenido, LinkedList<Operacion> parametros, int line, int column) {
        this.contenido = contenido;
        this.parametros = parametros;
        this.line = line;
        this.column = column;
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
        String palabra = contenido.Ejecutar(ts).toString();
        ArrayList<Object> valores = new ArrayList<>();
        parametros.forEach((item) -> {
            valores.add(item.Ejecutar(ts));
        });

        String[] cadena = palabra.split("%");
        if ((cadena.length - 1) == parametros.size()) {
            if (cadena.length == 1) {
                Principal.setMensaje(palabra);
            } else {
                char letra;
                String salida = cadena[0];
                Object val = null;
                for (int i = 1; i < cadena.length; i++) {
                    String trozo = cadena[i];
                    letra = trozo.charAt(0);
                    switch (letra) {
                        case 's':
                            int linea = line;

                            try {
                                Arbol aux = (Arbol) valores.get(i - 1);
                                salida += aux.getSalida();
                                break;
                            } catch (Exception ex) {
                                Principal.add_error("Tipo " + valores.get(i - 1) + " con chr[]", "Semantico", linea, this.column);
                                return null;
                            }
                        case 'e':
                            try {
                                val = (int) Double.parseDouble(valores.get(i - 1).toString());
                                salida += val.toString();
                                break;
                            } catch (NumberFormatException ex) {
                                Principal.add_error("Tipo " + valores.get(i - 1) + " con entero", "Semantico", line, column);
                                return null;
                            }
                        case 'd':
                            try {
                                val = (double) Double.parseDouble(valores.get(i - 1).toString());
                                salida += val.toString();
                            } catch (NumberFormatException ex) {
                                Principal.add_error("Tipo " + valores.get(i - 1) + " con decimal", "Semantico", line, column);
                                return null;
                            }
                            break;
                        case 'c':
                            try {
                                val = (char) Double.parseDouble(valores.get(i - 1).toString());
                                salida += val.toString();
                            } catch (NumberFormatException ex) {
                                Principal.add_error("Tipo " + valores.get(i - 1) + " con chr", "Semantico", line, column);
                                return null;
                            }
                            break;
                        default:
                            Principal.add_error("No existe el parametro " + letra, "Semantico", line, column);
                            return null;

                    }

                    for (int j = 1; j < trozo.length(); j++) {
                        salida += trozo.charAt(j);
                    }
                }
                Principal.setMensaje(salida);
                return null;
            }
        }
        Principal.add_error("No coinciden los parametros", "Semantico", line, column);
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
        //Se dejo vacio con intencion
    }

    @Override
    public Tipo getType() {
        return Tipo.IMPRIMIR;
    }
}
