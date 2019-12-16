/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Arbol.Arbol;
import Tabla_Simbolos.Simbolo;
import Tabla_Simbolos.TablaDeSimbolos;
import java.util.ArrayList;
import java.util.LinkedList;
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
        PESODE,
        ACCESO_ARREGLO,
        ACCESO_STRUCT,
        NULO
    }

    String id_objeto;
    LinkedList<Operacion> accesos;
    ArrayList<String> identificadores;
    Operacion operadorDer;
    Operacion operadorIzq;
    TipoOperacion tipo;
    Object valor;
    int line, column;

    public Operacion(Operacion operadorIzq, Operacion operadorDer, TipoOperacion tipo, int line, int column) {
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

    public Operacion(String id_objeto, LinkedList<Operacion> accesos, TipoOperacion tipo, int line, int column) {
        this.id_objeto = id_objeto;
        this.accesos = accesos;
        this.tipo = tipo;
        this.line = line;
        this.column = column;
    }

    public Operacion(String id_objeto, ArrayList<String> identificadores, TipoOperacion tipo, int line, int column) {
        this.id_objeto = id_objeto;
        this.identificadores = identificadores;
        this.tipo = tipo;
        this.line = line;
        this.column = column;
    }

    public Operacion(TipoOperacion tipo, int line, int column) {
        this.tipo = tipo;
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
        try {
            double aux2 = 0.0;
            switch (tipo) {
                case NULO:
                    return null;
                case DIVISION:
                    return (Double) Double.parseDouble(operadorIzq.Ejecutar(ts).toString()) / (Double) Double.parseDouble(operadorDer.Ejecutar(ts).toString());
                case MULTIPLICACION:
                    return (Double) Double.parseDouble(operadorIzq.Ejecutar(ts).toString()) * (Double) Double.parseDouble(operadorDer.Ejecutar(ts).toString());
                case RESTA:
                    return (Double) Double.parseDouble(operadorIzq.Ejecutar(ts).toString()) - (Double) Double.parseDouble(operadorDer.Ejecutar(ts).toString());
                case SUMA:
                    return (Double) Double.parseDouble(operadorIzq.Ejecutar(ts).toString()) + (Double) Double.parseDouble(operadorDer.Ejecutar(ts).toString());
                case NEGATIVO:
                    return (Double) Double.parseDouble(operadorIzq.Ejecutar(ts).toString()) * -1;
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
                            return val;
                        }
                    }
                    return null;
                case CADENA:
                    Arbol arbol_aux2 = new Arbol();
                    arbol_aux2.convertirString(valor.toString());
                    return arbol_aux2;
                case MAYOR_QUE:
                    return ((Double) Double.parseDouble(operadorIzq.Ejecutar(ts).toString())) > ((Double) Double.parseDouble(operadorDer.Ejecutar(ts).toString()));
                case MENOR_QUE:
                    return ((Double) Double.parseDouble(operadorIzq.Ejecutar(ts).toString())) < ((Double) Double.parseDouble(operadorDer.Ejecutar(ts).toString()));
                case MAYOR_IGUAL:
                    return ((Double) Double.parseDouble(operadorIzq.Ejecutar(ts).toString())) >= ((Double) Double.parseDouble(operadorDer.Ejecutar(ts).toString()));
                case MENOR_IGUAL:
                    return ((Double) Double.parseDouble(operadorIzq.Ejecutar(ts).toString())) <= ((Double) Double.parseDouble(operadorDer.Ejecutar(ts).toString()));
                case IGUAL_IGUAL:
                    Object ope1 = operadorIzq.Ejecutar(ts);
                    Object ope2 = operadorDer.Ejecutar(ts);

                    if (ope1 == null || ope2 == null) {
                        return ope1 == ope2;
                    }

                    return Objects.equals((Double) Double.parseDouble(ope1.toString()), (Double) Double.parseDouble(ope2.toString()));
                case DIFERENTE:
                    ope1 = operadorIzq.Ejecutar(ts);
                    ope2 = operadorDer.Ejecutar(ts);

                    if (ope1 == null || ope2 == null) {
                        return ope1 != ope2;
                    }

                    return !Objects.equals((Double) Double.parseDouble(ope1.toString()), (Double) Double.parseDouble(ope2.toString()));
                case CONCATENACION:
                    return operadorIzq.Ejecutar(ts).toString() + operadorDer.Ejecutar(ts).toString();
                case CARACTER:
                    int aux = (int) valor.toString().charAt(1);
                    return (double) aux;
                case BOOL:
                    return Boolean.valueOf(valor.toString());
                case MODULAR:
                    return (Double) Double.parseDouble(operadorIzq.Ejecutar(ts).toString()) % (Double) Double.parseDouble(operadorDer.Ejecutar(ts).toString());
                case POTENCIA:
                    return Math.pow((Double) Double.parseDouble(operadorIzq.Ejecutar(ts).toString()), (Double) Double.parseDouble(operadorDer.Ejecutar(ts).toString()));
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
                        } else if (simbolo.getTipo().getAsignado().equals("arreglo")) {
                            Arbol ar = (Arbol) ts.getValor(id_funcion);
                            return ar;
                        } else {
                            return ts.getValor(id_funcion).toString();

                        }
                    }
                case PESODE:
                    String id = valor.toString();
                    if (ts.existeSimbolo(id)) {
                        Simbolo simbolo_aux = ts.getSimbolo(id);
                        if (simbolo_aux.getTipo().getTipo() == Tipo.Struct) {
                            return ((TablaDeSimbolos) simbolo_aux.getValor()).size();
                        } else if (simbolo_aux.getTipo_instruccion() == Tipo.ARREGLO) {
                            Arbol arbol_aux = (Arbol) simbolo_aux.getValor();
                            return (int) Double.parseDouble(arbol_aux.getSize() + "");

                        } else {
                            Principal.add_error("No es un tipo struct. ", "Semantico", line, column);
                            return null;
                        }
                    } else {
                        Principal.add_error("No existe el tipo: " + id, "Semantico", line, column);
                        return null;
                    }
                case ACCESO_ARREGLO:
                    ArrayList<Integer> rutas = new ArrayList<>();
                    for (Operacion item : accesos) {
                        String result = item.Ejecutar(ts).toString();
                        int indice = (int) Double.parseDouble(result);
                        rutas.add(indice);
                    }
                    if (ts.existeSimbolo(id_objeto)) {
                        Simbolo simbolo_aux = ts.getSimbolo(id_objeto);

                        Arbol arbol = (Arbol) simbolo_aux.getValor();
                        return arbol.get(rutas);

                    } else {
                        Principal.add_error("No existe el tipo: " + id_objeto, "Semantico", line, column);
                        return null;
                    }
                case ACCESO_STRUCT:
                    if (ts.existeAcceso(id_objeto, identificadores)) {
                        //vamos y le damos el valor al objeto

//            Object val = valor.Ejecutar(ts);
                        TablaDeSimbolos local = ts.getTable(id_objeto, identificadores);
                        return local.getValor(identificadores.get(identificadores.size() - 1));
//            System.out.println("Valor: " + accesos.get(accesos.size() - 1) + " valor: " + val);
//            ts.setValorAccesos(id, accesos, val);
//            System.out.println("Valor: " + accesos.get(accesos.size() - 1) + " valor: " + val);
//            System.out.println("----");
                    } else {
                        ts.lst.forEach((item) -> {
                            Principal.add_error(item, "Semantico", line, column);
                        });
                    }

                default:
                    return null;

            }
        } catch (Exception e) {
            Principal.add_error("No es posible hacer la operacion.", "Semantico", line, column);
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
