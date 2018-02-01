package com.rdas.service;

import com.rdas.model.Employee;
import com.rdas.repo.EmployeeRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.stream.IntStream;

import static junit.framework.TestCase.assertNotNull;

//http://www.lucassaldanha.com/unit-and-integration-tests-in-spring-boot/
//http://www.baeldung.com/spring-boot-testing
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServicesTest {
    private static final Logger logger = LoggerFactory.getLogger(ServicesTest.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void assertThatRepoIsInjected() {
        assertNotNull(employeeRepository);
    }

    @Test
    public void assertThatEmployeesAreAdded() {
        IntStream.range(0, 20000).parallel().forEach(i -> {
            int ix = new Random().nextInt(100);
            Employee e = Employee.builder()
                    //.id(i)
                    .personId(i)
                    .company("TEST" + new DecimalFormat("000").format(ix))
                    .build();
            e = employeeRepository.save(e);
            logger.info("Added: " + e);
        });
    }
}
