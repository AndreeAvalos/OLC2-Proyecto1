/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Arbol.Arbol;
import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import Tabla_Simbolos.TipoSimbolo;
import java.util.ArrayList;
import java.util.LinkedList;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Funcion implements Instruccion {

    public enum TipoFuncion {
        ARREGLO,
        STRUCT,
        NORMAL
    }
    Tipo tipo_simbolo;//numero,cadena,identificador etc
    ArrayList<Integer> num_niveles;
    String tipo_struct = "";
    String id;//nombre de variable;
    LinkedList<Instruccion> parametros, contenido;
    public LinkedList<Operacion> valores_parametros = new LinkedList<>();
    Arbol arreglo = new Arbol();
    TablaDeSimbolos local = new TablaDeSimbolos();
    TablaDeSimbolos local2 = new TablaDeSimbolos();
    TipoFuncion tipo_funcion;

    ;
    int line, column;
    boolean Llamada = false;

    public Funcion(Tipo tipo_simbolo, String id, LinkedList<Instruccion> parametros, LinkedList<Instruccion> contenido, int line, int column) {
        this.tipo_simbolo = tipo_simbolo;
        this.id = id;
        this.parametros = parametros;
        this.contenido = contenido;
        this.line = line;
        this.column = column;
        this.tipo_funcion = TipoFuncion.NORMAL;
    }

    public Funcion(Tipo tipo_simbolo, String id, LinkedList<Instruccion> contenido, int line, int column) {
        this.tipo_simbolo = tipo_simbolo;
        this.id = id;
        this.contenido = contenido;
        this.parametros = new LinkedList<>();
        this.line = line;
        this.column = column;
        this.tipo_funcion = TipoFuncion.NORMAL;
    }

    public Funcion(String tipo_struct, String id, LinkedList<Instruccion> parametros, LinkedList<Instruccion> contenido, int line, int column) {
        this.tipo_struct = tipo_struct;
        this.tipo_simbolo = Tipo.Struct;
        this.id = id;
        this.parametros = parametros;
        this.contenido = contenido;
        this.line = line;
        this.column = column;
        this.tipo_funcion = TipoFuncion.STRUCT;
    }

    public Funcion(String tipo_struct, String id, LinkedList<Instruccion> contenido, int line, int column) {
        this.tipo_struct = tipo_struct;
        this.id = id;
        this.tipo_simbolo = Tipo.Struct;
        this.contenido = contenido;
        this.line = line;
        this.column = column;
        this.tipo_funcion = TipoFuncion.STRUCT;
    }

    public Funcion(Tipo tipo_simbolo, ArrayList<Integer> num_niveles, String id, LinkedList<Instruccion> contenido, int line, int column) {
        this.tipo_simbolo = tipo_simbolo;
        this.num_niveles = num_niveles;
        this.id = id;
        this.contenido = contenido;
        this.tipo_funcion = TipoFuncion.ARREGLO;
        this.line = line;
        this.column = column;
    }

    public Funcion(Tipo tipo_simbolo, ArrayList<Integer> num_niveles, String id, LinkedList<Instruccion> parametros, LinkedList<Instruccion> contenido, int line, int column) {
        this.tipo_simbolo = tipo_simbolo;
        this.num_niveles = num_niveles;
        this.id = id;
        this.parametros = parametros;
        this.contenido = contenido;
        this.tipo_funcion = TipoFuncion.ARREGLO;
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

        if (Llamada == true) {
            local = new TablaDeSimbolos();
            for (Simbolo item : local2) {
                Simbolo new_simbol = new Simbolo("", "");
                new_simbol.copy(item);
                local.add(new_simbol);
            }

            local.setPadre(ts);
            if (local.size() == valores_parametros.size()) {
                for (int i = 0; i < valores_parametros.size(); i++) {
                    if (local.asignValorByIndex(i, valores_parametros.get(i).Ejecutar(local))) {
                        local.setValorByIndex(i, valores_parametros.get(i).Ejecutar(local));
                    } else {
                        Principal.add_error("No coincide el parametro con el tipo enviado", "Semantico", line, column);
                    }
                }
            } else {
                Principal.add_error("El numero de parametros no coincide.", "Semantico", line, column);
                return null;
            }
            if (tipo_funcion == TipoFuncion.NORMAL) {
                EjecutarNormal(ts);
            } else if (tipo_funcion == TipoFuncion.STRUCT) {
                EjecutarStruct(ts);
            } else if (tipo_funcion == TipoFuncion.ARREGLO) {
                EjecutarArreglo(ts);
            }
        }
        //aun no se como hacer esto.
        return null;
    }

    private void EjecutarNormal(TablaDeSimbolos ts) {

        for (Instruccion item : contenido) {
            if (item.getType() == Tipo.RETURN) {
                //aqui ponemos el valor en la funcion
                Object resultado = item.Ejecutar(local);

                if (local.asignValor(id, resultado)) {
                    local.setValor(id, resultado);
                } else {
                    //error porque no se puede hacer el casteo explicito
                    Principal.add_error("La valor retornado no concuerda con el tipo", "Semantico", line, column);
                }
                return;
            } else {
                item.Ejecutar(local);
            }
        }
        Principal.add_error("No se encontro la instruccion de retorno ", "Semantico", line, column);
    }

    private void EjecutarStruct(TablaDeSimbolos ts) {

        for (Instruccion item : contenido) {
            if (item.getType() == Tipo.RETURN) {
                //aqui ponemos el valor en la funcion
                Object aux_val = item.Ejecutar(local);
                TablaDeSimbolos resultado = (TablaDeSimbolos) aux_val;

                Simbolo sim = ts.getSimbolo(tipo_struct);
                TablaDeSimbolos modelo = (TablaDeSimbolos) sim.getValor();
                if (local.asignValor(id, resultado)) {
                    boolean encontrada;
                    for (Simbolo base : modelo) {
                        encontrada = false;
                        for (Simbolo item2 : resultado) {
                            if (base.getId().equals(item2.getId())) {
                                encontrada = true;
                                break;
                            }
                        }
                        if (!encontrada) {
                            Principal.add_error("No es posible hacer el casteo al tipo: " + tipo_struct, "Semantico", line, column);
                            return;
                        }
                    }
                    local.setValor(id, resultado);
                } else {
                    //error porque no se puede hacer el casteo explicito
                    Principal.add_error("La valor retornado no concuerda con el tipo", "Semantico", line, column);
                }
                return;
            } else {
                item.Ejecutar(local);
            }
        }
        Principal.add_error("No se encontro la instruccion de retorno ", "Semantico", line, column);
    }

    private void EjecutarArreglo(TablaDeSimbolos ts) {
        for (Instruccion item : contenido) {
            if (item.getType() == Tipo.RETURN) {
                //Obtenemos el valor que da de retorno
                Object resultado = item.Ejecutar(local);
                try {
                    Arbol arreglo_resultado = (Arbol) resultado;
                    if (arreglo.getNiveles() == arreglo_resultado.getNiveles()) {
                        ts.setValor(id, arreglo_resultado);
                    } else {
                        Principal.add_error("No coinciden las dimensiones", "Semantico", line, column);
                    }
                } catch (Exception e) {
                    Principal.add_error("La valor retornado no concuerda con el arreglo", "Semantico", line, column);
                }
                return;
            } else {
                item.Ejecutar(local);
            }
        }
        Principal.add_error("No se encontro la instruccion de retorno ", "Semantico", line, column);
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {

        if (ts.getPadre() == null) {
            if (!ts.existeSimbolo(id)) {
                if (tipo_funcion == TipoFuncion.NORMAL) {
                    ts.add(new Simbolo(new TipoSimbolo(tipo_simbolo, tipo_struct), id, this, Tipo.FUNCION));
                } else if (tipo_funcion == TipoFuncion.STRUCT) {
                    if (ts.existeSimbolo(tipo_struct)) {
                        Simbolo sim = ts.getSimbolo(tipo_struct);
                        if (sim.getTipo_instruccion() == Tipo.OBJETO) {
                            ts.add(new Simbolo(new TipoSimbolo(tipo_simbolo, tipo_struct), id, this, Tipo.FUNCION));
                        } else {
                            Principal.add_error("El tipo: " + tipo_struct + " no es una fusion.", "Semantico", line, column);
                            return;
                        }
                    } else {
                        Principal.add_error("El tipo: \'" + tipo_struct + "\' no esta declarada", "Semantico", line, column);
                        return;
                    }
                } else if (tipo_funcion == TipoFuncion.ARREGLO) {
                    arreglo = new Arbol();
                    arreglo.setNivel(num_niveles.size());
                    ts.add(new Simbolo(new TipoSimbolo(tipo_simbolo, "arreglo"), id, arreglo, this, Tipo.FUNCION));
                }
            } else {
                Principal.add_error("La funcion \'" + id + "\' ya esta declarada", "Semantico", line, column);
                //aqui va el mensaje de error que ya esta declarada la variable en el ambito
                return;
            }
            parametros.forEach((item) -> {
                item.Recolectar(local2);
            });
        }
    }

    @Override
    public Tipo getType() {
        return Tipo.FUNCION;
    }

}
