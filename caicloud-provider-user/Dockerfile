# 版本信息 
FROM primetoninc/jdk:1.8
MAINTAINER stonesfour "sunshilei@caicloud.io"

# change path to your repo dir
COPY ./caicloud-provider-user/target/caicloud-provider-user-0.0.1-SNAPSHOT.jar  /caicloud-provider-user.jar

EXPOSE 8081

CMD ["java", "-jar", "/caicloud-provider-user.jar"]