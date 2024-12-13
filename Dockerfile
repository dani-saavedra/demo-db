FROM eclipse-temurin:17.0.13_11-jdk

ENV DB_HOST=localhost
ENV DB_DATABASE=resired
ENV DB_USER=user
ENV DB_PASS=password
COPY ./build/libs/pruebadb-0.0.1-SNAPSHOT.jar /app/api.jar

EXPOSE 8080

CMD ["java","-jar","/app/api.jar"]

#Si fuese python
#CMD ["python" ,"manage.py" ,"runserver"]