package Configuracion;
import java_cup.runtime.Symbol;
import java_cup.runtime.*;



%%

%{
    //Código de usuario
    String tempcadena= "";
    int escape = 0;
    int contador = 0;

%}

%cupsym sym
%cup
%class Lexico_Configuracion
%public
%line
%char
%column
%full
%state cadena


//simbolos

Tllave_a = "{"
Tllave_c = "}"
Tcoma = ","
Tdospuntos = ":"




//palabras reservadas


Tproyecto = "proyecto"
Tarchivo = "archivo"
Tcarpeta = "carpeta"
Truta = "ruta"
Tcorrer = "correr"
Tconfiguracion = "configuracion"
Tfechamod = "fecha_mod"
Tnombre = "nombre"


//expresiones
   
space   = [\ \r\t\f\t]
enter   = [\ \n]

Comment = {EndOfLineComment} | {MultiLine}

// Comment can be the last line of the file, without line terminator.
EndOfLineComment     = "//" [^\r\n]* [\r|\n|\r\n]?
MultiLine   = "/*" [^/] ~"/>" | "</" "/"+ "*/"




%%

<YYINITIAL> {Tproyecto}      { return new Symbol(sym.Tproyecto, yyline, yycolumn,yytext());}
<YYINITIAL> {Tarchivo}      { return new Symbol(sym.Tarchivo, yyline, yycolumn,yytext());}
<YYINITIAL> {Tcarpeta}      { return new Symbol(sym.Tcarpeta, yyline, yycolumn,yytext());}
<YYINITIAL> {Truta}      { return new Symbol(sym.Truta, yyline, yycolumn,yytext());}
<YYINITIAL> {Tcorrer}      { return new Symbol(sym.Tcorrer, yyline, yycolumn,yytext());}
<YYINITIAL> {Tconfiguracion}      { return new Symbol(sym.Tconfiguracion, yyline, yycolumn,yytext());}
<YYINITIAL> {Tfechamod}      { return new Symbol(sym.Tfechamod, yyline, yycolumn,yytext());}
<YYINITIAL> {Tnombre}      { return new Symbol(sym.Tnombre, yyline, yycolumn,yytext());}
<YYINITIAL> {Tcoma}     {return new Symbol(sym.Tcoma, yyline, yycolumn,yytext());}
<YYINITIAL> {Tllave_a}    {return new Symbol(sym.Tllave_a, yyline, yycolumn,yytext());}
<YYINITIAL> {Tllave_c}    {return new Symbol(sym.Tllave_c, yyline, yycolumn,yytext());}
<YYINITIAL> {Tdospuntos}    {return new Symbol(sym.Tdospuntos, yyline, yycolumn,yytext());}
<YYINITIAL> [\"]        { yybegin(cadena);}
<YYINITIAL> {Comment} {/*iognore*/}
<YYINITIAL> {space}     { /*Espacios en blanco, ignorados*/ }
<YYINITIAL> {enter}     { /*Saltos de linea, ignorados*/}


<YYINITIAL> . {
        String errLex = "Error léxico : '"+yytext()+"' en la línea: "+(yyline+1)+" y columna: "+(yycolumn+1);
        System.out.println(errLex);
}
<cadena> {
       
[^\"]   {tempcadena+=yytext();}

[\"]    { String temp=tempcadena; tempcadena=""; yybegin(YYINITIAL); return  new Symbol(sym.Tcadena, yychar,yyline, temp);}


"\\\""  {tempcadena+='\"';}
"\\n"   {tempcadena+='\n';}
"\\\\"	{tempcadena+='\\';}
"\\'"	{tempcadena+='\'';}
"\\?"	{tempcadena+='?';}
"\\%"	{tempcadena+='%';}


        
}