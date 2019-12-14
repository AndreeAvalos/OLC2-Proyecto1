/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Arbol.Arbol;
import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import java.util.Objects;
import proyecto1.Principal;

/**
 *
 * @author Andree
 */
public class Operacion implements Instruccion {

    public enum TipoOperacion {
        SUMA,
        RESTA,
        MULTIPLICACION,
        DIVISION,
        NEGATIVO,
        NUMERO,
        MODULAR,
        POTENCIA,
        IDENTIFICADOR,
        CADENA,
        MAYOR_QUE,
        MENOR_QUE,
        MAYOR_IGUAL,
        MENOR_IGUAL,
        IGUAL_IGUAL,
        DIFERENTE,
        CONCATENACION,
        CARACTER,
        BOOL,
        NOT,
        AND,
        OR,
        FUNCION,
        PESODE
    }
    Operacion operadorDer;
    Operacion operadorIzq;
    TipoOperacion tipo;
    Object valor;
    int line, column;

    public Operacion(Operacion operadorDer, Operacion operadorIzq, TipoOperacion tipo, int line, int column) {
        this.operadorDer = operadorDer;
        this.operadorIzq = operadorIzq;
        this.tipo = tipo;
        this.line = line;
        this.column = column;
    }

    public Operacion(Operacion operadorIzq, TipoOperacion tipo, int line, int column) {
        this.operadorIzq = operadorIzq;
        this.tipo = tipo;
        this.line = line;
        this.column = column;
    }

    public Operacion(Object valor, TipoOperacion tipo, int line, int column) {
        this.tipo = tipo;
        this.valor = valor;
        this.line = line;
        this.column = column;
    }

    public Operacion(String valor, int line, int column) {
        this.valor = valor;
        this.line = line;
        this.column = column;
        this.tipo = TipoOperacion.NUMERO;
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
        try {
            double aux2 = 0.0;
            switch (tipo) {
                case DIVISION:
                    return (Double) operadorIzq.Ejecutar(ts) / (Double) operadorDer.Ejecutar(ts);
                case MULTIPLICACION:
                    return (Double) operadorIzq.Ejecutar(ts) * (Double) operadorDer.Ejecutar(ts);
                case RESTA:
                    return (Double) operadorIzq.Ejecutar(ts) - (Double) operadorDer.Ejecutar(ts);
                case SUMA:
                    return (Double) operadorIzq.Ejecutar(ts) + (Double) operadorDer.Ejecutar(ts);
                case NEGATIVO:
                    return (Double) operadorIzq.Ejecutar(ts) * -1;
                case NUMERO:
                    return Double.parseDouble(valor.toString());
                case IDENTIFICADOR:
                    Object val = ts.getValor(valor.toString());
                    Simbolo sim = ts.getSimbolo(valor.toString());
                    if (sim != null) {
                        if (sim.getTipo_instruccion() == Tipo.ARREGLO) {
                            Arbol aux = (Arbol) val;
                            return aux;
                        } else if (sim.getTipo_instruccion() == Tipo.FUSION) {
                            String tipo_struct = sim.getTipo().getAsignado();
                            if (ts.existeSimbolo(tipo_struct)) {
                                return val;
                            }
                        } else {
                            aux2 = Double.parseDouble(val.toString());
                            return aux2;
                        }
                    }
                    return null;
                case CADENA:
                    return valor.toString();
                case MAYOR_QUE:
                    return ((Double) operadorIzq.Ejecutar(ts)) > ((Double) operadorDer.Ejecutar(ts));
                case MENOR_QUE:
                    return ((Double) operadorIzq.Ejecutar(ts)) < ((Double) operadorDer.Ejecutar(ts));
                case MAYOR_IGUAL:
                    return ((Double) operadorIzq.Ejecutar(ts)) >= ((Double) operadorDer.Ejecutar(ts));
                case MENOR_IGUAL:
                    return ((Double) operadorIzq.Ejecutar(ts)) <= ((Double) operadorDer.Ejecutar(ts));
                case IGUAL_IGUAL:
                    return Objects.equals((Double) operadorIzq.Ejecutar(ts), (Double) operadorDer.Ejecutar(ts));
                case DIFERENTE:
                    return !Objects.equals((Double) operadorIzq.Ejecutar(ts), (Double) operadorDer.Ejecutar(ts));
                case CONCATENACION:
                    return operadorIzq.Ejecutar(ts).toString() + operadorDer.Ejecutar(ts).toString();
                case CARACTER:
                    int aux = (int) valor.toString().charAt(1);
                    return (double) aux;
                case BOOL:
                    return Boolean.valueOf(valor.toString());
                case MODULAR:
                    return (Double) operadorIzq.Ejecutar(ts) % (Double) operadorDer.Ejecutar(ts);
                case POTENCIA:
                    return Math.pow((Double) operadorIzq.Ejecutar(ts), (Double) operadorDer.Ejecutar(ts));
                case NOT:
                    return !((boolean) operadorIzq.Ejecutar(ts));
                case OR:
                    return ((boolean) operadorIzq.Ejecutar(ts)) || ((boolean) operadorDer.Ejecutar(ts));
                case AND:
                    return ((boolean) operadorIzq.Ejecutar(ts)) && ((boolean) operadorDer.Ejecutar(ts));
                case FUNCION:
                    Llamada funcion = (Llamada) valor;
                    String id_funcion = funcion.id;
                    Instruccion aux3 = ts.getContenido(funcion.id);
                    Simbolo simbolo = ts.getSimbolo(id_funcion);
                    if (aux3.getType() == Tipo.METODO) {
                        System.out.println("No se Puede operar un metodo");
                        return 0.0;
                    } else {
                        funcion.Ejecutar(ts);
                        if (simbolo.getTipo().getTipo() == Tipo.Struct) {
                            return ts.getValor(id_funcion);
                        } else {
                            aux2 = Double.parseDouble(ts.getValor(id_funcion).toString());
                            return aux2;
                        }
                    }
                case PESODE:
                    String id = valor.toString();
                    if (ts.existeSimbolo(id)) {
                        Simbolo simbolo_aux = ts.getSimbolo(id);
                        if (simbolo_aux.getTipo().getTipo() == Tipo.Struct) {
                            return ((TablaDeSimbolos) simbolo_aux.getValor()).size();
                        } else {
                            Principal.add_error("No es un tipo struct. ", "Semantico", line, column);
                            return null;
                        }
                    } else {
                        Principal.add_error("No existe el tipo: " + id, "Semantico", line, column);
                        return null;
                    }
                default:
                    return null;

            }
        } catch (Exception e) {
            Principal.add_error("No es posible hacer la operacionxx.", "Semantico", line, column);
            return null;
        }
        
    }

    @Override
    public void Recolectar(TablaDeSimbolos ts) {
        //se dejo vacio con intencion
    }

    @Override
    public Tipo getType() {
        return Tipo.OPERACION;
    }
}
