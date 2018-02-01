package com.rdas.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import com.rdas.model.Employee;
import com.rdas.repo.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private EmployeeRepository repository;

    private HazelcastInstance instance;

    private IMap<Integer, Employee> map;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, HazelcastInstance hazelcastInstance) {
        this.repository = employeeRepository;
        this.instance = hazelcastInstance;
    }

    @PostConstruct
    public void init() {
        map = instance.getMap("employee");
        map.addIndex("company", true);
        logger.info("Employees cache at startup {}", map.size());
    }

    public Employee findByPersonId(Integer personId) {
        Predicate predicate = Predicates.equal("personId", personId);
        logger.info("Find from Employee cache personId {}", personId);
        Collection<Employee> ps = map.values(predicate);
        logger.info("Employee cached size {} " , ps.size());
        Optional<Employee> firstEmpl = ps.stream().findFirst();
        if (firstEmpl.isPresent()) {
            logger.info("Found from Employee personId {}", personId);
            return firstEmpl.get();
        }
        logger.info("Not found in Employee cache. Lookup repo. ");
        Employee emp = repository.findByPersonId(personId);
        if (emp != null) {
            logger.info("Found Employee: " + emp);
            map.put(emp.getId(), emp);
            return emp;
        } else {
            logger.info("Not found in Employee Repo.");
            return null;
        }
    }

    public List<Employee> findByCompany(String company) {
        Predicate predicate = Predicates.equal("company", company);
        logger.info("Employees cache find");
        Collection<Employee> ps = map.values(predicate);
        logger.info("Employees cache size: " + ps.size());
        if (ps.size() > 0) {
            return ps.stream().collect(Collectors.toList());
        }
        logger.info("Employees find");
        List<Employee> e = repository.findByCompany(company);
        logger.info("Employees size: " + e.size());
        e.parallelStream().forEach(it -> {
            map.putIfAbsent(it.getId(), it);
        });
        return e;
    }

    public Employee findById(Integer id) {
        Employee empl = map.get(id);
        if (empl != null)
            return empl;
        //empl = repository.findOne(id);
        empl = repository.findByPersonId(id);
        map.put(id, empl);
        return empl;
    }

    public Employee add(Employee empl) {
        empl = repository.save(empl);
        map.put(empl.getId(), empl);
        return empl;
    }
}
