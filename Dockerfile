FROM amazoncorretto:11

COPY ./target/awssecretmanager-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch awssecretmanager-0.0.1-SNAPSHOT.jar'

RUN echo $DBHOST

ENTRYPOINT ["java","-DDBHOST=$DBHOST","-jar","awssecretmanager-0.0.1-SNAPSHOT.jar"]

EXPOSE 90