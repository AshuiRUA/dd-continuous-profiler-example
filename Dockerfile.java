FROM openjdk:17

COPY . /home
WORKDIR /home/java
RUN curl -L -o dd-java-agent.jar 'http://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=com.datadoghq&a=dd-java-agent&v=latest'
RUN ./gradlew --no-daemon installDist
CMD JAVA_OPTS="-Ddd.agent.host=$DD_AGENT_HOST -Ddd.profiling.enabled=true -javaagent:dd-java-agent.jar" ./build/install/movies-api-java/bin/movies-api-java
