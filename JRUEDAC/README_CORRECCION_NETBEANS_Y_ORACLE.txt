CORRECCION DEL ERROR DE NETBEANS

El error:
Source option 6 is no longer supported. Use 7 or later.

Se corrige en nbproject/project.properties con:
javac.source=1.8
javac.target=1.8

CONEXION ORACLE 10G

La clase ConexionBD esta en:
src/com/jruedac/conexion/ConexionBD.java

Quedo configurada asi:
url      = jdbc:oracle:thin:@localhost:1521:XE
usuario  = JRUEDAC
password = JRUEDAC

Si en el computador la base de datos usa ORCL, cambiar XE por ORCL.

PRIVILEGIOS PARA LA VISTA

Si sale ORA-01031: privilegios insuficientes, entrar como SYSTEM y ejecutar:

CONN SYSTEM/tu_clave
@database/00_PRIVILEGIOS_OBLIGATORIO_SYSTEM.sql

Luego entrar como JRUEDAC:

CONN JRUEDAC/JRUEDAC
@database/01_crear_bd_JRUEDAC.sql

Si la tabla ya existia sin FECHA_REGISTRO, ejecutar:
@database/04_REPARAR_TABLA_Y_VISTA_JRUEDAC.sql

Si no quieres agregar FECHA_REGISTRO, ejecutar:
@database/04B_VISTA_SIN_FECHA_REGISTRO_JRUEDAC.sql
