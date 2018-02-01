package com.rdas.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.SerializerConfig;
import com.rdas.model.Employee;
import com.rdas.service.EmployeeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfiguration {
    /*
    declare cluster names and password credentials, connection parameters to the Hazelcast Management Center,
    and entity serialization configuration.
     */
    @Bean
    public Config config() {
        Config config = new Config();
        config.setInstanceName("cache-1");
        config.getGroupConfig().setName("dev").setPassword("dev-pass");
        ManagementCenterConfig mcc = new ManagementCenterConfig().setUrl("http://192.168.99.100:38080/mancenter").setEnabled(true);
        config.setManagementCenterConfig(mcc);

        SerializerConfig sc = new SerializerConfig().setTypeClass(Employee.class).setClass(EmployeeSerializer.class);
        config.getSerializationConfig().addSerializerConfig(sc);
        return config;
    }
}
