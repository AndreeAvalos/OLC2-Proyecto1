/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_Simbolos;

import Instrucciones.Instruccion;
import Instrucciones.Tipo;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Andree
 */
public class TablaDeSimbolos extends LinkedList<Simbolo> {

    public TablaDeSimbolos padre;

    public TablaDeSimbolos() {
        this.padre = null;
    }

    public void setPadre(TablaDeSimbolos tablapadre) {
        this.padre = tablapadre;
    }

    public void setValor(String id, Object valor) {

        setValor(id, valor, this);
    }

    private void setValor(String id, Object valor, TablaDeSimbolos padre) {
        for (Simbolo item : padre) {
            if (item.getTipo().getTipo() != null) {
                if (item.getId().equals(id)) {
                    try {
                        switch (item.getTipo_instruccion()) {
                            case VARIABLE:

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
                                        break;
                                }
                            case CONSTANTE:
                                //error porque no se puede cambiar el valor
                                System.out.println("No se puede cambiar valor a la variable: \'" + id + "\' es una constante.");
                                break;
                            case FUNCION:
                                // le damos el valor resultante al metodo
                                switch (item.getTipo().getTipo()) {
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
                                        break;
                                }
                            // ponemos valor de struct
                            case STRUCT:
                                break;
                            case ARREGLO:
                                item.setValor(valor);
                                break;
                            default:
                                //error ya que es un metodo
                                System.out.println("No puede asignar un valor a un metodo");
                                break;
                        }

                    } catch (EnumConstantNotPresentException e) {
                        return;
                    }
                }
            }
        }
        if (padre.getPadre() != null) {
            setValor(id, valor, padre.getPadre());
        }

    }

    public void eliminarSimbolo(String id) {
        eliminarSimbolo(id, this);
    }

    private void eliminarSimbolo(String id, TablaDeSimbolos padre) {
        for (int i = 0; i < padre.size(); i++) {
            if (padre.get(i).getId().equals(id)) {
                padre.remove(i);
                return;
            }
        }
    }

    public void setValorReferencias(String id, Object valor) {
        setValorReferencias(id, valor, this);
    }

    private void setValorReferencias(String id, Object valor, TablaDeSimbolos padre) {
        for (Simbolo item : padre) {
            if (item.getId().equals(id)) {
                item.getReferencias().forEach((referencia) -> {
                    padre.setValor(referencia, valor);
                });
                return;
            }
        }
        if (padre.getPadre() != null) {
            setValorReferencias(id, valor, padre.getPadre());
        }
    }

    public boolean existReferencia(String id) {
        return existReferencia(id, this);
    }

    private boolean existReferencia(String id, TablaDeSimbolos padre) {
        for (Simbolo item : padre) {
            if (item.getId().equals(id)) {
                return !item.getReferencias().isEmpty();
            }
        }

        if (padre.getPadre() != null) {
            existReferencia(id, padre.getPadre());
        }
        return false;
    }

    public void setReferencia(String origen, String destino) {
        setReferencia(origen, destino, this);
    }

    private void setReferencia(String origen, String destino, TablaDeSimbolos padre) {
        for (Simbolo item : padre) {
            if (item.getId().equals(origen)) {
                item.setReferencia(destino);
            }
        }

        if (padre.getPadre() != null) {
            setReferencia(origen, destino, padre.getPadre());
        }
    }

    public boolean compararTipos(String origen, String destino) {
        return getTipoAsignado(destino, this).equals(getTipoAsignado(origen, this));
    }

    private Tipo getTipoAsignado(String id, TablaDeSimbolos padre) {
        for (Simbolo item : padre) {
            if (item.getId().equals(id)) {
                return item.getTipo().getTipo();
            }
        }

        if (padre.getPadre() != null) {
            return getTipoAsignado(id, padre.getPadre());
        }
        return null;
    }

    public boolean asignValor(String id, Object valor) {
        return asignValor(id, valor, this);
    }

    private boolean asignValor(String id, Object valor, TablaDeSimbolos padre) {
        for (Simbolo item : padre) {
            if (item.getTipo().getTipo() != null) {
                if (item.getId().equals(id)) {
                    try {
                        Object val_aux = null;
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
                                return false;
                        }
                    } catch (EnumConstantNotPresentException e) {
                        return false;
                    }
                }
            }
        }
        if (padre.getPadre() != null) {
            return asignValor(id, valor, padre.getPadre());
        } else {
            return false;
        }
    }

    public void recorrerPadres(TablaDeSimbolos padre, int nivel) {
        System.out.println("-----SU NIVEL:" + nivel + "-----");
        if (padre.getPadre() != null) {
            recorrerPadres(padre.getPadre(), nivel + 1);
        }
    }

    public void setValorByIndex(int index, Object valor) {
        setValorByIndex(index, valor, this);
    }

    private void setValorByIndex(int index, Object valor, TablaDeSimbolos tsPadre) {

        Simbolo item = tsPadre.get(index);
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
    }

    public ArrayList<String> getListaReferencia(String id) {
        ArrayList<String> lista = new ArrayList<>();
        lista.add(id);
        return getListaReferencia(id, lista, this);
    }

    private ArrayList<String> getListaReferencia(String id, ArrayList<String> lista, TablaDeSimbolos padre) {
        ArrayList<String> lista_referencias = padre.getSimbolo(id, padre).getReferencias();        
        for (String item : lista_referencias) {
            if (!lista.contains(item)) {
                lista.add(item);
                getListaReferencia(item, lista, padre);
            }
        }

        if (padre.getPadre() != null) {
            return getListaReferencia(id, lista, padre.getPadre());
        } else {
            return lista;
        }
    }

    private Simbolo getSimbolo(String id, TablaDeSimbolos padre) {
        for (Simbolo item : padre) {
            if (item.getId().equals(id)) {
                return item;
            }
        }

        if (padre.getPadre() != null) {
            return getSimbolo(id, padre.getPadre());
        } else {
            return null;
        }
    }

    public boolean asignValorByIndex(int index, Object valor) {
        return asignValorByIndex(index, valor, this);
    }

    private boolean asignValorByIndex(int index, Object valor, TablaDeSimbolos tsPadre) {

        Simbolo item = tsPadre.get(index);
        try {
            Object val_aux = null;
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
                    return false;
            }

        } catch (NumberFormatException e) {
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

    public Instruccion getContenido(String id) {
        return getContenido(id, this);
    }

    private Instruccion getContenido(String id, TablaDeSimbolos tsPadre) {
        for (Simbolo item : tsPadre) {
            if (item.getId().equals(id)) {
                return item.getContenido();
            }
        }
        if (tsPadre.getPadre() != null) {
            return getContenido(id, tsPadre.getPadre());
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

    public boolean castearFuncion(String id, Object valor) {

        return castearFuncion(id, valor, this);
    }

    private boolean castearFuncion(String id, Object valor, TablaDeSimbolos padre) {
        for (Simbolo item : padre) {
            if (item.getTipo_instruccion() != null) {
                if (item.getTipo_instruccion() == Tipo.FUNCION) {
                    if (item.getId().equals(id)) {
                        try {
                            Object val_aux = null;
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
                                    return false;
                            }
                        } catch (EnumConstantNotPresentException e) {
                            return false;
                        }
                    }
                }
            }
        }
        if (padre.getPadre() != null) {
            return castearFuncion(id, valor, padre.getPadre());
        } else {
            return false;
        }
    }

    public Object castearValor(String id, Object Valor) {
        return castearValor(id, Valor, this);

    }

    private Object castearValor(String id, Object valor, TablaDeSimbolos padre) {
        for (Simbolo item : padre) {
            if (item.getTipo_instruccion() != null) {
                if (item.getTipo_instruccion() == Tipo.ARREGLO) {
                    if (item.getId().equals(id)) {
                        try {
                            switch (item.getTipo().getTipo()) {
                                case Entero:
                                    return (int) Double.parseDouble(valor.toString());
                                case Decimal:
                                    return Double.parseDouble(valor.toString());
                                case Char:
                                    return (char) Double.parseDouble(valor.toString());
                                case Bool:
                                    return Boolean.valueOf(valor.toString());
                                default:
                                    //deberia tirar error ya que no existe el ID
                                    break;
                            }
                        } catch (EnumConstantNotPresentException e) {
                            return null;
                        }
                    }
                }
            }
        }
        if (padre.getPadre() != null) {
            castearValor(id, valor, padre.getPadre());
        }
        return null;
    }

    public void setValor_Funcion(String id, Object valor) {

        setValor_Funcion(id, valor, this);
    }

    private void setValor_Funcion(String id, Object valor, TablaDeSimbolos padre) {
        for (Simbolo item : padre) {
            if (item.getTipo_instruccion() != null) {
                if (item.getTipo_instruccion() == Tipo.FUNCION) {
                    if (item.getId().equals(id)) {
                        try {
                            switch (item.getTipo().getTipo()) {
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
                                    break;
                            }
                        } catch (EnumConstantNotPresentException e) {
                            return;
                        }
                    }
                }
            }
        }
        if (padre.getPadre() != null) {
            setValor_Funcion(id, valor, padre.getPadre());
        }
    }

    public boolean existeMetodo_Funcion(String id) {
        return existeMetodo_Funcion(id, this);
    }

    private boolean existeMetodo_Funcion(String id, TablaDeSimbolos tsPadre) {
        for (Simbolo item : tsPadre) {
            if (item.getTipo_instruccion() != null) {
                if (item.getTipo_instruccion() == Tipo.METODO) {
                    if (item.getId().equals(id)) {
                        return true;
                    }
                } else if (item.getTipo_instruccion() == Tipo.FUNCION) {
                    if (item.getId().equals(id)) {
                        return true;
                    }
                }
            }
        }
        if (tsPadre.getPadre() != null) {
            return existeMetodo_Funcion(id, tsPadre.getPadre());
        } else {
            return false;
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
        for (Simbolo item : tsPadre) {
            if (item.getId().equals(id)) {
                return true;
            }
        }
        if (tsPadre.getPadre() != null) {
            return existeSimbolo(id, tsPadre.getPadre());
        } else {
            return false;
        }

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
