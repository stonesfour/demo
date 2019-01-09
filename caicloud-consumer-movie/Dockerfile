# 版本信息 
FROM primetoninc/jdk:1.8
MAINTAINER stonesfour "sunshilei@caicloud.io"

# change path to your repo dir
COPY ./caicloud-consumer-movie/target/caicloud-consumer-movie-0.0.1-SNAPSHOT.jar  /caicloud-consumer-movie.jar

EXPOSE 8082

CMD ["java", "-jar", "/caicloud-consumer-movie.jar"]