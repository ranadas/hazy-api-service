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
* docker-compose up -d --build
  docker-compose up -d --no-deps --build <service_name>     
* 


ReaDs: 
https://memorynotfound.com/spring-boot-hazelcast-caching-example-configuration/
https://dzone.com/articles/in-memory-data-grid-with-hazelcast
https://dzone.com/articles/microservice-architecture-with-spring-cloud-and-do
https://dzone.com/articles/5-things-java-programmer-should-learn-in-2018
https://github.com/topics/hazelcast?l=java&o=desc&s=updated
https://github.com/bitsofinfo/hazelcast-docker-swarm-discovery-spi