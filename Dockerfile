FROM java:8
EXPOSE 8080

VOLUME /tmp
VOLUME /home/neil/Downloads/apache-jmeter-4
ADD renren-fast.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
