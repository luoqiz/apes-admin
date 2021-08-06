FROM openjdk:11-jre
VOLUME /tmp
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ADD build/libs/*.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT java  -Xms512m   -Xmx1024m  -Xmn256m   -Xss256k   -XX:SurvivorRatio=8  -jar  /app.jar
EXPOSE 80
