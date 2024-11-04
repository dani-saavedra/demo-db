# Performance Spring jpa (hikari) vs DriverManager

Para comparar el perfomance de trabajar con Hikari o DriverManager(conexión directa sin pool) se tendrán dos tipos de muestras diferentes. Una donde esten la aplicación java y la base de datos en el mismo servidor
y otra donde la aplicación java y la base de datos están en servidores distintos.

## Java & Mysql en el mismo servidor 
Comparacíon de resultados de usar spring jpa con hikari por (defecto 10 connection pool)
vs Drivermanager.getConnection
### Resultados con 1 usuario concurrente
![1_user__same_server.png](results%2F1_user__same_server.png)
### Resultados con 5 usuario concurrentes
![5_users__same_server.png](results%2F5_users__same_server.png)


## Java & Mysql en  servidores diferentes

### Resultados con 1 usuario concurrentes
![1_users_different_server.png](results%2F1_users_different_server.png)
### Resultados con 5 usuario concurrentes

![5_User_different_server.png](results%2F5_User_different_server.png)

### Resultados con 10 usuario concurrentes
![10_User_different_server.png](results%2F10_User_different_server.png)

## Analisis
Como se puede ver en los gráficos anteriores, al trabajar en el mismo server, no hace diferencia el conectarse a la base de datos
a través de un pool de conexiones o abrir/cerrar conexión cada que se necesite la base de datos.
Esto se debe a que el tiempo que se requiere para hacer la conexión (por TCP) es practicamente nula al estar dentro del mismo server.

Sin embargo, los datos cambian completamente cuando la aplicación java se encuentra ejecutandose en un servidor diferente al de la base de datos, acá observamos como el tiempo 
incrementa en un 70% en comparación de usar un pool de conexiones. Aunque los tiempos aumentan al incrementar el número de usuarios, el crecimiento del tiempo del pool de conexiones
vs conexión directa se mantiene.

## Tecnologias

* Mysql 8.0.39: DB
* Java 17 Backend
* Newrelic como APM

Comando:
java -javaagent:/Users/danielsaavedra/Documentos/arquitectura/newrelic/newrelic.jar -jar pruebadb-0.0.1-SNAPSHOT.jar