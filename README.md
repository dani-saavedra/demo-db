# Performance Spring jpa (hikari) vs DriverManager

## Java & Mysql en el mismo servidor 
Comparacíon de resultados de usar spring jpa con hikari por (defecto 10 connection pool)
vs Drivermanager.getConnection
### Resultados con 1 usuario concurrente

![1_user.png](results%2F1_user.png)

### Resultados con 5 usuario concurrentes
![5_users.png](results%2F5_users.png)

## Java & Mysql en  servidores diferentes

## Tecnologias

* Mysql 8.0.39
* Java 17
* Newrelic 

Comando:
java -javaagent:/Users/danielsaavedra/Documentos/arquitectura/newrelic/newrelic.jar -jar pruebadb-0.0.1-SNAPSHOT.jar