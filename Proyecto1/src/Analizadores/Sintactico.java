
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package Analizadores;

import java.util.LinkedList;
import Instrucciones.*;
import java_cup.runtime.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import Instrucciones.Operacion.TipoOperacion;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class Sintactico extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public Sintactico() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public Sintactico(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Sintactico(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\034\000\002\002\004\000\002\002\003\000\002\003" +
    "\004\000\002\003\003\000\002\004\003\000\002\004\003" +
    "\000\002\005\005\000\002\005\007\000\002\011\003\000" +
    "\002\011\003\000\002\011\003\000\002\011\003\000\002" +
    "\006\006\000\002\007\004\000\002\007\005\000\002\007" +
    "\005\000\002\007\005\000\002\007\005\000\002\007\005" +
    "\000\002\007\005\000\002\007\005\000\002\007\003\000" +
    "\002\010\003\000\002\010\003\000\002\010\003\000\002" +
    "\010\003\000\002\010\003\000\002\010\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\056\000\014\040\014\041\007\042\011\043\016\137" +
    "\010\001\002\000\016\002\ufffe\040\ufffe\041\ufffe\042\ufffe" +
    "\043\ufffe\137\ufffe\001\002\000\016\002\ufffd\040\ufffd\041" +
    "\ufffd\042\ufffd\043\ufffd\137\ufffd\001\002\000\004\002\060" +
    "\001\002\000\004\137\ufff8\001\002\000\004\011\055\001" +
    "\002\000\004\137\ufff7\001\002\000\016\002\000\040\014" +
    "\041\007\042\011\043\016\137\010\001\002\000\016\002" +
    "\ufffc\040\ufffc\041\ufffc\042\ufffc\043\ufffc\137\ufffc\001\002" +
    "\000\004\137\ufff9\001\002\000\004\137\017\001\002\000" +
    "\004\137\ufff6\001\002\000\006\011\021\023\020\001\002" +
    "\000\016\002\ufffb\040\ufffb\041\ufffb\042\ufffb\043\ufffb\137" +
    "\ufffb\001\002\000\022\004\030\024\027\066\024\067\033" +
    "\137\031\140\026\141\025\143\023\001\002\000\022\004" +
    "\uffec\005\uffec\006\uffec\007\uffec\023\uffec\025\uffec\027\uffec" +
    "\033\uffec\001\002\000\022\004\uffe8\005\uffe8\006\uffe8\007" +
    "\uffe8\023\uffe8\025\uffe8\027\uffe8\033\uffe8\001\002\000\022" +
    "\004\uffe7\005\uffe7\006\uffe7\007\uffe7\023\uffe7\025\uffe7\027" +
    "\uffe7\033\uffe7\001\002\000\022\004\uffea\005\uffea\006\uffea" +
    "\007\uffea\023\uffea\025\uffea\027\uffea\033\uffea\001\002\000" +
    "\022\004\uffe9\005\uffe9\006\uffe9\007\uffe9\023\uffe9\025\uffe9" +
    "\027\uffe9\033\uffe9\001\002\000\022\004\030\024\027\066" +
    "\024\067\033\137\031\140\026\141\025\143\023\001\002" +
    "\000\022\004\030\024\027\066\024\067\033\137\031\140" +
    "\026\141\025\143\023\001\002\000\022\004\uffeb\005\uffeb" +
    "\006\uffeb\007\uffeb\023\uffeb\025\uffeb\027\uffeb\033\uffeb\001" +
    "\002\000\020\004\035\005\042\006\036\007\034\023\037" +
    "\027\041\033\040\001\002\000\022\004\uffe6\005\uffe6\006" +
    "\uffe6\007\uffe6\023\uffe6\025\uffe6\027\uffe6\033\uffe6\001\002" +
    "\000\022\004\030\024\027\066\024\067\033\137\031\140" +
    "\026\141\025\143\023\001\002\000\022\004\030\024\027" +
    "\066\024\067\033\137\031\140\026\141\025\143\023\001" +
    "\002\000\022\004\030\024\027\066\024\067\033\137\031" +
    "\140\026\141\025\143\023\001\002\000\016\002\ufffa\040" +
    "\ufffa\041\ufffa\042\ufffa\043\ufffa\137\ufffa\001\002\000\022" +
    "\004\030\024\027\066\024\067\033\137\031\140\026\141" +
    "\025\143\023\001\002\000\022\004\030\024\027\066\024" +
    "\067\033\137\031\140\026\141\025\143\023\001\002\000" +
    "\022\004\030\024\027\066\024\067\033\137\031\140\026" +
    "\141\025\143\023\001\002\000\022\004\ufff3\005\ufff3\006" +
    "\036\007\034\023\ufff3\025\ufff3\027\041\033\040\001\002" +
    "\000\022\004\uffef\005\uffef\006\uffef\007\uffef\023\uffef\025" +
    "\uffef\027\uffef\033\040\001\002\000\022\004\uffee\005\uffee" +
    "\006\uffee\007\uffee\023\uffee\025\uffee\027\uffee\033\uffee\001" +
    "\002\000\022\004\ufff1\005\ufff1\006\ufff1\007\ufff1\023\ufff1" +
    "\025\ufff1\027\ufff1\033\040\001\002\000\022\004\ufff2\005" +
    "\ufff2\006\036\007\034\023\ufff2\025\ufff2\027\041\033\040" +
    "\001\002\000\022\004\ufff0\005\ufff0\006\ufff0\007\ufff0\023" +
    "\ufff0\025\ufff0\027\ufff0\033\040\001\002\000\022\004\ufff4" +
    "\005\ufff4\006\036\007\034\023\ufff4\025\ufff4\027\041\033" +
    "\040\001\002\000\020\004\035\005\042\006\036\007\034" +
    "\025\053\027\041\033\040\001\002\000\022\004\uffed\005" +
    "\uffed\006\uffed\007\uffed\023\uffed\025\uffed\027\uffed\033\uffed" +
    "\001\002\000\016\002\uffff\040\uffff\041\uffff\042\uffff\043" +
    "\uffff\137\uffff\001\002\000\022\004\030\024\027\066\024" +
    "\067\033\137\031\140\026\141\025\143\023\001\002\000" +
    "\020\004\035\005\042\006\036\007\034\023\057\027\041" +
    "\033\040\001\002\000\016\002\ufff5\040\ufff5\041\ufff5\042" +
    "\ufff5\043\ufff5\137\ufff5\001\002\000\004\002\001\001\002" +
    "" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\056\000\016\002\005\003\011\004\003\005\004\006" +
    "\012\011\014\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\012\004\053\005\004\006\012\011\014" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\006\007\031\010\021\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\006\007\051\010\021\001\001\000\006\007" +
    "\050\010\021\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\006\007\047\010\021\001\001\000" +
    "\006\007\046\010\021\001\001\000\006\007\045\010\021" +
    "\001\001\000\002\001\001\000\006\007\044\010\021\001" +
    "\001\000\006\007\043\010\021\001\001\000\006\007\042" +
    "\010\021\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\006\007\055\010\021\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Sintactico$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Sintactico$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$Sintactico$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



	public LinkedList<Instruccion> AST = new LinkedList<Instruccion>();
	public static List<String> ErroresSintacticos = new ArrayList<>();
    public String salida = "" ; 
       
    public void syntax_error(Symbol s)
    {
        System.err.println("El analizador se recupero tras el error\nError en la Línea " + (s.left+1) +" Columna "+(s.right+1)+ ". Identificador "
        +s.value + " no reconocido." );  
        ErroresSintacticos.add("El analizador se recupero tras el error\nError en la Línea " + (s.left+1) +" Columna "+(s.right+1)+ ". Identificador "
        +s.value + " no reconocido." );
    }

    /**Metodo al que se llama en el momento en que ya no es posible una recuperación de errores.*/
    public void unrecovered_syntax_error(Symbol s) throws java.lang.Exception{
        System.err.println("El analizador No se recupero tras el error\nError en la Línea " + (s.right+1)+ " Columna "+(s.left+1)+". Identificador " +
        s.value + " no reconocido.");            
    }
    
    public void setSalida(String cadena)
    {
        salida = cadena;
    }



/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$Sintactico$actions {
  private final Sintactico parser;

  /** Constructor */
  CUP$Sintactico$actions(Sintactico parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$Sintactico$do_action_part00000000(
    int                        CUP$Sintactico$act_num,
    java_cup.runtime.lr_parser CUP$Sintactico$parser,
    java.util.Stack            CUP$Sintactico$stack,
    int                        CUP$Sintactico$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Sintactico$result;

      /* select the action based on the action number */
      switch (CUP$Sintactico$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= S EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).right;
		String start_val = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).value;
		RESULT = start_val;
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Sintactico$parser.done_parsing();
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // S ::= INSTRUCCIONES 
            {
              String RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		LinkedList<Instruccion> a = (LinkedList<Instruccion>)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 AST= a; 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("S",0, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // INSTRUCCIONES ::= INSTRUCCIONES INSTRUCCION 
            {
              LinkedList<Instruccion> RESULT =null;
		int Lista1left = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).left;
		int Lista1right = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).right;
		LinkedList<Instruccion> Lista1 = (LinkedList<Instruccion>)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).value;
		int instruleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int instruright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion instru = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 	Lista1.add(instru);
				RESULT = Lista1;
			
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("INSTRUCCIONES",1, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // INSTRUCCIONES ::= INSTRUCCION 
            {
              LinkedList<Instruccion> RESULT =null;
		int instruleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int instruright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion instru = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 	LinkedList<Instruccion> AST = new LinkedList<Instruccion>();
				AST.add(instru);
				RESULT = AST;
			
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("INSTRUCCIONES",1, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // INSTRUCCION ::= DECLARACION 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion a = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = a; 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("INSTRUCCION",2, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // INSTRUCCION ::= ASIGNACION 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion a = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = a; 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("INSTRUCCION",2, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // DECLARACION ::= TIPODATO Tid Tpycoma 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).right;
		Tipo a = (Tipo)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).right;
		String b = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).value;
		 RESULT = new Declaracion(a,a,b, bleft, bright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("DECLARACION",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // DECLARACION ::= TIPODATO Tid Tigual OPERACION_NUMERICA Tpycoma 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-4)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-4)).right;
		Tipo a = (Tipo)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-4)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-3)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-3)).right;
		String b = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-3)).value;
		int cleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).left;
		int cright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).right;
		Operacion c = (Operacion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).value;
		 RESULT = new Declaracion(a,a,b, new Asignacion(b,c,bleft,bright) , bleft, bright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("DECLARACION",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-4)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // TIPODATO ::= Tent 
            {
              Tipo RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = Tipo.Entero; 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("TIPODATO",7, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // TIPODATO ::= Tdec 
            {
              Tipo RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = Tipo.Decimal; 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("TIPODATO",7, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // TIPODATO ::= Tchr 
            {
              Tipo RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = Tipo.Char; 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("TIPODATO",7, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // TIPODATO ::= Tbul 
            {
              Tipo RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = Tipo.Bool; 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("TIPODATO",7, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // ASIGNACION ::= Tid Tigual OPERACION_NUMERICA Tpycoma 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-3)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-3)).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-3)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).right;
		Operacion b = (Operacion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).value;
		 RESULT = new Asignacion(a,b,aleft,aright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("ASIGNACION",4, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-3)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // OPERACION_NUMERICA ::= Tmenos OPERACION_NUMERICA 
            {
              Operacion RESULT =null;

              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("OPERACION_NUMERICA",5, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // OPERACION_NUMERICA ::= OPERACION_NUMERICA Tmas OPERACION_NUMERICA 
            {
              Operacion RESULT =null;

              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("OPERACION_NUMERICA",5, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // OPERACION_NUMERICA ::= OPERACION_NUMERICA Tmenos OPERACION_NUMERICA 
            {
              Operacion RESULT =null;

              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("OPERACION_NUMERICA",5, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // OPERACION_NUMERICA ::= OPERACION_NUMERICA Tpor OPERACION_NUMERICA 
            {
              Operacion RESULT =null;

              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("OPERACION_NUMERICA",5, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // OPERACION_NUMERICA ::= OPERACION_NUMERICA Tdiv OPERACION_NUMERICA 
            {
              Operacion RESULT =null;

              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("OPERACION_NUMERICA",5, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // OPERACION_NUMERICA ::= OPERACION_NUMERICA Tmod OPERACION_NUMERICA 
            {
              Operacion RESULT =null;

              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("OPERACION_NUMERICA",5, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // OPERACION_NUMERICA ::= OPERACION_NUMERICA Tpotencia OPERACION_NUMERICA 
            {
              Operacion RESULT =null;

              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("OPERACION_NUMERICA",5, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // OPERACION_NUMERICA ::= Tpar_a OPERACION_NUMERICA Tpar_c 
            {
              Operacion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).right;
		Operacion a = (Operacion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).value;
		 RESULT = a; 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("OPERACION_NUMERICA",5, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // OPERACION_NUMERICA ::= VALOR 
            {
              Operacion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Operacion a = (Operacion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = a;
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("OPERACION_NUMERICA",5, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // VALOR ::= Tid 
            {
              Operacion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(a,TipoOperacion.IDENTIFICADOR, aleft, aright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("VALOR",6, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // VALOR ::= Tentero 
            {
              Operacion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(a, aright, aleft); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("VALOR",6, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // VALOR ::= Tdecimal 
            {
              Operacion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(a, aright, aleft); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("VALOR",6, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // VALOR ::= Tcaracter 
            {
              Operacion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(a,TipoOperacion.CARACTER, aleft, aright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("VALOR",6, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // VALOR ::= Ttrue 
            {
              Operacion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(a,TipoOperacion.BOOL, aleft, aright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("VALOR",6, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // VALOR ::= Tfalse 
            {
              Operacion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(a,TipoOperacion.BOOL, aleft, aright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("VALOR",6, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$Sintactico$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$Sintactico$do_action(
    int                        CUP$Sintactico$act_num,
    java_cup.runtime.lr_parser CUP$Sintactico$parser,
    java.util.Stack            CUP$Sintactico$stack,
    int                        CUP$Sintactico$top)
    throws java.lang.Exception
    {
              return CUP$Sintactico$do_action_part00000000(
                               CUP$Sintactico$act_num,
                               CUP$Sintactico$parser,
                               CUP$Sintactico$stack,
                               CUP$Sintactico$top);
    }
}

}