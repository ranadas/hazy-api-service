package com.rdas.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.SerializerConfig;
import com.rdas.model.Employee;
import com.rdas.service.EmployeeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfiguration {
    /*
    declare cluster names and password credentials, connection parameters to the Hazelcast Management Center,
    and entity serialization configuration.
     */
    @Bean
    public Config config() {
        Config config = new Config();
        config.setInstanceName("hazelcast-instance");
        config.getGroupConfig().setName("dev").setPassword("dev-pass");
        /*
        JoinConfig joinConfig = config.getNetworkConfig().getJoin();
        joinConfig.getMulticastConfig().setEnabled(false);
        joinConfig.getTcpIpConfig().setEnabled(true).setMembers(singletonList("127.0.0.1"));
         */
        ManagementCenterConfig mcc = new ManagementCenterConfig().setUrl("http://localhost:38080/mancenter").setEnabled(true);
        config.setManagementCenterConfig(mcc);

        SerializerConfig sc = new SerializerConfig().setTypeClass(Employee.class).setClass(EmployeeSerializer.class);
        config.getSerializationConfig().addSerializerConfig(sc);
        return config;
    }
    /*
    //https://github.com/armdev/hazelcast-spring-boot/blob/master/src/main/java/io/project/app/HazelcastConfig.java
    @Bean
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config();
        config.setProperty("hazelcast.jmx", "true");
        LocalListConfig listConfig = new LocalListConfig();
        listConfig.setMaxSize(50);
        listConfig.setTimeToLiveSeconds(10);
        listConfig.setBackupCount(0);
        listConfig.setName("contactList");

        config.addListConfig(listConfig);

        ContactMapConfig contactMapConfig = new ContactMapConfig();

        contactMapConfig.setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE));
        contactMapConfig.setTimeToLiveSeconds(10);
        contactMapConfig.setBackupCount(0);
        contactMapConfig.setName("contactMap");
        contactMapConfig.setEvictionPolicy(EvictionPolicy.LRU);
        contactMapConfig.setEvictionPercentage(25);
        contactMapConfig.setInMemoryFormat(InMemoryFormat.BINARY);
        contactMapConfig.setMergePolicy("com.hazelcast.map.merge.PassThroughMergePolicy");

        config.addMapConfig(contactMapConfig);

        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);

        return instance;
    }
     */
}
