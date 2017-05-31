# Vida.LINK #

vida.link es un intermediario activo para la comunicación M2M, hoy más conocido cómo IoT. Consiste en un Broker, con distintos conectores entre los que está ZMQ , Socket.IO (Websocket) y webservices (REST)

Entre las cosas extras está una consola ssh para poder acceder y un panel de control web usando JSF, Primefaces y Bootstrap, se entra en el puerto 9080 , para ingresar al panel de administrador (admin@vida.link:vdl). 

El servidor ssh es sólo accesible usando Putty, el puerto es el 9022 (root:vdl), entre los comandos está *conn list* que muestra los connectores activos en el sistema y *thread ls* para ver las hebras de la aplicación. La idea de esta consola es administrar la aplicación.


## Requisitos ##

* Postgres SQL 9 o superior
* Usuario en Postgres ( usuario="vdl" , clave="vdl")
* Crear base de datos vdl , con owner vdl.
* Maven

## Correr aplicación ##

* mvn clean
* mvn build
* mvn run