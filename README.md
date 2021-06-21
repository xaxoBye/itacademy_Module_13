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
  
# MODUL 13 SPRING - NIVELL 2 (AJAX)
Entrega d'exercici Simple HTTP Service (M13) - nivell 2
La base de dades

SQL
	CREATE SCHEMA itacademy;
	USE itacademy;
	CREATE TABLE `empleat` (
	`id_empleat` bigint(20) NOT NULL AUTO_INCREMENT,
	`feina` varchar(15) NOT NULL,
	`nom` varchar(150) NOT NULL,
	`salari` double NOT NULL,
	PRIMARY KEY (`id_empleat`)
	) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
	Dades

	INSERT INTO (`feina`,`nom`,`salari`) VALUES ('CONDUCTOR','Anna',1800.0);
	INSERT INTO (`feina`,`nom`,`salari`) VALUES ('PROFESSOR','Joan',2800.0);
	INSERT INTO (`feina`,`nom`,`salari`) VALUES ('DENTISTA','Cristina',2000.0);
	INSERT INTO (`feina`,`nom`,`salari`) VALUES ('TÈCNIC','Pol',2500.0);
	INSERT INTO (`feina`,`nom`,`salari`) VALUES ('GRANGER','Xavier',3800.0);
	INSERT INTO (`feina`,`nom`,`salari`) VALUES ('OPERARI','Julia',1200.0);
	INSERT INTO (`feina`,`nom`,`salari`) VALUES ('MECÀNIC','Carme',1300.0);

ARCHIU
src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/itacademy?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

URL del navegador. Pagina principal
http://localhost:8080/



# MODUL 13 SPRING - NIVELL 3 (Bootstrap)
Entrega d'exercici Simple HTTP Service (M13) - nivell 3

La base de dades
SQL:

	CREATE SCHEMA itacademy;
	USE itacademy;
	CREATE TABLE `empleat` (
	`id_empleat` bigint(20) NOT NULL AUTO_INCREMENT,
	`feina` varchar(15) NOT NULL,
	`nom` varchar(150) NOT NULL,
	`salari` double NOT NULL,
	PRIMARY KEY (`id_empleat`)
	) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
	Dades

	INSERT INTO (`feina`,`nom`,`salari`) VALUES ('CONDUCTOR','Anna',1800.0);
	INSERT INTO (`feina`,`nom`,`salari`) VALUES ('PROFESSOR','Joan',2800.0);
	INSERT INTO (`feina`,`nom`,`salari`) VALUES ('DENTISTA','Cristina',2000.0);
	INSERT INTO (`feina`,`nom`,`salari`) VALUES ('TÈCNIC','Pol',2500.0);
	INSERT INTO (`feina`,`nom`,`salari`) VALUES ('GRANGER','Xavier',3800.0);
	INSERT INTO (`feina`,`nom`,`salari`) VALUES ('OPERARI','Julia',1200.0);
	INSERT INTO (`feina`,`nom`,`salari`) VALUES ('MECÀNIC','Carme',1300.0);
	ARCHIU

src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/itacademy?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

 
URL del navegador. Pagina principal
http://localhost:8080/




