Para poder ambientar y/o levantar este microservicio , debera descargar el cliente de postgresql en su maquina local para posteriormente levantar el proyecto.
https://www.postgresql.org/download/windows/

Una vez descargado el programa deberá configurar durante la instalación la siguiente contraseña para el cliente de postgres:

usuario:postgres
pass:root

Una vez cumplido el proceso de instalación , procederá a descargar el código fuente de este proyecto para posteriormente levantarlo por la siguiente ruta

http://localhost:10443/mso-prueba/v1

Para verificar el health check del microservicio se usará la siguiente url: 
http://localhost:10443/mso-prueba/v1/status

Limpiar y construir el proyecto desde maven

Para limpiar el proyecto usando maven deberá usar el siguiente comando desde cmd :

mvn clean

Para descargar las dependencia y/o construir el proyecto deberá usar el siguiente comando desde cmd: 

mvn clean install -Dmaven.test.skip=true

Para ejecutar la aplicación deberá usar este comando y ejecutarlo desde cmd:

mvn spring-boot:run

Nota: para grabar información del usuario de token en la bd la aplicación en automatico cuando despliega , ésta genera en automatico en tiempo de ejecución las tablas y mapea las entidades para usarlas al momento.

Y cabe mencionarm que no será necesario insertar usuarios en la base de datos

Les anexo la info para la autenticación 
user:eguillenm
pass:Lalo1994
