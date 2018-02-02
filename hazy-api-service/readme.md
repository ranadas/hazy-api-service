TODO :
https://reflectoring.io/monitoring-spring-boot-with-prometheus/
Configure Hazelcast Management Center in Spring Boot. 

features : 
1. Spring boot 2.x
2. JPA with H2
3. undertow instead of tomcat


* docker build -t  hazebo-image .
* docker run -d -p8089:8089 -t  --name hazebo-cont  hazebo-image
* docker exec -it  hazebo-cont /bin/bash
    