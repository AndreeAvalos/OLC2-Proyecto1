/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import Tabla_Simbolos.TipoSimbolo;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Declaracion_Fusion implements Instruccion {

    String tipo, id, tipo2;
    Operacion valor;
    int line, column;

    public Declaracion_Fusion(String tipo, String id, String tipo2, int line, int column) {
        this.tipo = tipo;
        this.id = id;
        this.tipo2 = tipo2;
        this.line = line;
        this.column = column;
    }

    public Declaracion_Fusion(String tipo, String id, Operacion valor, int line, int column) {
        this.tipo = tipo;
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
        if (ts.getPadre() != null) {
            if (valor != null) {
                Object aux_val = valor.Ejecutar(ts);
                Simbolo sim = ts.getSimbolo(tipo);
                TablaDeSimbolos modelo = (TablaDeSimbolos) sim.getValor();
                if (aux_val != null) {
                    try {
                        TablaDeSimbolos resultado = (TablaDeSimbolos) aux_val;
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
                                Principal.add_error("No es posible hacer el casteo al tipo1: " + tipo, "Semantico", line, column);
                                return null;
                            }
                        }

                        if (!ts.existeSimboloAmbienteActual(id)) {
                            TablaDeSimbolos local = ts.get_struct(tipo);
                            TablaDeSimbolos nueva = new TablaDeSimbolos();

                            for (Simbolo item : local) {
                                Simbolo new_simbol = new Simbolo("", ")");
                                nueva.add(new_simbol.copy(item));
                            }
                            nueva.nombre = id;
                            ts.add(new Simbolo(new TipoSimbolo(Tipo.Struct, tipo), id, Tipo.FUSION));
                            ts.setValor(id, nueva);
                        } else {
                            Principal.add_error("La varaible \'" + id + "\' ya esta declarada", "Semantico", line, column);
                        }

                    } catch (Exception e) {
                        Principal.add_error("No es posible hacer el casteo al tipo2: " + tipo, "Semantico", line, column);
                        return null;
                    }
                    Principal.add_error("No es posible hacer el casteo al tipo3: " + tipo, "Semantico", line, column);
                }

            } else {
                if (!tipo.equals(tipo2)) {
                    Principal.add_error("El tipo: " + tipo + " con la fusion: " + tipo2, "Semantico", line, column);
                    return null;
                }
                if (ts.existeSimbolo(tipo)) {
                    if (!ts.existeSimboloAmbienteActual(id)) {
                        TablaDeSimbolos local = ts.get_struct(tipo);

                        TablaDeSimbolos nueva = new TablaDeSimbolos();

                        for (Simbolo item : local) {
                            Simbolo new_simbol = new Simbolo("", ")");
                            nueva.add(new_simbol.copy(item));
                        }
                        nueva.nombre = id;
                        ts.add(new Simbolo(new TipoSimbolo(Tipo.Struct, tipo), id, Tipo.FUSION));
                        ts.setValor(id, nueva);
                    } else {
                        Principal.add_error("La varaible \'" + id + "\' ya esta declarada", "Semantico", line, column);
                    }
                } else {
                    Principal.add_error("No existe el tipo: " + tipo, "Semantico", line, column);

                }
            }
        }
        return null;
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
        if (ts.getPadre() == null) {
            if (valor == null) {
                if (!tipo.equals(tipo2)) {
                    Principal.add_error("El tipo: " + tipo2 + " con la fusion: " + tipo2, "Semantico", line, column);
                    return;
                }
                if (ts.getPadre() == null) {
                    if (ts.existeSimbolo(tipo)) {
                        if (!ts.existeSimbolo(id)) {
                            TablaDeSimbolos local = ts.get_struct(tipo);

                            TablaDeSimbolos nueva = new TablaDeSimbolos();

                            for (Simbolo item : local) {
                                Simbolo new_simbol = new Simbolo("", ")");
                                nueva.add(new_simbol.copy(item));
                            }
                            nueva.nombre = id;
                            ts.add(new Simbolo(new TipoSimbolo(Tipo.Struct, tipo), id, Tipo.FUSION));
                            ts.setValor(id, nueva);
                        } else {
                            Principal.add_error("La varaible \'" + id + "\' ya esta declarada", "Semantico", line, column);
                        }
                    } else {
                        Principal.add_error("No existe el tipo: " + tipo, "Semantico", line, column);
                    }
                }
            } else {
                Object aux_val = null;
                try {
                    aux_val = valor.Ejecutar(ts);
                } catch (Exception e) {
                    Principal.add_error("No es posible hacer la operacion ", "Semantico", line, column);
                    return;
                }

                Simbolo sim = ts.getSimbolo(tipo);
                TablaDeSimbolos modelo = (TablaDeSimbolos) sim.getValor();
                if (aux_val != null) {
                    try {
                        TablaDeSimbolos resultado = (TablaDeSimbolos) aux_val;
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
                                Principal.add_error("No es posible hacer el casteo al tipo1: " + tipo, "Semantico", line, column);
                                return;
                            }
                        }

                        if (!ts.existeSimboloAmbienteActual(id)) {
                            TablaDeSimbolos local = ts.get_struct(tipo);
                            TablaDeSimbolos nueva = new TablaDeSimbolos();

                            for (Simbolo item : local) {
                                Simbolo new_simbol = new Simbolo("", ")");
                                nueva.add(new_simbol.copy(item));
                            }
                            nueva.nombre = id;
                            ts.add(new Simbolo(new TipoSimbolo(Tipo.Struct, tipo), id, Tipo.FUSION));
                            ts.setValor(id, nueva);
                        } else {
                            Principal.add_error("La varaible \'" + id + "\' ya esta declarada", "Semantico", line, column);
                        }

                    } catch (Exception e) {
                        Principal.add_error("No es posible hacer el casteo al tipo2: " + tipo, "Semantico", line, column);

                    }
                    Principal.add_error("No es posible hacer el casteo al tipo3: " + tipo, "Semantico", line, column);
                }
            }

        }
    }

    @Override
    public Tipo getType() {
        return Tipo.DECLARACION_FUSION;
    }

}
