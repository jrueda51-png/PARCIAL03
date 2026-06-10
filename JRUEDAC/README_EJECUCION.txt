PROYECTO: ProyectoDatosPersonales_JRUEDAC

REQUISITOS:
1. Oracle 10g funcionando.
2. Usuario de base de datos: JRUEDAC
3. Contraseña de base de datos: JRUEDAC
4. Apache NetBeans IDE.
5. Librería JDBC: ojdbc14.jar

PASO 1 - CORREGIR ERROR ORA-01031 EN LA VIEW:
Entrar a SQL*Plus como SYSTEM:

CONN SYSTEM/tu_clave

Ejecutar:

GRANT CREATE VIEW TO JRUEDAC;

O ejecutar el archivo:
database/00_solo_corregir_privilegio_vista_SYSTEM.sql

PASO 2 - CREAR LA BASE DE DATOS:
Entrar a SQL*Plus con el usuario JRUEDAC:

CONN JRUEDAC/JRUEDAC

Ejecutar:

@database/01_crear_bd_JRUEDAC.sql

PASO 3 - AGREGAR OJDBC14:
Copiar ojdbc14.jar dentro de la carpeta lib del proyecto:

lib/ojdbc14.jar

PASO 4 - ABRIR EN NETBEANS:
Abrir NetBeans.
Ir a File > Open Project.
Seleccionar la carpeta ProyectoDatosPersonales_JRUEDAC.
Click derecho sobre el proyecto > Clean and Build.
Luego Run.

NOTA SOBRE LA CONEXION:
La clase de conexión usa:

jdbc:oracle:thin:@localhost:1521:XE
usuario: JRUEDAC
clave: JRUEDAC

Si tu Oracle usa ORCL, cambia XE por ORCL en:
src/com/jruedac/conexion/ConexionBD.java
