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
chr cadena[10] = "hola";
ent arreglo2[]={2,3};
ent arreglo3[3]={6,6,6};
ab = 4+Funcion(2,3);
zro main(){


_imp("El peso de la fusion persona es: %e",_pesode(persona));
Metodo();
}
ent Funcion(ent c, ent d){

_imp("FUNCION CON VALORES, c: %e d: %e",a,dd);

regresar d;
}

zro Metodo(){

persona per2 = _reservar(_pesode(persona));
per2.edad = 100;
per.hola = per2;
persona per3 = _reservar(_pesode(persona));
per2.edad = 77;
per.hola.hola = per3;
per.hola.hola.edad = 16;

_imp("Se llamo a la funcion , Funcion: %s", cadena);

}