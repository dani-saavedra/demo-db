# Performance Spring jpa (hikari) vs DriverManager

Comparacíon de resultados de usar spring jpa con hikari por (defecto 10 connection pool)
vs Drivermanager.getConnection
## Resultados con 1 usuario concurrente

![1_user.png](results%2F1_user.png)

## Resultados con 5 usuario concurrentes
![5_users.png](results%2F5_users.png)
