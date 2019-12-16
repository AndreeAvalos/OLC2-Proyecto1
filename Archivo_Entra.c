fusion persona {
chr name[10];
ent edad;
persona hola;
};

persona per = _reservar(_pesode(persona));
per.edad =55;

ent ab=7;
ent a=0;
ent b=*a;
ent dd=*b;

dd=3;
ent arreglo1[3];
arreglo1 = {1,2,3};
ent arreglo2[3]={2,3,3};
ent arreglo3[3][3][3]={{{6,6,6},{6,6,6},{6,6,6}},{{6,6,6},{6,6,6},{6,6,6}},{{6,6,6},{6,6,6},{6,6,6}}};
ab = 4+Funcion(2,3);
zro main(){


_imp("El peso de la fusion persona es: %e",_pesode(persona));
_imp("El peso del arreglo arreglo3 es: %e",_pesode(arreglo3));
Metodo("ESTO ES UNA PRUEBA DE CONCATENACION DE STRINGS");
}
ent Funcion(ent c, ent d){

_imp("FUNCION CON VALORES, c: %e d: %e",ab,dd);

regresar d;
}

zro Metodo(chr cadena[]){

persona per2 = _reservar(_pesode(persona));
per2.edad = 100;
per.hola = per2;
persona per3 = _reservar(_pesode(persona));
per2.edad = 77;
per.hola.hola = per3;
per.hola.hola.edad = 16;

_imp("AQUI VIENE LA CADENA: %s", cadena);

}