package Analizadores;

import java_cup.runtime.Symbol;



%%

%{
    //Código de usuario
    String tempcadena= "";
    int escape = 0;
    int contador = 0;

%}

%cupsym sym
%cup
%class Lexico
%public
%line
%char
%column
%full
%state cadena
%state cadena2


//simbolos

Tmenos = "-"
Tmas = "+"
Tpor = "*"
Tdiv = "/"
Tdiferente = "<>"
Tigual = "="
Tigualigual = "=="
Tmenor = "<"
Tmayor = ">"
Tor = "||"
Tand = "&&"
Tllave_a = "{"
Tllave_c = "}"
Tcorchete_a = "["
Tcorchete_c = "]"
Tpycoma = ";"
Tpar_a = "("
Tpar_c = ")"
Tcoma = ","
Tmod = "%"
Tmayorigual = ">="
Tmenorigual = "<="
Tnot = "!"
Tpotencia = "^"
Tdospuntos = ":"
Tincremento = "++"
Tdecremento = "--"
Tpunto = "."


//palabras reservadas


Tent = "ent"
Tdec = "dec"
Tchr = "chr"
Tbul = "bul"
Tnlo = "nlo"
Tpesode = "_pesode"
Treservar = "_reservar"
Tzro = "zro"
Tif = "if"
Twhile = "while"
Tfor = "for"
Trepeat = "repeat"
Tswitch = "switch"
Tcase = "case"
Tdefault = "default"
Tromper = "romper"
Tsiga = "siga"
Tdefinir = "#definir"
Tfusion = "fusion"
Timportar = "#importar"
Tregresar = "regresar"
Twhen = "when"
Trstring = "Rstring"
Ttrue = "true"
Tfalse = "false"
Tatxt = "_atxt"
Tconc = "_conc"
Taent = "_aent"
Tadec = "_adec"
Teqls = "_eqls"
Telse = "else"
Telseif = "else if"
Timp = "_imp"
Twrite = "_write"
Tapend = "_apend"
Twf = "_wf"
Tclose = "_close"
Tread = "_read"
Tlbl = "Rlbl"
Trtxt = "Rtxt"
Trtxta = "Rtxta"
Trtxtp = "Rtxtp"
Trtxtn = "Rtxtn"
Trbton = "Rbton"
Trmensage = "Rmensage"
Tiniciarventana = "iniciar_ventana"
Taldarclick = "al_dar_click"
Taltoyancho = "_alto_y_ancho"
Tsettexto = "settexto"
Tsetancho = "setancho"
Tsetalto = "setalto"
Tsetpos = "setpos"
Tgettexto = "gettexto"
Tgetancho = "getancho"
Tgetalto = "getalto"
Tgetpos = "getpos"
Tnuevogui = "_Nuevo_GUI"

//expresiones
   
Tid      = [A-Za-zñÑ][_0-9A-Za-zñÑ]*
Tdecimal = [0-9]+[.][0-9]+
Tentero = [0-9]+

space   = [\ \r\t\f\t]
enter   = [\ \n]

Comment = {EndOfLineComment} | {MultiLine}

// Comment can be the last line of the file, without line terminator.
EndOfLineComment     = "//" [^\r\n]* [\r|\n|\r\n]?
MultiLine   = "/*" [^/] ~"/>" | "</" "/"+ "*/"




%%

<YYINITIAL> {Tent}      { return new Symbol(sym.Tent, yyline, yycolumn,yytext());}
<YYINITIAL> {Tdec}      { return new Symbol(sym.Tdec, yyline, yycolumn,yytext());}
<YYINITIAL> {Tchr}      { return new Symbol(sym.Tchr, yyline, yycolumn,yytext());}
<YYINITIAL> {Tbul}      { return new Symbol(sym.Tbul, yyline, yycolumn,yytext());}
<YYINITIAL> {Tnlo}      { return new Symbol(sym.Tnlo, yyline, yycolumn,yytext());}
<YYINITIAL> {Tpesode}      { return new Symbol(sym.Tpesode, yyline, yycolumn,yytext());}
<YYINITIAL> {Treservar}      { return new Symbol(sym.Treservar, yyline, yycolumn,yytext());}
<YYINITIAL> {Tzro}      { return new Symbol(sym.Tzro, yyline, yycolumn,yytext());}
<YYINITIAL> {Tif}      { return new Symbol(sym.Tif, yyline, yycolumn,yytext());}
<YYINITIAL> {Twhile}      { return new Symbol(sym.Twhile, yyline, yycolumn,yytext());}
<YYINITIAL> {Tfor}      { return new Symbol(sym.Tfor, yyline, yycolumn,yytext());}
<YYINITIAL> {Trepeat}      { return new Symbol(sym.Trepeat, yyline, yycolumn,yytext());}
<YYINITIAL> {Tswitch}      { return new Symbol(sym.Tswitch, yyline, yycolumn,yytext());}
<YYINITIAL> {Tcase}      { return new Symbol(sym.Tcase, yyline, yycolumn,yytext());}
<YYINITIAL> {Tdefault}      { return new Symbol(sym.Tdefault, yyline, yycolumn,yytext());}
<YYINITIAL> {Tromper}      { return new Symbol(sym.Tromper, yyline, yycolumn,yytext());}
<YYINITIAL> {Tsiga}      { return new Symbol(sym.Tsiga, yyline, yycolumn,yytext());}
<YYINITIAL> {Tdefinir}      { return new Symbol(sym.Tdefinir, yyline, yycolumn,yytext());}
<YYINITIAL> {Tfusion}      { return new Symbol(sym.Tfusion, yyline, yycolumn,yytext());}
<YYINITIAL> {Timportar}      { return new Symbol(sym.Timportar, yyline, yycolumn,yytext());}
<YYINITIAL> {Tregresar}      { return new Symbol(sym.Tregresar, yyline, yycolumn,yytext());}
<YYINITIAL> {Twhen}      { return new Symbol(sym.Twhen, yyline, yycolumn,yytext());}
<YYINITIAL> {Tfalse}      { return new Symbol(sym.Tfalse, yyline, yycolumn,yytext());}
<YYINITIAL> {Ttrue}      { return new Symbol(sym.Ttrue, yyline, yycolumn,yytext());}
<YYINITIAL> {Tregresar}      { return new Symbol(sym.Tregresar, yyline, yycolumn,yytext());}
<YYINITIAL> {Tatxt}      { return new Symbol(sym.Tatxt, yyline, yycolumn,yytext());}
<YYINITIAL> {Tconc}      { return new Symbol(sym.Tconc, yyline, yycolumn,yytext());}
<YYINITIAL> {Taent}      { return new Symbol(sym.Taent, yyline, yycolumn,yytext());}
<YYINITIAL> {Tadec}      { return new Symbol(sym.Tadec, yyline, yycolumn,yytext());}
<YYINITIAL> {Teqls}      { return new Symbol(sym.Teqls, yyline, yycolumn,yytext());}
<YYINITIAL> {Telse}      { return new Symbol(sym.Telse, yyline, yycolumn,yytext());}
<YYINITIAL> {Telseif}      { return new Symbol(sym.Telseif, yyline, yycolumn,yytext());}
<YYINITIAL> {Timp}      { return new Symbol(sym.Timp, yyline, yycolumn,yytext());}
<YYINITIAL> {Twrite}      { return new Symbol(sym.Twrite, yyline, yycolumn,yytext());}
<YYINITIAL> {Tapend}      { return new Symbol(sym.Tapend, yyline, yycolumn,yytext());}
<YYINITIAL> {Twf}      { return new Symbol(sym.Twf, yyline, yycolumn,yytext());}
<YYINITIAL> {Tclose}      { return new Symbol(sym.Tclose, yyline, yycolumn,yytext());}
<YYINITIAL> {Tread}      { return new Symbol(sym.Tread, yyline, yycolumn,yytext());}
<YYINITIAL> {Tlbl}      { return new Symbol(sym.Tlbl, yyline, yycolumn,yytext());}
<YYINITIAL> {Trtxt}      { return new Symbol(sym.Trtxt, yyline, yycolumn,yytext());}
<YYINITIAL> {Trtxta}      { return new Symbol(sym.Trtxta, yyline, yycolumn,yytext());}
<YYINITIAL> {Trtxtp}      { return new Symbol(sym.Trtxtp, yyline, yycolumn,yytext());}
<YYINITIAL> {Trtxtn}      { return new Symbol(sym.Trtxtn, yyline, yycolumn,yytext());}
<YYINITIAL> {Trbton}      { return new Symbol(sym.Trbton, yyline, yycolumn,yytext());}
<YYINITIAL> {Trmensage}      { return new Symbol(sym.Trmensage, yyline, yycolumn,yytext());}
<YYINITIAL> {Tiniciarventana}      { return new Symbol(sym.Tiniciarventana, yyline, yycolumn,yytext());}
<YYINITIAL> {Taldarclick}      { return new Symbol(sym.Taldarclick, yyline, yycolumn,yytext());}
<YYINITIAL> {Tnuevogui}      { return new Symbol(sym.Tnuevogui, yyline, yycolumn,yytext());}
<YYINITIAL> {Taltoyancho}      { return new Symbol(sym.Taltoyancho, yyline, yycolumn,yytext());}
<YYINITIAL> {Tsettexto}      { return new Symbol(sym.Tsettexto, yyline, yycolumn,yytext());}
<YYINITIAL> {Tsetancho}      { return new Symbol(sym.Tsetancho, yyline, yycolumn,yytext());}
<YYINITIAL> {Tsetalto}      { return new Symbol(sym.Tsetalto, yyline, yycolumn,yytext());}
<YYINITIAL> {Tsetpos}      { return new Symbol(sym.Tsetpos, yyline, yycolumn,yytext());}
<YYINITIAL> {Tgettexto}      { return new Symbol(sym.Tgettexto, yyline, yycolumn,yytext());}
<YYINITIAL> {Tgetancho}      { return new Symbol(sym.Tgetancho, yyline, yycolumn,yytext());}
<YYINITIAL> {Tgetalto}      { return new Symbol(sym.Tgetalto, yyline, yycolumn,yytext());}
<YYINITIAL> {Tgetpos}      { return new Symbol(sym.Tgetpos, yyline, yycolumn,yytext());}
<YYINITIAL> {Trstring}      { return new Symbol(sym.Trstring, yyline, yycolumn,yytext());}



<YYINITIAL> {Tmenos}     {return new Symbol(sym.Tmenos, yyline, yycolumn,yytext());}
<YYINITIAL> {Tmas}     {return new Symbol(sym.Tmas, yyline, yycolumn,yytext());}
<YYINITIAL> {Tpor}     {return new Symbol(sym.Tpor, yyline, yycolumn,yytext());}
<YYINITIAL> {Tdiv}     {return new Symbol(sym.Tdiv, yyline, yycolumn,yytext());}
<YYINITIAL> {Tdiferente}     {return new Symbol(sym.Tdiferente, yyline, yycolumn,yytext());}
<YYINITIAL> {Tigual}     {return new Symbol(sym.Tigual, yyline, yycolumn,yytext());}
<YYINITIAL> {Tigualigual}     {return new Symbol(sym.Tigualigual, yyline, yycolumn,yytext());}
<YYINITIAL> {Tmenor}     {return new Symbol(sym.Tmenor, yyline, yycolumn,yytext());}
<YYINITIAL> {Tmayor}     {return new Symbol(sym.Tmayor, yyline, yycolumn,yytext());}
<YYINITIAL> {Tand}     {return new Symbol(sym.Tand, yyline, yycolumn,yytext());}
<YYINITIAL> {Tor}     {return new Symbol(sym.Tor, yyline, yycolumn,yytext());}
<YYINITIAL> {Tfalse}     {return new Symbol(sym.Tfalse, yyline, yycolumn,yytext());}
<YYINITIAL> {Ttrue}     {return new Symbol(sym.Ttrue, yyline, yycolumn,yytext());}
<YYINITIAL> {Tcoma}     {return new Symbol(sym.Tcoma, yyline, yycolumn,yytext());}
<YYINITIAL> {Tpar_a}     {return new Symbol(sym.Tpar_a, yyline, yycolumn,yytext());}
<YYINITIAL> {Tpar_c}     {return new Symbol(sym.Tpar_c, yyline, yycolumn,yytext());}
<YYINITIAL> {Tllave_a}    {return new Symbol(sym.Tllave_a, yyline, yycolumn,yytext());}
<YYINITIAL> {Tllave_c}    {return new Symbol(sym.Tllave_c, yyline, yycolumn,yytext());}
<YYINITIAL> {Tcorchete_a}    {return new Symbol(sym.Tcorchete_a, yyline, yycolumn,yytext());}
<YYINITIAL> {Tcorchete_c}    {return new Symbol(sym.Tcorchete_c, yyline, yycolumn,yytext());}
<YYINITIAL> {Tpycoma}    {return new Symbol(sym.Tpycoma, yyline, yycolumn,yytext());}
<YYINITIAL> {Tnot}    {return new Symbol(sym.Tnot, yyline, yycolumn,yytext());}
<YYINITIAL> {Tmayorigual}    {return new Symbol(sym.Tmayorigual, yyline, yycolumn,yytext());}
<YYINITIAL> {Tmenorigual}    {return new Symbol(sym.Tmenorigual, yyline, yycolumn,yytext());}
<YYINITIAL> {Tmod}    {return new Symbol(sym.Tmod, yyline, yycolumn,yytext());}
<YYINITIAL> {Tpotencia}    {return new Symbol(sym.Tpotencia, yyline, yycolumn,yytext());}
<YYINITIAL> {Tdospuntos}    {return new Symbol(sym.Tdospuntos, yyline, yycolumn,yytext());}
<YYINITIAL> {Tpunto}    {return new Symbol(sym.Tpunto, yyline, yycolumn,yytext());}
<YYINITIAL> {Tincremento}    {return new Symbol(sym.Tincremento, yyline, yycolumn,yytext());}
<YYINITIAL> {Tdecremento}    {return new Symbol(sym.Tdecremento, yyline, yycolumn,yytext());}



<YYINITIAL> {Tentero}    { return new Symbol(sym.Tentero, yyline, yycolumn,yytext());}
<YYINITIAL> {Tdecimal}    { return new Symbol(sym.Tdecimal, yyline, yycolumn,yytext());}
<YYINITIAL> {Tid}        {return new Symbol(sym.Tid, yyline, yycolumn,yytext());}
<YYINITIAL> [\"]        { yybegin(cadena);}
<YYINITIAL> [\']        { yybegin(cadena2); tempcadena+="\'"; escape=0; contador=0;}
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
<cadena2> {
        [\'] { 
                if(escape==0){
                    if(contador>=1)
                    {
                        String tmp=tempcadena+"\'"; 
                        tempcadena=""; yybegin(YYINITIAL);  
                        return new Symbol(sym.Tcaracter, yychar,yyline,tmp); 
                    }
                    else{
                        String tmp=tempcadena; tempcadena="";  
                        System.out.println("Tipo de dato caracter tiene mas de 1 caracter"); 
                        yybegin(YYINITIAL);
                    }
                }
                else{
                    tempcadena+=yytext();
                    escape=0;
                    contador++;
                }
            }
        [\n] {
                if(escape==0){
                    String tmp=tempcadena; tempcadena="";  
                    System.out.println("Se esperaba cierre de caracter (\")."); 
                    yybegin(YYINITIAL);
                }else{
                    String tmp=tempcadena; tempcadena="";  
                    System.out.println("Se esperaba caracter de escape o cierre de caracter;"); 
                    yybegin(YYINITIAL); 
                }
            }
        [\\] { 
                tempcadena+=yytext();
                if(escape==0){
                 escape=1;
                }else{
                 escape=0;
                 contador++;
                }
             }
        [\"] {                
                if(escape==0){
                    String tmp=tempcadena; tempcadena="";  
                    System.out.println("Caracter especial  sin escape definido"); 
                    yybegin(YYINITIAL);              
                }else{
                 tempcadena+=yytext();
                 escape=0;
                 contador++;
                }
             }
        [\?] { 
                contador++;
                if(escape==0){
                    tempcadena+=yytext();              
                }else{
                 tempcadena+=yytext();
                 escape=0;
                }
             }
        [\%] { 
                contador++;
                if(escape==0){
                    tempcadena+=yytext();              
                }else{
                 tempcadena+=yytext();
                 escape=0;
                }
             }
        [n] { 
                contador++;
                if(escape==0){
                 tempcadena+=yytext();
                }else{
                 tempcadena+=yytext();
                 escape=0;
                }
             }
        [^\"] { contador++; tempcadena+=yytext();}
}