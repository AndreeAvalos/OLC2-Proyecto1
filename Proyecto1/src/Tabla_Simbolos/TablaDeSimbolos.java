/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_Simbolos;

import Instrucciones.Tipo;
import java.util.LinkedList;

/**
 *
 * @author Andree
 */
public class TablaDeSimbolos extends LinkedList<Simbolo> {

    private TablaDeSimbolos padre;

    public TablaDeSimbolos() {
        this.padre = null;
    }

    public void setPadre(TablaDeSimbolos tablapadre) {
        this.padre = tablapadre;
    }

    public void setValor(String id, Object valor) {
        setValor(id, valor, this);
    }

    private void setValor(String id, Object valor, TablaDeSimbolos tsPadre) {
        for (Simbolo item : tsPadre) {
            if (item.getId().equals(id)) {
                if (item.getTipo_instruccion() == Tipo.VARIABLE) {
                    switch (item.getTipo().getAsignado()) {
                        case Entero:
                            item.setValor((int) Double.parseDouble(valor.toString()));
                            return;
                        case Decimal:
                            item.setValor(Double.parseDouble(valor.toString()));
                            return;
                        case Char:
                            item.setValor((char) Double.parseDouble(valor.toString()));
                            return;
                        case Cadena:
                            item.setValor((String) valor.toString());
                            return;
                        case Bool:
                            item.setValor(Boolean.valueOf(valor.toString()));
                            return;
                        default:
                            //deberia tirar error ya que no existe el ID
                            return;
                    }
                } else if (item.getTipo_instruccion() == Tipo.CONSTANTE) {
                    //error porque no se puede cambiar el valor 
                    System.out.println("No se puede cambiar valor a la variable: \'" + id + "\' es una constante.");
                } else if (item.getTipo_instruccion() == Tipo.FUNCION) {
                    // le damos el valor resultante al metodo
                } else if (item.getTipo_instruccion() == Tipo.STRUCT) {
                    // ponemos valor de structn
                } else {
                    //error ya que es un metodo
                }
            }
        }
        if (tsPadre.getPadre() != null) {
            setValor(id, tsPadre.getPadre());
        }
    }

    public boolean asignValor(String id, Object valor) {
        return asignValor(id, valor, this);
    }

    private boolean asignValor(String id, Object valor, TablaDeSimbolos tsPadre) {

        try {
            Object val_aux = null;
            for (Simbolo item : tsPadre) {
                if (item.getId().equals(id)) {
                    switch (item.getTipo().getTipo()) {

                        case Entero:
                            val_aux = (int) Double.parseDouble(valor.toString());
                            return true;
                        case Decimal:
                            val_aux = Double.parseDouble(valor.toString());
                            return true;
                        case Cadena:
                            val_aux = valor.toString();
                            return true;
                        case Char:
                            val_aux = (char) Double.parseDouble(valor.toString());
                            return true;
                        case Bool:
                            val_aux = Boolean.valueOf(valor.toString());
                            return true;
                        default:
                            break;
                    }
                }
            }
            if (tsPadre.getPadre() != null) {
                return asignValor(id, tsPadre.getPadre());
            }
            return val_aux != null;
        } catch (Exception e) {
            return false;
        }
    }

    public Object getValor(String id) {
        return getValor(id, this);
    }

    private Object getValor(String id, TablaDeSimbolos tsPadre) {
        for (Simbolo item : tsPadre) {
            if (item.getId().equals(id)) {
                return item.getValor();
            }
        }
        if (tsPadre.getPadre() != null) {
            return getValor(id, tsPadre.getPadre());
        }
        return null;
    }

    public void incrementarValor(String id) {
        incrementarValor(id, this);
    }

    private void incrementarValor(String id, TablaDeSimbolos tsPadre) {
        tsPadre.stream().filter((item) -> (item.getId().equals(id))).forEachOrdered((item) -> {
            item.setValor((int) item.getValor() + 1);
        });
        if (tsPadre.getPadre() != null) {
            incrementarValor(id, tsPadre.getPadre());
        }
    }

    public void decrementarValor(String id) {
        decrementarValor(id, this);
    }

    private void decrementarValor(String id, TablaDeSimbolos tsPadre) {
        tsPadre.stream().filter((item) -> (item.getId().equals(id))).forEachOrdered((Simbolo item) -> {
            item.setValor((int) item.getValor() - 1);
        });
        if (tsPadre.getPadre() != null) {
            decrementarValor(id, tsPadre.getPadre());
        }
    }

    public boolean existeSimbolo(String id) {
        return existeSimbolo(id, this);
    }

    private boolean existeSimbolo(String id, TablaDeSimbolos tsPadre) {
        return tsPadre.stream().anyMatch((item) -> (item.getId().equals(id)));
    }

    public TablaDeSimbolos getPadre() {
        return this.padre;
    }

    public Tipo getTipo(Object valor) {
        try {
            int val = (int) Double.parseDouble(valor.toString());
            return Tipo.Entero;
        } catch (NumberFormatException e1) {
            try {
                double val = Double.parseDouble(valor.toString());
                return Tipo.Decimal;
            } catch (NumberFormatException e2) {
                try {
                    boolean val = (boolean) Boolean.valueOf(valor.toString());
                    return Tipo.Bool;
                } catch (NumberFormatException e3) {
                    try {
                        char val = (char) Double.parseDouble(valor.toString());
                        return Tipo.Char;
                    } catch (NumberFormatException e4) {

                    }
                }

            }
        }
        return null;
    }

}
