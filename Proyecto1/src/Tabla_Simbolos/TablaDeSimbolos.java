/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_Simbolos;

import Arbol.Arbol;
import Instrucciones.Instruccion;
import Instrucciones.Tipo;
import java.util.ArrayList;
import java.util.LinkedList;
import Tipos_Importantes.Error;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class TablaDeSimbolos extends LinkedList<Simbolo> {

    public TablaDeSimbolos padre;
    public String nombre;
    public ArrayList<String> lst = new ArrayList<>();

    public TablaDeSimbolos() {
        this.padre = null;
    }

    public TablaDeSimbolos(String nombre) {
        this.nombre = nombre;
        this.padre = null;
    }

    public void setPadre(TablaDeSimbolos tablapadre) {
        this.padre = tablapadre;
    }
    // -------------------------------METODO PARA DAR VALOR SIMPLE-----------------------------

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
                                switch (item.getTipo().getTipo()) {
                                    case Entero:
                                        item.setValor((int) Double.parseDouble(valor.toString()));
                                        return;
                                    case Decimal:
                                        item.setValor(Double.parseDouble(valor.toString()));
                                        return;
                                    case Char:
                                        item.setValor((char) valor.toString().charAt(0));
                                        return;
                                    case Cadena:
                                        item.setValor((String) valor.toString());
                                        return;
                                    case Bool:
                                        item.setValor(Boolean.valueOf(valor.toString()));
                                        return;
                                    case String:
                                        item.setValor(valor.toString());
                                        return;
                                    default:
                                        //deberia tirar error ya que no existe el ID
                                        break;
                                }
                            case CONSTANTE:
                                //error porque no se puede cambiar el valor
                                System.out.println("No se puede cambiar valor a la variable: \'" + id + "\' es una constante.");
                                break;
                            case COMPONENTE:
                                item.setValor(valor);
                            case FUNCION:
                                if (item.getTipo().getAsignado().equals("arreglo")) {
                                    item.setValor(valor);
                                    return;
                                }
                                // le damos el valor resultante al metodo
                                switch (item.getTipo().getTipo()) {
                                    case Entero:
                                        item.setValor((int) Double.parseDouble(valor.toString()));
                                        return;
                                    case Decimal:
                                        item.setValor(Double.parseDouble(valor.toString()));
                                        return;
                                    case Char:
                                        item.setValor((char) valor.toString().charAt(0));
                                        return;
                                    case Cadena:
                                        item.setValor((String) valor.toString());
                                        return;
                                    case Bool:
                                        item.setValor(Boolean.valueOf(valor.toString()));
                                        return;
                                    case Struct:
                                        Object val_aux = new Object();
                                        item.setValor(valor);
                                        break;
                                    default:
                                        //deberia tirar error ya que no existe el ID
                                        break;
                                }
                            // ponemos valor de struct

                            case FUSION:
                                //asignamos la tabla de simbolos;
                                item.setValor(valor);
                                return;
                            case OBJETO:
                                //error ya que no se puede asignar un valor al objeto
                                break;
                            case ARREGLO:
                                item.setValor(valor);
                                break;
                            default:
                                //error ya que es un metodo
                                System.out.println("No puede asignar un valor a un metodo");
                                break;
                        }

                    } catch (Exception e) {
                        return;
                    }
                }
            }
        }

        if (padre.getPadre()
                != null) {
            setValor(id, valor, padre.getPadre());
        }

    }
// -------------------------------METODO PARA DAR VALOR SIMPLE-----------------------------

// -------------------------------METODO PARA Eliminar SIMBOLO-----------------------------
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
    // -------------------------------METODO PARA Eliminar SIMBOLO-----------------------------

    //--------------------------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------------------------
    public boolean asignValor(String id, Object valor) {
        return asignValor(id, valor, this);
    }

    public boolean asignValor(String id, Object valor, TablaDeSimbolos padre) {
        for (Simbolo item : padre) {
            if (item.getTipo().getTipo() != null) {

                if (item.getId().equals(id)) {
                    try {
                        Object val_aux = null;
                        if (item.getTipo_instruccion() == Tipo.ARREGLO) {
                            val_aux = (Arbol) valor;
                            return true;
                        }
                        switch (item.getTipo().getTipo()) {
                            case Entero:
                                val_aux = (int) Double.parseDouble(valor.toString());
                                return true;
                            case Decimal:
                                val_aux = Double.parseDouble(valor.toString());
                                return true;
                            case Cadena:
                                try {
                                    val_aux = (String) valor;
                                    return true;
                                } catch (Exception e) {
                                    return false;
                                }

                            case Char:
                                val_aux = (char) Double.parseDouble(valor.toString());
                                return true;
                            case Bool:
                                val_aux = Boolean.valueOf(valor.toString());
                                return true;
                            case Struct:
                                TablaDeSimbolos obtenida = (TablaDeSimbolos) valor;
                                return true;
                            case String:
                                return true;
                            default:
                                return false;
                        }
                    } catch (Exception e) {
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

    //--------------------------------------------------------------------------------------------
    public void recorrerPadres(TablaDeSimbolos padre, int nivel) {
        System.out.println("-----SU NIVEL:" + nivel + "-----");
        if (padre.getPadre() != null) {
            recorrerPadres(padre.getPadre(), nivel + 1);
        }
    }

    //--------------------------------------------------------------------------------------------
    public void setValorByIndex(int index, Object valor) {
        setValorByIndex(index, valor, this);
    }

    private void setValorByIndex(int index, Object valor, TablaDeSimbolos tsPadre) {

        Simbolo item = tsPadre.get(index);
        if (item.getTipo_instruccion() == Tipo.ARREGLO) {
            item.setValor(valor);
            return;
        }
        switch (item.getTipo().getTipo()) {
            case Entero:
                item.setValor((int) Double.parseDouble(valor.toString()));
                break;
            case Decimal:
                item.setValor(Double.parseDouble(valor.toString()));
                break;
            case Char:
                item.setValor((char) valor.toString().charAt(0));
                break;
            case Cadena:
                item.setValor((String) valor.toString());
                break;
            case Bool:
                item.setValor(Boolean.valueOf(valor.toString()));
                break;
            case Struct:
                item.setValor(valor);
            default:
                //deberia tirar error ya que no existe el ID
                break;
        }
    }

    //--------------------------------------------------------------------------------------------
    public ArrayList<String> getListaReferencia(String id) {
        ArrayList<String> lista = new ArrayList<>();
        lista.add(id);
        return getListaReferencia(id, lista, this);
    }

    private ArrayList<String> getListaReferencia(String id, ArrayList<String> lista, TablaDeSimbolos padre) {

        if (padre.getSimbolo(id, padre) != null) {
            ArrayList<String> lista_referencias = padre.getSimbolo(id, padre).getReferencias();
            for (String item : lista_referencias) {
                if (!lista.contains(item)) {
                    lista.add(item);
                    getListaReferencia(item, lista, padre);
                }
            }
        }
        if (padre.getPadre() != null) {
            return getListaReferencia(id, lista, padre.getPadre());
        } else {
            return lista;
        }
    }

    //--------------------------------------------------------------------------------------------
    public Simbolo getSimbolo(String id) {
        return getSimbolo(id, this);
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

    //--------------------------------------------------------------------------------------------
    public boolean asignValorByIndex(int index, Object valor) {
        return asignValorByIndex(index, valor, this);
    }

    private boolean asignValorByIndex(int index, Object valor, TablaDeSimbolos tsPadre) {

        Simbolo item = tsPadre.get(index);
        try {
            Object val_aux = null;
            if (item.getTipo_instruccion() == Tipo.ARREGLO) {
                val_aux = (Arbol) valor;
                return true;
            }
            switch (item.getTipo().getTipo()) {
                case Entero:
                    val_aux = (int) Double.parseDouble(valor.toString());
                    return true;
                case Decimal:
                    val_aux = Double.parseDouble(valor.toString());
                    return true;
                case Cadena:
                    try {
                        val_aux = (String) valor;
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                case Char:
                    val_aux = (char) valor.toString().charAt(0);
                    return true;
                case Bool:
                    val_aux = Boolean.valueOf(valor.toString());
                    return true;
                case Struct:
                    if (valor == null) {
                        return true;
                    }
                    val_aux = (TablaDeSimbolos) valor;
                    return true;
                default:
                    return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    //--------------------------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------------------------
    public boolean castearFuncion(String id, Object valor) {

        return castearFuncion(id, valor, this);
    }

    private boolean castearFuncion(String id, Object valor, TablaDeSimbolos padre) {
        for (Simbolo item : padre) {
            if (item.getTipo_instruccion() != null) {
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
                                val_aux = (char) valor.toString().charAt(0);
                                return true;
                            case Bool:
                                val_aux = Boolean.valueOf(valor.toString());
                                return true;
                            default:
                                return false;
                        }
                    } catch (Exception e) {
                        return false;
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

    //--------------------------------------------------------------------------------------------
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
                        } catch (Exception e) {
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

    //--------------------------------------------------------------------------------------------
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
                        } catch (Exception e) {
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

    //--------------------------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------------------------
    public TablaDeSimbolos getPadre() {
        return this.padre;
    }

    public Tipo getTipo(Object valor) {
        try {
            int val = (int) Double.parseDouble(valor.toString());
            return Tipo.Entero;
        } catch (Exception e1) {
            try {
                double val = Double.parseDouble(valor.toString());
                return Tipo.Decimal;
            } catch (Exception e2) {
                try {
                    boolean val = (boolean) Boolean.valueOf(valor.toString());
                    return Tipo.Bool;
                } catch (Exception e3) {
                    try {
                        char val = (char) Double.parseDouble(valor.toString());
                        return Tipo.Char;
                    } catch (Exception e4) {
                        return Tipo.Cadena;
                    }
                }

            }
        }
    }

    //-------------------------------------Area para Accesos a structs recursivos--------------------------------------
    public boolean existeAcceso(String id, ArrayList<String> identificadores) {
        lst.clear();
        Simbolo simbol = getSimbolo(id, this);
        if (simbol != null) {
            if (simbol.getTipo().getTipo() == Tipo.Struct) {
                return existeAcceso(identificadores, 0, (TablaDeSimbolos) simbol.getValor());
            }
            add_error("La variable " + id + " no es una fusion. ");
            return false;
        }
        add_error("La variable " + id + " no existe4. ");
        return false;
    }

    private boolean existeAcceso(ArrayList<String> identificadores, int nivel, TablaDeSimbolos padre) {
        if (padre != null) {
            for (Simbolo item : padre) {
                if (item.getId().equals(identificadores.get(nivel))) {
                    if ((nivel + 1) < identificadores.size()) {
                        if (item.getTipo().getTipo() == Tipo.Struct) {
                            return existeAcceso(identificadores, nivel + 1, (TablaDeSimbolos) item.getValor());
                        } else {
                            add_error(identificadores.get(nivel) + " no es tipo fusion");
                            return false;
                        }
                    } else {
                        return true;
                    }
                }
            }
            add_error("La ruta de accesos no existe");
        } else {
            add_error("No se ha instanciado el objeto " + identificadores.get(nivel - 1));
        }
        return false;
    }

    public void print() {
        System.out.println("==================================================================");
        this.forEach((item) -> {
            System.out.println("ID: " + item.getId() + " VALOR: " + item.getValor());
        });
        System.out.println("==================================================================");
    }

    public void setValorAccesos(String id, ArrayList<String> identificadores, Object valor) {

        //tabla donde se desea guardar el valor
        Simbolo sim = getSimbolo(id);
        TablaDeSimbolos tabla_actual = (TablaDeSimbolos) sim.getValor();

        for (Simbolo item : tabla_actual) {
            if (item.getId().equals(identificadores.get(0))) {
                if (item.getTipo().getTipo() == Tipo.Struct) {
                    //tabla que contiene el siguiente valor
                    TablaDeSimbolos tabla_resultado = (TablaDeSimbolos) valor;
                    tabla_resultado.print();
                    item.setValor(tabla_resultado);
                } else {
                    item.setValor(valor);
                }
            }
        }

    }

    private void setValorAccesos(ArrayList<String> identificadores, Object valor, int nivel, TablaDeSimbolos padre) {

    }

    public TablaDeSimbolos getTable(String id, ArrayList<String> identificadores) {
        lst.clear();
        Simbolo sim = getSimbolo(id);
        TablaDeSimbolos tabla_actual = (TablaDeSimbolos) sim.getValor();
        if (identificadores.size() == 1) {
            for (Simbolo item : tabla_actual) {
                if (item.getId().equals(identificadores.get(0))) {
                    return tabla_actual;
                }
            }
        }
        return getTable(sim, identificadores, 0);
    }

    private TablaDeSimbolos getTable(Simbolo sim, ArrayList<String> identificadores, int nivel) {
        TablaDeSimbolos tabla_actual = (TablaDeSimbolos) sim.getValor();
        for (Simbolo item : tabla_actual) {
            if (item.getId().equals(identificadores.get(nivel))) {
                if ((nivel + 1) < identificadores.size()) {
                    if (item.getTipo().getTipo() == Tipo.Struct) {
                        return getTable(item, identificadores, nivel + 1);

                    } else {
                        lst.add("La variable " + identificadores.get(nivel) + " no es un objeto");
                        return new TablaDeSimbolos();
                    }
                } else {
                    return tabla_actual;

                }
            }
        }

        return new TablaDeSimbolos();
    }

    //-------------------------------------Fin  para Accesos a structs recursivos--------------------------------------
    //-------------------------------------ACCESOS A REFERENCIAS-------------------------------------------------------
    public TablaDeSimbolos getEntorno(String id) {
        return getEntorno(id, this);
    }

    private TablaDeSimbolos getEntorno(String id, TablaDeSimbolos padre) {

        for (Simbolo item : padre) {
            if (item.getId().equals(id)) {
                return padre;
            }
        }

        if (padre.getPadre() != null) {
            return getEntorno(id, padre.getPadre());
        }
        return null;
    }

    //-----------------------------------------------------------------------------------------------------------------
    private void add_error(String mensaje) {
        lst.add(mensaje);
    }
//-------------------------------------------------------------------------------------------------------------------------------------------
    //Metodos para estructuras

    public void replace(String id, TablaDeSimbolos tabla) {

        for (Simbolo item : this) {
            if (item.getId().equals(id)) {
                item.setValor(tabla);
                return;
            }
        }
        if (this.getPadre() != null) {
            replace(id, tabla, this.getPadre());
        }

    }

    private void replace(String id, TablaDeSimbolos tabla, TablaDeSimbolos padre) {
        for (Simbolo item : padre) {
            if (item.getId().equals(id)) {
                item.setValor(tabla);
                return;
            }
        }
        if (this.getPadre() != null) {
            replace(id, tabla, padre.getPadre());
        }
    }

    public boolean containsKey(String id) {
        for (Simbolo item : this) {
            if (item.getId().equals(id)) {
                return true;
            }
        }
        if (this.getPadre() != null) {
            return containsKey(id, this.getPadre());
        }

        return false;
    }

    private boolean containsKey(String id, TablaDeSimbolos padre) {
        for (Simbolo item : padre) {
            if (item.getId().equals(id)) {
                return true;
            }
        }
        if (this.getPadre() != null) {
            return containsKey(id, padre.getPadre());
        }
        return false;
    }

    public TablaDeSimbolos get_struct(String id) {
        int index = 0;
        for (Simbolo item : this) {
            if (item.getId().equals(id)) {
                return (TablaDeSimbolos) this.get(index).getValor();
            }
            index++;
        }
        if (this.getPadre() != null) {
            return get_struct(id, this.getPadre());
        }
        return null;
    }

    private TablaDeSimbolos get_struct(String id, TablaDeSimbolos padre) {
        int index = 0;
        for (Simbolo item : padre) {
            if (item.getId().equals(id)) {
                return (TablaDeSimbolos) padre.get(index).getValor();
            }
            index++;
        }

        if (this.getPadre() != null) {
            return get_struct(id, padre.getPadre());
        }
        return null;
    }

    private Simbolo new_simbolo(Simbolo sim) {
        Simbolo new_simbolo = new Simbolo("", null);
        new_simbolo.copy(sim);
        return new_simbolo;

    }

    //-------------------------------------------------------------------------------------------------------------------------
    public boolean existeSimboloAmbienteActual(String id) {
        return existeSimboloAmbienteActual(id, this);
    }

    private boolean existeSimboloAmbienteActual(String id, TablaDeSimbolos padre) {
        for (Simbolo item : padre) {
            if (item.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void removeReferencias(String id) {
        Simbolo sim = getSimbolo(id);
        for (String item : sim.getReferencias()) {
            Simbolo aux = getSimbolo(item);
            aux.removeReferencia(id);
        }
        sim.setReferencias(new ArrayList<>());
    }
}
