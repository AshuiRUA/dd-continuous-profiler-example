FROM openjdk:11

COPY . /home
WORKDIR /home/java
RUN wget https://github.com/DataDog/dd-trace-java/releases/download/v0.74.1/dd-java-agent-0.74.1.jar
RUN ./gradlew --no-daemon installDist
RUN chmod 777 dd-java-agent-0.74.1.jar
CMD JAVA_OPTS="-Ddd.agent.host=$DD_AGENT_HOST -Ddd.profiling.enabled=true -javaagent:dd-java-agent-0.74.1.jar" ./build/install/movies-api-java/bin/movies-api-java
