PROYECTO DATOS PERSONALES - JRUEDAC

1. Abrir el proyecto en Apache NetBeans.
2. Copiar ojdbc14.jar dentro de la carpeta lib.
   Debe quedar asi: JRUEDAC/lib/ojdbc14.jar
3. En NetBeans ir a Project Properties > Libraries > Add JAR/Folder.
4. Seleccionar lib/ojdbc14.jar.
5. Ejecutar en SQL*Plus primero como SYSTEM:

   @database/00_PRIVILEGIOS_SYSTEM.sql

   Cambiar tu_clave por la clave real de SYSTEM.

6. Ejecutar con JRUEDAC:

   @database/01_CREAR_BD_JRUEDAC.sql

7. En NetBeans hacer Clean and Build y despues Run.

CONEXION USADA:
url: jdbc:oracle:thin:@localhost:1521:XE
user: JRUEDAC
password: JRUEDAC

Si en la universidad usan ORCL, cambiar en ConexionBD.java:
XE por ORCL.

ERROR CLASICO:
ClassNotFoundException: oracle.jdbc.driver.OracleDriver

SOLUCION:
Ese error significa que falta ojdbc14.jar en Libraries del proyecto o no esta en lib/ojdbc14.jar.
