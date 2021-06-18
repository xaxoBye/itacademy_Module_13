# MODUL 13 SPRING - NIVELL 1
Entrega d'exercici Simple HTTP Service (M13) - nivell 1
- 
BASE DE DATOS interna en un arrayList de objetos Empleat.

PETICIONES por navegador o por POSTMAN

metodo=GET
http://localhost:8080/empleats/
http://localhost:8080/empleats/5
http://localhost:8080/empleats/feina/PROFESSOR


POSTMAN
CREAR UN EMPLEAT
metodo = POST
http://localhost:8080/empleats/
	En Postman (body)
	   {
         "nom": "Maria",
         "feina": "TÈCNIC",
          "salari": 2500.0
     }
 no necesitamos el idEmpleat porque se genera automáticamente
 
 
POSTMAN
ESBORRA UN EMPLEAT
metodo = DELETE
http://localhost:8080/empleats/2


POSTMAN
CANVIA UN EMPLEAT
metodo = PUT
http://localhost:8080/empleats/
En Postman (body)
	   {
	     "idEmpleat" : 2,
         "nom": "Maria",
         "feina": "GRANGER"
    }

