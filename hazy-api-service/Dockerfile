FROM ubuntu:latest

MAINTAINER Rana Das <rana.pratap.das@gmail.com>

RUN apt-get update \
  && apt-get install -y vim   \
  && apt-get install -y curl \
  && apt-get install -y iputils-ping \
  && apt-get install -y net-tools \
  && apt-get install -y lsof


#Install openjdk
RUN apt-get update && \
	apt-get install -y openjdk-8-jdk && \
	apt-get install -y ant && \
	apt-get clean && \
	rm -rf /var/lib/apt/lists/* && \
	rm -rf /var/cache/oracle-jdk8-installer;

# Fix certificate issues, found as of
# https://bugs.launchpad.net/ubuntu/+source/ca-certificates-java/+bug/983302
RUN apt-get update && \
	apt-get install -y ca-certificates-java && \
	apt-get clean && \
	update-ca-certificates -f && \
	rm -rf /var/lib/apt/lists/* && \
	rm -rf /var/cache/oracle-jdk8-installer;

# Setup JAVA_HOME
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME

RUN	mkdir /api
WORKDIR /api

ADD build/libs/haze-api-server.jar  boot-api.jar
RUN chmod 777 -R /api

ENTRYPOINT ["java" ,"-Djava.security.egd=file:/dev/./urandom" ,"-Dspring.profiles.active=test ", "-jar" ,"boot-api.jar"]
EXPOSE 8089





