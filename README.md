# Performance Spring jpa (hikari) vs DriverManager

## Java & Mysql en el mismo servidor 
Comparacíon de resultados de usar spring jpa con hikari por (defecto 10 connection pool)
vs Drivermanager.getConnection
### Resultados con 1 usuario concurrente

![1_user_same_server.png](results%2F1_user_same_server.png)
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
Como se observa en los gráficos anteriores el trabajar con conectandose directamente a la base de datos cada vez que se necesite hacer una operación, es decir, sin pool de conexiones 
no hace ninguna diferencia siempre y cuando se este trabajando la base de datos y la aplicación en el mismo servidor fisico. Esto se debe a que el tiempo que se requiere para hacer la conexión (por TCP) es practicamente nula al estar dentro del mismo server.

Sin embargo, los datos cambian completamente cuando la aplicación java se encuentra ejecutandose en un servidor diferente al de la base de datos, acá observamos como el tiempo 
incrementa en un 70% en comparación de usar un pool de conexiones. Aunque los tiempos aumentan al incrementar el número de usuarios, el crecimiento del tiempo del pool de conexiones
vs conexión directa se mantiene.

## Tecnologias

* Mysql 8.0.39
* Java 17
* Newrelic 

Comando:
java -javaagent:/Users/danielsaavedra/Documentos/arquitectura/newrelic/newrelic.jar -jar pruebadb-0.0.1-SNAPSHOT.jar