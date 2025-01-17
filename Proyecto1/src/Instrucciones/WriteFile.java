/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Arbol.Arbol;
import Tabla_Simbolos.TablaDeSimbolos;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class WriteFile implements Instruccion {

    Instruccion contenido;
    LinkedList<Operacion> parametros;
    int line, column;

    public WriteFile(Instruccion contenido, int line, int column) {
        this.contenido = contenido;
        this.line = line;
        this.column = column;
        this.parametros = new LinkedList<>();
    }

    public WriteFile(Instruccion contenido, LinkedList<Operacion> parametros, int line, int column) {
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
        if (Principal.escribiendo == false) {
            return null;
        }

        Arbol aux2 = (Arbol) contenido.Ejecutar(ts);
        aux2.print();
        String palabra = aux2.getSalida();
        ArrayList<Object> valores = new ArrayList<>();
        parametros.forEach((item) -> {
            valores.add(item.Ejecutar(ts));
        });

        String[] cadena = palabra.split("%");
        if ((cadena.length - 1) == parametros.size()) {
            if (cadena.length == 1) {
                try {
                    Principal.buffer.write(palabra);
                } catch (IOException ex) {
                }
                return null;
            } else {
                char letra;
                String salida = cadena[0];
                Object val = null;
                for (int i = 1; i < cadena.length; i++) {
                    String trozo = cadena[i];
                    letra = trozo.charAt(0);
                    switch (letra) {
                        case 's':
                            try {
                                Arbol aux = (Arbol) valores.get(i - 1);
                                aux.print();
                                salida += aux.getSalida();
                                break;
                            } catch (Exception ex) {
                                Principal.add_error("Tipo " + valores.get(i - 1) + " con chr[]", "Semantico", line, this.column);
                                return null;
                            }
                        case 'e':
                            try {
                                val = (int) Integer.parseInt(valores.get(i - 1).toString());
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
                                val = (char) valores.get(i - 1).toString().charAt(0);
                                salida += val.toString();
                            } catch (NumberFormatException ex) {
                                Principal.add_error("Tipo " + valores.get(i - 1) + " con chr", "Semantico", line, column);
                                return null;
                            }
                            break;
                        case 'b':
                            try {
                                val = (boolean) Boolean.parseBoolean(valores.get(i - 1).toString());
                                salida += val.toString();
                            } catch (NumberFormatException ex) {
                                Principal.add_error("Tipo " + valores.get(i - 1) + " con bul", "Semantico", line, column);
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
                try {
                    Principal.buffer.write(salida);
                } catch (IOException ex) {
                }

                salida = "";
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
